package cn.bigfire.crab.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：一个线程安全的缓存Map，支持链式put调用
 * 封装ConcurrentHashMap 线程安全下的HashMap 并支持设置失效时间,单位为毫秒，支持链式put
 * 可以当作一个没有持久化功能的假redis使用
 */
public class CacheMap extends ConcurrentHashMap<String, Object> {
    @Override
    public CacheMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public CacheMap put(String key, Object value, long expire) {
        super.put(key, value);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        };
        timer.schedule(timerTask,expire);
        return this;
    }
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
