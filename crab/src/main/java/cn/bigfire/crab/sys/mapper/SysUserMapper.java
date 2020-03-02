package cn.bigfire.crab.sys.mapper;

import cn.bigfire.crab.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-10
* @ Desc   ： Mapper 接口
*/

public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
