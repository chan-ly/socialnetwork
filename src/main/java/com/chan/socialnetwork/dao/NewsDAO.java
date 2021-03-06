package com.chan.socialnetwork.dao;

import com.chan.socialnetwork.model.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsDAO {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = " title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    //Java注解的方式
    @Select({"selet ", INSERT_FIELDS, " from ", TABLE_NAME, " where id = #{id}"})
    News getById(int id);

    //xml
    //从首页中选择一批咨询（分页处理）不在通过注解方式写而是通过xml来写该方法，xml放下相同的包目录下并且自定义名字一样
    //与接口同名的xml文件要放在，相同的包的相同的目录下。 xml里可以做一些复杂的处理的
    //socialnetwork\src\main\resources\com.chan.socialnetwork\NewsDAO.xml
    List<News> selectByUserIdAndOffect(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
}
