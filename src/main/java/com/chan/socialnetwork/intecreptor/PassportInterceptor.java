package com.chan.socialnetwork.intecreptor;

import com.chan.socialnetwork.dao.LoginTicketDAO;
import com.chan.socialnetwork.dao.UserDAO;
import com.chan.socialnetwork.model.HostHolder;
import com.chan.socialnetwork.model.LoginTicket;
import com.chan.socialnetwork.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private HostHolder hostHolder;

    private static final Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("拦截器：判断用户登录信息");
        String ticket = null;
        if(httpServletRequest.getCookies() != null){
            for (Cookie cookie: httpServletRequest.getCookies()) {
                if(cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if(ticket != null){
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            if(loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0){
                return true;
            }else {
                User user = userDAO.selectById(loginTicket.getId());
                hostHolder.setUsers(user);
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && hostHolder.getUSer() != null){
            modelAndView.addObject(hostHolder.getUSer());
            hostHolder.clear();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
