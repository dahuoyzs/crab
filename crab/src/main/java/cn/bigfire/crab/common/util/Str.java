package cn.bigfire.crab.common.util;
import java.util.stream.Stream;


/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/19  18:13
 * @ Desc   ：StringBuilder的增强,
 * 项目只直接用+=的方式拼接字符串式非常低效的，应该使用StringBuilder来优化字符串的拼接
 * 但是StringBuilder会让简单的字符串拼接变得极为复杂，所以这个SB类就是简化StringBuilder的
 * 当然这个类是线程不安全的
 */

public class Str {
    private StringBuilder sb;
    public Str(){
        this.sb = new StringBuilder();
    }//effect java建议聚合而非继承

    public Str(Object ...s){
        this.sb = new StringBuilder();
        Stream.of(s).forEach(str-> this.sb.append(str));
    }
    public Str add(Object ...s){
        Stream.of(s).forEach(str-> this.sb.append(str));
        return this;
    }
    public Str ls(){
        System.out.println(this);
        return this;
    }
    public static Str of(Object ...s){
        return new Str(s);
    }
    public Str reverse(){
        this.sb.reverse();
        return this;
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
