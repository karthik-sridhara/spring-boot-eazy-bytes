package com.easybytes.ex8.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class UserSession {

    private final String sessionId;

    public UserSession(){
        sessionId = UUID.randomUUID().toString();
        System.out.println("Creating UserSession Bean with sessionId: " + sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
