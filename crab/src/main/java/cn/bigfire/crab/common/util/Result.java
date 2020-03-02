package cn.bigfire.crab.common.util;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：web 统一返回工具类
 */
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private  T data;
    private String time;
    public Result(){
        setTime(LocalDateTime.now().toString());
    }
    public Result(Integer code){
        setCode(code).setTime(LocalDateTime.now().toString());
    }
    public Result(Integer code, String msg){
        setCode(code).setMsg(msg).setTime(LocalDateTime.now().toString());
    }
    public Result(Integer code, String msg, T data){
        setCode(code).setMsg(msg).setData(data).setTime(LocalDateTime.now().toString());
    }
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;
    private static final String SUCCESS_MSG = "成功";
    private static final String ERROR_MSG = "失败";
    //成功返回值
    public static Result getSuccessResult() {
        return new Result(SUCCESS_CODE,SUCCESS_MSG);
    }
    public static <T> Result<T> getSuccessResult(T data) {
        return new Result<T>(SUCCESS_CODE,SUCCESS_MSG,data);
    }
    //错误返回值
    public static Result getErrorResult() {
        return new Result(ERROR_CODE,ERROR_MSG);
    }
    public static Result getErrorResult(String msg) {
        return new Result(ERROR_CODE,msg);
    }
    //自定义消息
    public static Result getMsgResult(String msg) {
        return new Result(SUCCESS_CODE,msg);
    }
    public static <T> Result<T> getMsgResult(String msg,T data) {
        return getMsgResult(msg).setData(data);
    }
    //自定义返回Code
    public static Result getCodeResult(Integer code,String msg) {
        return new Result(code,msg);
    }
    public static <T> Result<T> getCodeResult(Integer code,String msg,T data) {
        return getCodeResult(code,msg).setData(data);
    }
    public Integer getCode() {
        return code;
    }
    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
    public String getMsg() {
        return msg;
    }
    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public T getData() {
        return data;
    }
    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
    public String getTime() {
        return time;
    }
    public Result<T> setTime(String time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
