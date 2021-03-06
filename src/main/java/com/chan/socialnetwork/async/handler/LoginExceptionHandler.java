package com.chan.socialnetwork.async.handler;

import com.chan.socialnetwork.async.EventHandler;
import com.chan.socialnetwork.async.EventModel;
import com.chan.socialnetwork.async.EventType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class LoginExceptionHandler implements EventHandler {

    @Override
    public void doHandle(EventModel eventModel) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", eventModel.get("username"));
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
