package controllers.mock;

import durscht.contracts.logic.model.IUser;

import java.util.Date;


public class User implements IUser {

    private final int id;
    private String name;
    private String email;
    private String password;
    private String joinedDate;

    public User(int id, String name, String email, String password, String joinedDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinedDate = joinedDate;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getJoinedDate(){
        return this.joinedDate;
    }

}
