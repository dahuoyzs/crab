package cn.bigfire.crab.sys.service.impl;

import cn.bigfire.crab.common.util.Constant;
import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.common.util.TokenUtil;
import cn.bigfire.crab.sys.dto.SysLoginParam;
import cn.bigfire.crab.sys.dto.SysLoginResult;
import cn.bigfire.crab.sys.entity.SysMenu;
import cn.bigfire.crab.sys.entity.SysUser;
import cn.bigfire.crab.sys.mapper.SysMenuMapper;
import cn.bigfire.crab.sys.mapper.SysUserMapper;
import cn.bigfire.crab.sys.service.SysUserService;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-10
* @ Desc   ： 服务实现类
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser sysUserByToken(String token) {
        return sysUserMapper.selectById(TokenUtil.getId(token));
    }


    @Override
    public Set<String> getUserPermissions(Long userId) {

        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN_ID){
            List<SysMenu> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StrUtil.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public Result login(SysLoginParam sysLoginParam) {

        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                .eq("username",sysLoginParam.getUsername())
                .eq("password",sysLoginParam.getPassword()));

        if (sysUsers.size()>0){
            SysUser sysUser = sysUsers.get(0);

            String token = TokenUtil.createToken(sysUser.getId());
            SysLoginResult sysLoginResult = Convert.convert(SysLoginResult.class,sysUser);
            sysLoginResult.setToken(token);

            return Result.getMsgResult("登录成功",sysLoginResult);
        }else {
            return Result.getErrorResult("登录失败,密码错误,或用户不存在");
        }
    }
}
