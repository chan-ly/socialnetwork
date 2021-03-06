package com.chan.socialnetwork.dao;

import com.chan.socialnetwork.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = "login_ticket";
    String INSERT_FIELDS = "user_id, ticket, expired, status ";
    String SELECT_FIELDS = "id, user_id, ticket, expired, status ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{userId}, #{ticket}, #{expired}, #{status})" })
    int addLoginTicket(LoginTicket loginTicket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket = #{ticket}"})
    LoginTicket selectByTicket(String ticket);
}
