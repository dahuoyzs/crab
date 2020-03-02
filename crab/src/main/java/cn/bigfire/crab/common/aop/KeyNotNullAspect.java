package cn.bigfire.crab.common.aop;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import cn.bigfire.crab.common.annotation.KeyNotNull;
import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.Constant;
import cn.bigfire.crab.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/6  9:30
 * @ Desc   :  Controller 输入参数校验
 * 注解支持的方式
 * 1.@KeyNotNull("username")
 * 2.@KeyNotNull({"username","password"})
 * 3.@KeyNotNull("username,password")
 */
@Slf4j
@Aspect
@Component
public class KeyNotNullAspect {
    @Around("@annotation(cn.bigfire.crab.common.annotation.KeyNotNull)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            KeyNotNull keyNotNull = method.getAnnotation(KeyNotNull.class);
            String[] keys = keyNotNull.value();
            //去重，且处理以逗号分割的参数
            TreeSet<String> set = new TreeSet<>();
            Arrays.stream(keys).forEach(s -> set.addAll(s.contains(",")? Arrays.asList(s.split(",")): Arrays.asList(s)));
            set.removeIf(s -> s.contains(","));

            String contentType = request.getContentType();
            if (contentType!=null && contentType.startsWith(Constant.JSON)){
                for (String key:set)
                    if (isNull(key,joinPoint.getArgs()))
                        return Result.getErrorResult(key+"字段不能为空");
            }else if (contentType!=null && (contentType.startsWith(Constant.FORM_URLENCODED) || contentType.startsWith(Constant.MULTIPART))){
                for (String key:set) {
                    if (!request.getParameterMap().keySet().contains(key))
                        return Result.getErrorResult(key+"字段不能为空");
                }
            }
        }catch (Throwable throwable){
            log.error(throwable.getMessage());
            Console.printStackTrace(throwable);
        }
        return joinPoint.proceed();
    }
    private boolean isNull(String key, Object[] args){
        for (Object o:args) {
            try{
                JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(o));
                return !jo.keySet().contains(key);
            }catch (JSONException e){
                log.debug(Console.where()+e.getMessage());
            }
        }
        return true;
    }
}
