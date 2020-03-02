package cn.bigfire.crab.test.controller;

import cn.bigfire.crab.common.tool.upload.UploadTool;
import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.Result;
import cn.bigfire.crab.sys.entity.SysUser;
import cn.bigfire.crab.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/11  10:41
 * @ Desc   ：
 */

@RestController
@RequestMapping("/test")
@Api(tags = "测试相关接口")
public class TestController {

    @GetMapping("/zero")
    @ApiOperation("测试系统内发生runtime异常时aop的效果")
    public String zero(){
        int runtimeException = 10/0;
        Console.log(runtimeException);
        return "IndexController.zero()";
    }

    @GetMapping("/result")
    @ApiOperation("测试Result工具类的格式是否正确")
    public Result<String> result(){
        return Result.getSuccessResult("IndexController.result()");
    }

//    @Autowired
//    UploadTool uploadTool;



    @GetMapping("/sleep")
    @ApiOperation("测试sleep耗时接口录入日志")
    public Result sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.getSuccessResult();
    }

//    @PostMapping("/upload")
//    @ApiOperation("上传文件")
//    public Result upload(@RequestParam("file") MultipartFile file){
//        return Result.getSuccessResult(uploadTool.upload(file));
//    }

//    @DeleteMapping("/upload/del")
//    @ApiOperation("删除文件")
//    public Result uploadDel(@RequestParam String key){
//        return Result.getSuccessResult(uploadTool.delete(key));
//    }

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/sys/user")
    @ApiOperation("添加用户")
    public Result create(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.getMsgResult("添加成功");
    }

    @PutMapping("/sys/user")
    @ApiOperation("更新用户")
    public Result update(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.getMsgResult("更新成功");
    }

    @GetMapping("/sys/user")
    @ApiOperation("查看用户")
    public Result list() {
        return Result.getSuccessResult(sysUserService.list());
    }

    @GetMapping("/user")
    @ApiOperation("查看用户")
    public Result find(@RequestParam("id")Long id) {
        return Result.getSuccessResult(sysUserService.getById(id));
    }
}
