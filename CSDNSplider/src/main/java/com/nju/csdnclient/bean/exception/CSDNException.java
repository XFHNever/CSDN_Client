package com.nju.csdnclient.bean.exception;

/**
 * Created by never on 2014/8/25.
 */
public class CSDNException extends Exception {
    public CSDNException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CSDNException(Throwable cause) {
        super(cause);
    }

    public CSDNException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSDNException(String message) {
        super(message);
    }

    public CSDNException() {
    }
}
