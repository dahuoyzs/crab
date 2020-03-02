package cn.bigfire.crab.sys.service;

import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.sys.dto.SysLoginParam;
import cn.bigfire.crab.sys.dto.SysLoginResult;
import cn.bigfire.crab.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-10
* @ Desc   ： 服务类
*/
public interface SysUserService extends IService<SysUser> {

    SysUser sysUserByToken(String token);

    Set<String> getUserPermissions(Long userId);

    Result login(SysLoginParam sysLoginParam);
}
