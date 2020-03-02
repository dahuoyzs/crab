package cn.bigfire.crab.common.util;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/29  11:47
 * @ Desc   ：
 */
public interface Message {

    // errResultMessage
    String ID_NOT_NULL = "id不能为空";
    String ACCOUNT_ERROR = "手机号不能为空";
    String PHONE_NOT_NULL = "手机号不能为空";
    String PHONE_FORMAT_ERROR = "手机号格式不正确";
    String EMAIL_NOT_NULL = "邮箱不能为空";
    String EMAIL_FORMAT_ERROR = "邮箱号格式不正确";
    String TOKEN_NOT_NULL = "token不能为空";
    String TOKEN_ERROR = "非法token";

    enum ERROR{

    }
    enum CODE{

    }
}
