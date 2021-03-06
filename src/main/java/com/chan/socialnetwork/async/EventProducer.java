package com.chan.socialnetwork.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*事件生产者，负责把事件放入消息队列*/
@Component
public class EventProducer {
    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

}
