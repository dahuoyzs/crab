package cn.bigfire.crab.common.aop;

import cn.bigfire.crab.common.annotation.SaveLog;
import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.sys.entity.SysLog;
import cn.bigfire.crab.sys.mapper.SysLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/6  9:30
 * @ Desc   : 插入日志到数据库,
 * SaveLog注解用于如果有的接口比较重要，可用把日志保存到数据库中进行保存
 */

@Slf4j
@Aspect
@Component
public class SaveLogAspect {
    @Autowired
    SysLogMapper sysLogMapper;

    @Around("@annotation(cn.bigfire.crab.common.annotation.SaveLog)")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        log.info("添加日志拦截");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SaveLog saveLog = method.getAnnotation(SaveLog.class);
        String operation = saveLog.value();
        SysLog sysLog = new SysLog()
                .setOperation(operation)
                .setUrl(request.getRequestURL().toString())
                .setIp(request.getRemoteAddr())
                .setMethod(request.getMethod())
                .setParams(Arrays.toString(joinPoint.getArgs()))
                .setCreateTime(new Date());


        long beginTime = System.currentTimeMillis();
        try {
            Object object = joinPoint.proceed();
            long time = System.currentTimeMillis() - beginTime;
            sysLog.setUseTime(time);
            sysLogMapper.insert(sysLog);
            log.info("添加日志成功");
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            long time = System.currentTimeMillis() - beginTime;
            sysLog.setUseTime(time);
            sysLogMapper.insert(sysLog);
            log.info("存日志时出现错误");
            return Result.getErrorResult("存日志时出现错误:" + throwable.getMessage());
        }
    }
}
