package cn.bigfire.crab.common.aop;

import cn.bigfire.crab.common.util.Constant;
import cn.bigfire.crab.sys.entity.SysLog;
import cn.bigfire.crab.sys.mapper.SysLogMapper;
import com.alibaba.fastjson.JSONObject;
import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;


/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/6  9:30
 * @ Desc   : 日志收集切面类
 * 方法执行顺序   doAround  ->  doBefore  ->  doAfter ->  doAfterReturning  ->  doAround
 */
@Slf4j
@Aspect
@Component
public class HttpLogAspect {
    @Autowired
    SysLogMapper sysLogMapper;

    @Pointcut("execution(* cn.bigfire.crab.*.controller.*.*(..))")
    public void log() {
    }
    @Around("log()")
    public Object doAround(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        try {
            return point.proceed();//放行操作
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
            log.error(Console.where() + throwable.getMessage());
            Console.printStackTrace(throwable);
            return Result.getErrorResult("后台ERROR:" + throwable.getMessage());
        }finally {

            long time = System.currentTimeMillis() - beginTime;
            log.info("请求耗时={}", time);

            if (time>1000){//接口耗时长加入日志
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String operation = "【耗时接口】";
                SysLog sysLog = new SysLog()
                        .setOperation(operation)
                        .setUrl(request.getRequestURL().toString())
                        .setIp(request.getRemoteAddr())
                        .setMethod(request.getMethod())
                        .setParams(Arrays.toString(point.getArgs()))
                        .setUseTime(time)
                        .setCreateTime(new Date());
                sysLogMapper.insert(sysLog);
                log.info("耗时接口已加入日志");
            }

            //【知识点】finally 会在 try 和catch 的return语句之前执行，如果finally内又return,try和catch里的return不生效
            //return ;

        }
    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String packClassName = joinPoint.getSignature().getDeclaringTypeName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = joinPoint.getSignature().getName();
        System.out.println();
        log.info("URL={}", request.getRequestURL());
        log.info("Method={}", request.getMethod());
        log.info("IP={}", request.getRemoteAddr());
        log.info("Class.Method={}", packClassName + "." + method + "(" + className + ".java:1)");
        log.info("ContentType={}", request.getContentType());
        log.info("Args={}", joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter() {
    }
    @AfterReturning(returning = "response", pointcut = "log()")
    public void doAfterReturning(Object response) {
        log.debug("response={}", JSONObject.toJSONString(response));
    }


}
