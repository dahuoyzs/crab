/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.bigfire.crab.common.util;


/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：常量
 * 常量存放位置说明
 * 1.配置常量：则写在配置文件中,需要时注入，如：支付回调，审核回调，等各种回调地址，写在配置文件中可保证不同环境下常量的可用性。
 * 2.通用常量：项目中多处用可能用到的常量，统一写在Constant中。
 * 3.特定常量：只有某个类会使用，其他地方基本不会用到的常量，写在类的开头即可。前提要确保这些常量在不同环境下均可用。如:邮件,短信,又拍云等
 */
public interface Constant{
    //通用常量
	Long SUPER_ADMIN_ID = 1L;                                                                       //超级管理员id
    String PAGE = "page";                                                                           //当前页码
    String SIZE = "size";                                                                           //每页个数
    String ASC = "asc";                                                                             //升序
    String DESC = "desc";                                                                           //逆序

    //Http请求方式
    String JSON = "application/json";                                                               // Request请求application/json编码      JSON格式
    String XML = "application/xml";                                                                 // Request请求application/xml编码       XML格式
    String TEXT_XML = "text/xml";                                                                   // Request请求text/xml编码              普通文本或者网页格式
    String MULTIPART = "multipart/form-data";                                                       // Request请求form-data编码             文件上传格式
    String FORM_URLENCODED = "application/x-www-form-urlencoded";                                   // Request请求x-www-form-urlencoded编码（name1=value1&name2=value2…）

    //系统信息
    String JAVA_HOME = System.getProperty("java.home");                                             //当前JAVA_HOME目录
    String JAVA_VERSION = System.getProperty("java.version");                                       //当前JAVA版本
    String OS_NAME = System.getProperty("os.name");                                                 //当前系统名称
    String OS_VERSION = System.getProperty("os.version");                                           //当前系统版本
    String USER_NAME = System.getProperty("user.name");                                             //当前系统用户
    String USER_HOME = System.getProperty("user.home");                                             //当前用户目录
    String USER_DIR = System.getProperty("user.dir");                                               //当前项目所在目录
    String FILE_SEPARATOR = System.getProperty("file.separator");                                   //文件路径分割服"/" on Unix "\" on Windows

    /**
     * 换行
     * */
    String LINE = System.lineSeparator();
    /**
     * 回车
     * */
    String ENTER = System.lineSeparator();
    /**
     * 制表符
     * */
    String TAB = "\t";

    //DIR
    String DIR = USER_DIR + "/data/";                                                             //文件存储路径

    String DIR_BACKUP = DIR + "backup/";                                                          //备份路径
    String DIR_CACHE = DIR + "cache/";                                                            //缓存路径
    String DIR_DOC = DIR + "doc/";                                                                //文档路径
    String DIR_LOG = DIR + "log/";                                                                //日志路径
    String DIR_SQL = DIR + "sql/";                                                                //sql路径
    String DIR_UPLOAD = DIR + "upload/";                                                          //上传路径

}
