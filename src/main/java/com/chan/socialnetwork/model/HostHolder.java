package com.chan.socialnetwork.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUSer(){return users.get();}

    public void setUsers(User user){users.set(user);}

    public void clear(){users.remove();}
}
