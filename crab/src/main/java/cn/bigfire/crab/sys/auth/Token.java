package cn.bigfire.crab.sys.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/10  19:36
 * @ Desc   ：
 */
public class Token implements AuthenticationToken {
    private String token;
    public Token(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
