package com.jsongrts.authstudy;

/**
 * Created by jsong on 2/10/16.
 */
public class AuthStudyException extends RuntimeException {
    public AuthStudyException(final Throwable cause) {
        super(cause);
    }

    public AuthStudyException(final String msg) {
        super(msg);
    }
}
