package com.chan.socialnetwork.service;

import com.chan.socialnetwork.dao.NewsDAO;
import com.chan.socialnetwork.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDAO newsDAO;

    public News getById(int id){
        return newsDAO.getById(id);
    }

    public List<News> getLatestNews(int userId, int offset, int limit){
        return newsDAO.selectByUserIdAndOffect(userId, offset, limit);
    }
}
