package cn.bigfire.crab.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  10:40
 * @ Desc   ：配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "crab")
public class CrabProperties {
    String baseUrl;
    String sysEmailActiveUrl;
}
