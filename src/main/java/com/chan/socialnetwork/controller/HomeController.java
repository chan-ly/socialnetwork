package com.chan.socialnetwork.controller;

import com.chan.socialnetwork.model.HostHolder;
import com.chan.socialnetwork.model.News;
import com.chan.socialnetwork.model.ViewObject;
import com.chan.socialnetwork.service.NewsService;
import com.chan.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    private List<ViewObject> getNews(int userId, int offset, int limit){
        List<News> newsList = newsService.getLatestNews(userId, offset, limit);
        int localUserId = hostHolder.getUSer() != null ? hostHolder.getUSer().getId() : 0;
        List<ViewObject> vos = new ArrayList<>();
        for (News news: newsList) {
            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(path = {"/"}, method = {RequestMethod.GET, RequestMethod.POST})
    /* Controller返回一个视图名称，Springboot默认在main/resource/templates目录下找该视图*/
    public String home(Model model, @RequestParam(value = "pop", defaultValue = "0") int pop){
        model.addAttribute("pop", pop);
        model.addAttribute("vos", getNews(0, 0, 10));
        return "home";
    }

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId){
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }
}
