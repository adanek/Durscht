package durscht.model;

import durscht.contracts.logic.IUser;

import java.util.Date;

/**
 * Created by Mark on 04.05.2015.
 */
public class User implements IUser{
    int id;
    String name;
    String email;
    Date date;

    public int getId() {
        return id;
    }

    public String getName(){
     return name;
    }

    public String getEmail(){
        return email;
    }

    public Date getJoinedDate(){
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
