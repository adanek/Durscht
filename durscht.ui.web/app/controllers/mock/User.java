package controllers.mock;

import java.util.Date;

public class User {

    private final int id;
    private String name;
    private String email;
    private String password;
    private Date joinedDate;

    public User(int id, String name, String email, String password, Date joinedDate){
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

    public Date getJoinedDate(){
        return this.joinedDate;
    }

    public void setName(){
        this.name = name;
    }

    public void setEmail(){
        this.email = email;
    }

    public void setPassword(){
        this.password = password;
    }

}
