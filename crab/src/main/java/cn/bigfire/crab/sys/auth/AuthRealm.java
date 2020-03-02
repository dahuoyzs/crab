package cn.bigfire.crab.sys.auth;

import cn.bigfire.crab.common.exception.SysException;
import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.TokenUtil;
import cn.bigfire.crab.sys.entity.SysUser;
import cn.bigfire.crab.sys.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/9  19:29
 * @ Desc   ：授权规则类
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }
    /**
     * 执行授权逻辑(验证权限时调用 @RequiresPermissions("sys:menu:list") 注解修饰的方法调用时，会检测set中是否包含 )
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long) principals.getPrimaryPrincipal();

        Set<String> permsSet = sysUserService.getUserPermissions(userId);//用户权限列表
        permsSet.add("sys:loglevel:test02");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }
    /**
     * 执行认证逻辑(登录时调用)
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Console.log("Token校验完成,进行用户状态校验");
        String accessToken = (String) token.getPrincipal();
        SysUser sysUser = sysUserService.sysUserByToken(accessToken);
        if (sysUser==null){
            throw new SysException("用户不存在,请重新登录");
        }
        if (sysUser.getUserStatus()==0){
            throw new SysException("账号不可用,请联系管理员激活");
        }

        return new SimpleAuthenticationInfo(sysUser.getId(), token.getCredentials().toString(), getName());
    }
}
