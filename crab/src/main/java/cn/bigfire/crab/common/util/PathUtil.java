package cn.bigfire.crab.common.util;

import cn.hutool.core.util.StrUtil;

import java.util.Calendar;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  14:40
 * @ Desc   ：
 */
public class PathUtil {

    /**
     * 路径添加月份
     *
     * @param fileName                 文件路径（包含文件名）
     * @return String               返回 路径中添加了月份的路径
     * 如：
     *      输入  /crab/avatar/a.png
     *      返回  /crab/avatar/2019-10/a.png
     */
    public static String toMonthPath(String fileName){

        fileName = normalizePath(fileName);

        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = calendar.get(Calendar.MONTH)+1+"/";
        String monthPath = year + "/" + month;

        int index = fileName.lastIndexOf("/")+1;
        String pre = fileName.substring(0,index);

        return  StrUtil.appendIfMissing(pre,monthPath) + fileName.substring(index);
    }

    /**
     * 路径添加日期
     *
     * @param fileName              又拍云路径（包含文件名）
     * @return String               返回 路径中添加了日期的路径
     * 如：
     *      输入  /crab/avatar/a.png
     *      返回  /crab/avatar/2019-10/15/a.png
     *      d:a/b/c/
     *      d:a/b/c
     */
    public static String toDayPath(String fileName){

        fileName = toMonthPath(fileName);

        Calendar calendar = Calendar.getInstance();
        String day = calendar.get(Calendar.DATE)+"/";

        int index = fileName.lastIndexOf("/")+1;
        String pre = fileName.substring(0,index);

        return  StrUtil.appendIfMissing(pre,day) + fileName.substring(index);
    }

    /**
     * 获得文件名(不带后缀)
     *
     * @param  fileName                 路径+文件名
     * @return String                   返回 文件名去掉后缀
     * 如：
     *      输入  D:\a\b\c/d/d/pp.txt
     *      返回  pp
     */
    public static String getFileBaseName(String fileName){

        fileName = normalizePath(fileName);

        int separatorIndex = fileName.lastIndexOf("/");
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0 || separatorIndex >= dotIndex) return fileName;
        return fileName.substring(separatorIndex + 1, dotIndex);
    }

    /**
     * 获得文件后缀
     *
     * @param  fileName                 路径+文件名
     * @return String                   返回 文件后缀
     * 如：
     *      输入  D:\a\b\c/d/d/pp.txt
     *      返回  txt
     */
    public static String getFileSuffix(String fileName){

        fileName = normalizePath(fileName);

        int dotIndex = fileName.lastIndexOf( '.');
        if (dotIndex < 0) { return ""; }

        return fileName.substring(dotIndex + 1);
    }

    /**
     * 标准化文件路径
     *
     * @param path                  磁盘路径
     * @return String               返回 标准话的/形式的路径
     * 如：
     *      输入  D:\a\b\c/d/d/pp.txt
     *      返回  D:/a/b/c/d/d/pp.txt       [补齐]
     *      输入  D:\a\b\c/d/d
     *      返回  D:/a/b/c/d/d/             [标准+补齐]
     */
    public static String normalizePath(String path){
        if (!path.contains("."))
            path = StrUtil.appendIfMissing(path,"/");
        return path.replace("\\","/");
    }
    /**
     * 接口中的static和default方法有啥区别？
     * default可以被继承，同时可以被子类覆写。
     * static不可用被继承，只能通过 类.方法 的方式调用
     * */
}
