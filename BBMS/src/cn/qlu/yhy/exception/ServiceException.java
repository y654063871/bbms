package cn.qlu.yhy.exception;

public class ServiceException extends BaseException {

    private static final long serialVersionUID = -2769900853379424512L;

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(int errorCode) {
        super(errorCode);
    }

    public ServiceException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ServiceException(int errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
    }
}
