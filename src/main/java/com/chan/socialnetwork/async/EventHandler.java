package com.chan.socialnetwork.async;

import org.springframework.stereotype.Component;

import java.util.List;

/*处理事件的接口*/
@Component
public interface EventHandler {
    /*不同的实现类处理事件的方式不同*/
    void doHandle(EventModel eventModel);
    /*获取该handler可以处理的所有事件类型*/
    List<EventType> getSupportEventTypes();
}
