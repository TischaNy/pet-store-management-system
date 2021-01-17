package restfullResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import java.util.List;

public class ApiController<T> {
    private T data;
    private List<String> messages;
    private String message;
    private HttpStatus statusCode;

    public ApiController(T data, String message, HttpStatus statusCode) throws Exception {
        this.data = data;
        if(data.getClass().getAnnotation(Entity.class) == null){
            throw new Exception("An error occured while instantiating object");
        }
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiController(String message, HttpStatus statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiController(List<String> messages, HttpStatus statusCode){
        this.messages = messages;
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
