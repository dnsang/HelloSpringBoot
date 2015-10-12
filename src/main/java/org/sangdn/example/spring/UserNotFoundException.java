package org.sangdn.example.spring;

/**
 * Created by sangdn on 10/12/15.
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super();
    }
    public  UserNotFoundException(Integer uid){
        super("uid: " + uid + " not found");
    }
}
