package com.chan.socialnetwork.async;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
    /*处理的事件类型*/
    private EventType eventType;
    /*触发者*/
    private int actorId;
    /*触发对象*/
    private int entityId;
    private int entityType;
    /*触发对象拥有者，拥有者会收到通知*/
    private int entityOwnerId;

    /*触发现场的信息保存*/
    private Map<String, String> exts = new HashMap<>();

    public EventModel() {
    }

    public EventModel(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    /*使用return this可以让代码执行链路化操作*/
    public EventModel set(String key, String value){
        exts.put(key, value);
        return this;
    }
    public String get(String key){
        return exts.get(key);
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public void setExts(Map<String, String> exts) {
        this.exts = exts;
    }
}
