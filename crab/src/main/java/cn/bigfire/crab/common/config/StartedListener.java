package cn.bigfire.crab.common.config;

import cn.bigfire.crab.common.config.CrabProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  10:30
 * @ Desc   ：
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent>{

    @Autowired
    CrabProperties crabProperties;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        this.printStartInfo();
    }


    private void printStartInfo() {
        String baseUrl = crabProperties.getBaseUrl();
        log.info("Crab started at         {}", baseUrl);
        log.debug("Crab doc was enable at  {}/swagger-ui.html", baseUrl);
    }
}
