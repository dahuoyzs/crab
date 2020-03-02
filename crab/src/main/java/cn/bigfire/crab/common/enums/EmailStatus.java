package cn.bigfire.crab.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  22:46
 * @ Desc   ：
 */


public enum EmailStatus implements IEnum<Integer> {
    UNACTIVE(0,"未激活"),
    ACTIVATED(1,"已激活"),
    EXPIRED(2,"已失效");

    private int value;
    @JsonValue
    private String desc;

    EmailStatus(Integer value,String desc){
        this.value =value;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }
}
/**
 * 2020-2-11
 * 枚举类型，(0,"状态")
 * 数据库存0,1,2,3客户端返回字符串，Server中根据name做比较
 * */