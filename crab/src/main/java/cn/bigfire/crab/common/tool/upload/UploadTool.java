package cn.bigfire.crab.common.tool.upload;

import cn.bigfire.crab.common.util.Console;
import cn.hutool.core.util.StrUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/11  11:07
 * @ Desc   ：文件上传接口
 */
@Component
public class UploadTool{

    private final ArrayList<UpLoader> upLoaders = new ArrayList<>();

    public UploadTool(ApplicationContext applicationContext){
        Map map = applicationContext.getBeansOfType(UpLoader.class);
        Console.printMap(map);
    }
}
