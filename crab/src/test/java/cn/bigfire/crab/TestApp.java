package cn.bigfire.crab;

import cn.bigfire.crab.common.enums.MenuType;
import cn.bigfire.crab.common.util.PathUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/1/10  15:55
 * @ Desc   ：
 */
@Slf4j
public class TestApp {
    @Test
    public void test01() throws Exception {
        String path1 = "d:/a/b/c/d";
        String path2 = "d:/a/b/c/d/";
        String path3 = "d:/a/b/c/d/a.png";

        System.out.println(PathUtil.toMonthPath(path1));
        System.out.println(PathUtil.toMonthPath(path2));
        System.out.println(PathUtil.toMonthPath(path3));

        System.out.println(PathUtil.toDayPath(path1));
        System.out.println(PathUtil.toDayPath(path2));
        System.out.println(PathUtil.toDayPath(path3));
    }

    @Test
    public void test02() throws Exception {
        System.out.println(System.getProperties().getProperty("user.home"));
        System.out.println(System.getProperties().getProperty("user.dir"));
    }

    @Test
    public void test03() throws Exception {
        String test = "D:\\desktop\\文本\\源码\\mycode\\crab\\crab/data/upload/2020/2/";
        System.out.println(PathUtil.toMonthPath(test));
    }

    /**
     * 测试去哪网开源项目Yapi注水
     */
    @Test
    public void test04() throws Exception {
        for (int i = 1; i < 100; i++) {
            String username = "【测试】";
            String api = "http://yapi.demo.qunar.com/api/user/reg";
            HashMap<String, Object> map = new HashMap<>();
            map.put("email", username + i + "@qq.com");
            map.put("username", username + i);
            map.put("password", "123456");
            System.out.println(HttpUtil.post(api, map));
        }
    }

    @Test
    public void test05() throws Exception {
        System.out.println(MenuType.CATALOG.getValue());
    }

    @Test
    public void test06() throws Exception {
        testSysOut();
    }

    @Test
    public void test07() throws Exception {
        testLog();
    }

    private static void testSysOut(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000; i++) {
            System.out.println(i);
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("SysOut耗时:"+time);
    }
    private static void testLog(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000; i++) {
           log.debug("{}",i);
        }
        long time = System.currentTimeMillis()-start;
        log.debug("Log耗时:{}",time);
    }
}
