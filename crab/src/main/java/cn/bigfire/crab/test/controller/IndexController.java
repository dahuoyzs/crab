package cn.bigfire.crab.test.controller;

import cn.bigfire.crab.common.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/11  10:41
 * @ Desc   ：
 */

@RestController
@RequestMapping("/")
@Api(tags = "根路径相关接口")
public class IndexController {

    @GetMapping("/")
    @ApiOperation("访问根路径是的返回信息")
    public String index(){
        return "IndexController.index()";
    }

    @GetMapping("/table/list")
    @ApiOperation("返回一个list")
    public Result list(){
        ArrayList<Map> arrayList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            HashMap<String,Object> map = new HashMap();
            map.put("id",i+1);
            map.put("title","title"+(i+1));
            map.put("author","author"+(i+1));
            map.put("display_time", LocalDateTime.now());
            map.put("pageviews", (i+1)*100);
            arrayList.add(map);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("total",arrayList.size());
        hashMap.put("items",arrayList);
        return Result.getCodeResult(20000,"请求成功",hashMap);
    }

}
