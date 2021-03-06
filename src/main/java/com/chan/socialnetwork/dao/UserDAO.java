package com.chan.socialnetwork.dao;

import com.chan.socialnetwork.model.User;
import org.apache.ibatis.annotations.*;

/*具体实现类由Mybatis创建*/
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = "name, password, salt, head_url";
    String SELECT_FIELDS = "id, name, password, salt, head_url";

    @Insert({"insert into ", TABLE_NAME , "(", INSERT_FIELDS, ") values (#{name}, #{password}, #{salt}, #{headUrl})" })
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, "from ", TABLE_NAME, "where name = #{name}"})
    User selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id = #{id}"})
    User selectById(int id);
}
