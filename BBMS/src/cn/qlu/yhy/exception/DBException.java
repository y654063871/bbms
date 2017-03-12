package cn.qlu.yhy.exception;

public class DBException extends BaseException {

    private static final long serialVersionUID = -6553325548199138216L;

    public DBException(String errorMessage) {
        super(errorMessage);
    }

    public DBException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public DBException(int errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
    }
}
