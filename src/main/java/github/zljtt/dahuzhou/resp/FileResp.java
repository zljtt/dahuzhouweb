package github.zljtt.dahuzhou.resp;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;

public class FileResp {

    String content;

    FileStatus status;

    String message;

    public String getContent() {
        return content;
    }

    public FileResp setContent(String jsonString) {
        this.content = jsonString;
        return this;
    }

    public FileStatus getFileStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileResp setFileStatus(FileStatus status) {
        this.status = status;
        this.message = status.message;
        return this;
    }

    public FileResp() {
        this.status = FileStatus.NOT_FOUND;
        this.content = "";
    }

    @Override
    public String toString() {
        return "FileResp{" +
                "content='" + content + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public enum FileStatus {
        CREATE("成功创建文件", HttpStatus.OK),
        OVERRIDE("成功覆盖文件", HttpStatus.OK),
        NOT_FOUND("文件不存在", HttpStatus.OK),
        DELETE("成功删除文件", HttpStatus.OK),
        FOUND("成功读取文件", HttpStatus.OK),
        IO_EXCEPTION("文件存储失败", HttpStatus.INTERNAL_SERVER_ERROR),
        WRONG_FORMAT("文件格式错误", HttpStatus.NOT_ACCEPTABLE);

        public String getMessage() {
            return message;
        }

        final String message;

        public HttpStatus getHttpStatus() {
            return status;
        }

        final HttpStatus status;

        FileStatus(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }
    }

}
