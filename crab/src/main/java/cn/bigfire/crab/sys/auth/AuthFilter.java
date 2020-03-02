package cn.bigfire.crab.sys.auth;

import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.common.util.TokenUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/10  17:47
 * @ Desc   ：
 */

public class AuthFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        return new Token(((HttpServletRequest) request).getHeader("token"));
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = ((HttpServletRequest) request).getHeader("token");
        if (token==null||token.isEmpty()){
            response.getWriter().print(Result.getCodeResult(401,"no token"));
            return false;
        }
        if (TokenUtil.isTokenExpired(token)){//错误token
            response.getWriter().print(Result.getCodeResult(403,"err token"));
            return false;
        }
        return executeLogin(request, response);
    }
}
