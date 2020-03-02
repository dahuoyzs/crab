package cn.bigfire.crab.common.util;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：web 统一返回工具类
 * Result的简化形式
 */
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private  T data;
    private String time;
    public R(){
        setTime(LocalDateTime.now().toString());
    }
    public R(Integer code){
        setCode(code).setTime(LocalDateTime.now().toString());
    }
    public R(Integer code, String msg){
        setCode(code).setMsg(msg).setTime(LocalDateTime.now().toString());
    }
    public R(Integer code, String msg, T data){
        setCode(code).setMsg(msg).setData(data).setTime(LocalDateTime.now().toString());
    }
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;
    private static final String SUCCESS_MSG = "成功";
    private static final String ERROR_MSG = "失败";
    //成功返回值
    public static R ok() {
        return new R(SUCCESS_CODE,SUCCESS_MSG);
    }
    public static R ok(Object data) {
        return ok().setData(data);
    }
    //错误返回值
    public static R error() {
        return new R(ERROR_CODE,ERROR_MSG);
    }
    public static R error(String msg) {
        return new R(ERROR_CODE,msg);
    }
    //自定义消息
    public static R msg(String msg) {
        return new R(SUCCESS_CODE,msg);
    }
    public static R msg(String msg,Object data) {
        return msg(msg).setData(data);
    }
    //自定义返回Code
    public static R code(Integer code,String msg) {
        return new R(code,msg);
    }
    public static R code(Integer code,String msg,Object data) {
        return code(code,msg).setData(data);
    }
    public Integer getCode() {
        return code;
    }
    public R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
    public String getMsg() {
        return msg;
    }
    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public T getData() {
        return data;
    }
    public R<T> setData(T data) {
        this.data = data;
        return this;
    }
    public String getTime() {
        return time;
    }
    public R<T> setTime(String time) {
        this.time = time;
        return this;
    }
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
