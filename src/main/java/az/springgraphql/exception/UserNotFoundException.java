package az.springgraphql.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String msg){
        super(msg);
    }
}
