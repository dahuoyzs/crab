package cn.bigfire.crab.sys.controller;

import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.common.util.TokenUtil;
import cn.bigfire.crab.sys.dto.SysLoginParam;
import cn.bigfire.crab.sys.entity.SysUser;
import cn.bigfire.crab.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/8  14:54
 * @ Desc   ：
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController {
    @Autowired
    SysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation("系统登录")
    public Result login(@RequestBody @Valid SysLoginParam sysLoginParam){
        return sysUserService.login(sysLoginParam);
    }

    @GetMapping("/logout")
    @ApiOperation("系统登录")
    public Result logout(@RequestHeader String token){
        // clear token by redis Or DB
        return Result.getSuccessResult();
    }

}
