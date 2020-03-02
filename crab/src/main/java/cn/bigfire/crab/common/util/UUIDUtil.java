package cn.bigfire.crab.common.util;

import java.util.UUID;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/10  18:02
 * @ Desc   ：
 */
public class UUIDUtil {
    /**
     * 生成32位UUID  去除“-”  并转换为小写
     *
     * @return 随机生成的 {@code UUID}
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
