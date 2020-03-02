package cn.bigfire.crab.common.config;

import cn.bigfire.crab.common.tool.upload.UploadTool;
import cn.bigfire.crab.common.util.Console;
import cn.bigfire.crab.common.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/9  18:24
 * @ Desc   ：静态文件访问
 */
@Slf4j
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + Constant.DIR_UPLOAD);
    }

}
