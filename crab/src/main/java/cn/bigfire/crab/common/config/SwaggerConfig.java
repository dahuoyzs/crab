package cn.bigfire.crab.common.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
*
* */

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/8  13:35
 * @ Desc   ：Swagger2配置
 * @ local_url ：http://localhost/crab/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.bigfire.crab"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("crab")
            .description("crab接口文档")
            .version("1.0.0")
            .build();
    }
}