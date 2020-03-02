package cn.bigfire.crab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/10  14:26
 * @ Desc   ：
 */

@MapperScan("cn.bigfire.crab.*.mapper")
@SpringBootApplication
public class CrabApp {
    public static void main(String[] args) {
        SpringApplication.run(CrabApp.class,args);
    }
}
