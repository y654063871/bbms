package cn.qlu.yhy.exception;




public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5521119352877234983L;

    private int errorCode;

    public BaseException(int errorCode) {
        super("");
        setErrorCode(errorCode);
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        setErrorCode(errorCode);
    }

    public BaseException(int errorCode, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        setErrorCode(errorCode);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}