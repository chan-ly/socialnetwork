package com.chan.socialnetwork.service;

import com.chan.socialnetwork.dao.LoginTicketDAO;
import com.chan.socialnetwork.dao.UserDAO;
import com.chan.socialnetwork.model.LoginTicket;
import com.chan.socialnetwork.model.User;
import com.chan.socialnetwork.util.SocialnetworkUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicket;

    public Map<String, Object> register(String userName, String passWord){
        Map<String, Object> map = new HashMap<>();

        if(StringUtils.isBlank(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(passWord)){
            map.put("msg", "密码不能为空");
            return map;
        }
        User user = userDAO.selectByName(userName);
        if(user != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        user = new User();
        user.setName(userName);
        /*UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的形式*/
        user.setPassword(SocialnetworkUtil.MD5(passWord + user.getSalt()));
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",1));
        userDAO.addUser(user);

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    public Map<String, Object> login(String userName, String password){
        Map<String, Object> map = new HashMap<>();

        if(StringUtils.isBlank(userName)){
            map.put("msg", "用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg", "密码不能为空");
        }
        User user = userDAO.selectByName(userName);
        if(user == null){
            map.put("msg", "用户名不存在");
            return map;
        }
        if(!user.getPassword().equals((SocialnetworkUtil.MD5(password + user.getSalt())))){
            map.put("msg", "密码错误");
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    public String addLoginTicket(int userId){
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicket.addLoginTicket(ticket);
        return ticket.getTicket();
    }

    public User getUser(int id){
        return userDAO.selectById(id);
    }
}
