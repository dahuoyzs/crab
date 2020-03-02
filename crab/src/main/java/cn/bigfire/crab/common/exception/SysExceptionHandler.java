package cn.bigfire.crab.common.exception;

import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/13  20:12
 * @ Desc   ：全局异常处理类
 */
@RestControllerAdvice
public class SysExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(SysExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        logger.error(Console.where()+e.getMessage());
        Console.printStackTrace(e);
        return Result.getErrorResult("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(SysException.class)
    public Result handleSysException(SysException e){
        logger.error(Console.where()+e.getMessage());
        Console.printStackTrace(e);
        return Result.getCodeResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e){
        logger.error(Console.where()+e.getMessage());
        Console.printStackTrace(e);
        return Result.getErrorResult("请求ERROR:"+e.getMessage());
    }

}
