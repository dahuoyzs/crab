package cn.bigfire.crab.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：控制台工具类
 */
public class Console {

    private Console(){}
    /**
     * 控制台打印日志，显示具体在哪一行打印的日志，在linux上则只打印很短的信息
     * @param object        要打印的对象
     * @return boolean      返回是否失效 true失效,false有效
     */
    public static void log(Object object)
    {

        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String where = System.getProperties().getProperty("os.name").toLowerCase().contains("linux") ?
                ste.getMethodName()+"(" +ste.getFileName()+":"+ ste.getLineNumber()+")" :
                ste.getClassName()+"."+ste.getMethodName()+"(" +ste.getFileName()+":"+ ste.getLineNumber()+")";
        System.out.print("["+where+"]");
        System.out.println(object);
    }

    /**
     * 控制台打印日志，显示具体在哪一行打印的日志
     * @param object        要打印的对象
     * @return boolean      返回是否失效 true失效,false有效
     */
    public static void debug(Object object)
    {
        System.out.print("["+where()+"]");
        System.out.println(object);
    }

    /**
     * 获取异常栈的最顶部一条信息
     * @return String      返回where()的当前行号位置
     * 如：
     * cn.bigfire.crab.TestApp.testA(TestApp.java:376)
     */
    public static String where()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return strOf(ste.getClassName(),".",ste.getMethodName(),"(" ,ste.getFileName(),":",ste.getLineNumber()+")");
    }

    /**
     * 打印异常信息，用来替代e.printStackTrace.只打印5条异常信息
     * e.printStackTrace的缺点：
     * 1.e.printStackTrace内部打印使用的是System.err的打印，System.err和System.out的区别 在于System.err在打印过程中，可能会掺杂其他的输出
     * 2.这个方法在生产环境可能应为栈太多导致异常信息不显示。
     *
     System.err和System.out的区别
     https://blog.csdn.net/captainCZY/article/details/79496959
     */
    public static void printStackTrace(Throwable throwable)
    {
        System.out.println(error(throwable));
    }

    /**
     * 获取异常栈顶部的5条异常信息，默认不打印，只返回错误信息
     * @return String           返回异常栈顶部的5条异常信息
     * 效果:
    java.lang.ArithmeticException: / by zero
        at cn.bigfire.crab.TestApp.testA(TestApp.java:379)
        at cn.bigfire.crab.TestApp.test22(TestApp.java:364)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-2)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     */
    public static String error(Throwable throwable)
    {
        StackTraceElement[] elements = throwable.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(throwable);
        sb.append("\n");
        for (int i = 0; i < 5; i++) {
            StackTraceElement ste = elements[i];
            String where = strOf("\tat ", ste.getClassName(), ".", ste.getMethodName(), "(", ste.getFileName(), ":", ste.getLineNumber() + ")");
            sb.append(i == 4 ? where : where + "\n");
        }
        return sb.toString();
    }

    /**
     * 用StringBuilder拼接字符串
     * @return String      返回拼接好后的字符串
     */
    public static String strOf(Object ...s){
        StringBuilder sb = new StringBuilder();
        Stream.of(s).forEach(sb::append);
        return sb.toString();
    }

    /**
     * 打印map
     * */
    public static void printMap(Map map) {
        System.out.println(JSONObject.toJSONString(map));
    }
    /**
     * 打印list
     * */
    public static void printList(List list){
        System.out.println(JSONArray.toJSONString(list));
    }
}
