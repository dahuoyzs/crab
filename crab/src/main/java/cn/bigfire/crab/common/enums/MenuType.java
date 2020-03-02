package cn.bigfire.crab.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo
 * @ Date   ：2020/2/11  20:37
 * @ Desc   ：
 */
public enum MenuType implements IEnum<Integer> {
    //0目录,1菜单,2按钮,3外链

    CATALOG(0),

    MENU(1),

    ACTION(2),

    OUTLINK(3);

    @EnumValue
    private final Integer value;

    MenuType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
