package cn.qlu.yhy.exception;

import java.util.Map;

public class ParamException extends BaseException {

    private static final long serialVersionUID = 3518461700906778953L;

    private Map<String, String> errorMsgMap;

    public ParamException(String errorMessage) {
        super(errorMessage);
    }

    public ParamException(int errorCode, Map<String, String> errorMsgMap) {
        super(errorCode);
        setErrorMsgMap(errorMsgMap);
    }

    public ParamException(int errorCode, String errorMessage, Map<String, String> errorMsgMap) {
        super(errorCode, errorMessage);
        setErrorMsgMap(errorMsgMap);
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}