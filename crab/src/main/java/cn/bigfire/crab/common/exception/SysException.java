package cn.bigfire.crab.common.exception;

import lombok.Data;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/13  20:10
 * @ Desc   ：系统异常
 */
@Data
public class SysException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg;
    private int code = 500;
    public SysException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public SysException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }
    public SysException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public SysException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
