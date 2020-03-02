package cn.bigfire.crab.sys.controller;


import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.common.util.TokenUtil;
import cn.bigfire.crab.sys.dto.SysLoginParam;
import cn.bigfire.crab.sys.entity.SysUser;
import cn.bigfire.crab.sys.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-10
* @ Desc   ： 前端控制器
*/
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @GetMapping("/info")
    @ApiOperation("用户信息")
    public Result info(@RequestHeader String token){
        return Result.getSuccessResult(
                sysUserService.getById(TokenUtil.getId(token))
                .setPassword("******")
        );
    }

    @GetMapping()
    @ApiOperation("用户列表")
    public Result list(@RequestHeader String token,
                       @RequestParam(value = "page",defaultValue = "1")Integer page,
                       @RequestParam(value = "size",defaultValue = "5")Integer size){
        return Result.getSuccessResult(sysUserService.page(new Page<>(page,size)));
    }

}
