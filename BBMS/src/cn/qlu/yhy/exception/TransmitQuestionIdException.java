package cn.qlu.yhy.exception;

public class TransmitQuestionIdException extends RuntimeException {

    /**
     *Let create question's questionId transmit to controller
     */
    private static final long serialVersionUID = 4956858400221008537L;

    private String message;
    @Override

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransmitQuestionIdException(String message) {
        super();
        this.message = message;
    }
}
