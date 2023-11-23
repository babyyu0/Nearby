/* package com.ssafy.trip.util;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{

    //name : sessionID
    private static final Map<String, HttpSession> sessionID = new ConcurrentHashMap<>();
    
    public synchronized static void getSessionidCheck(String compareId){
        
        //만약 세션이 이미 존재한다면
        if (sessionID.containsKey(compareId)) {
            //sessions 에서 sessionID에 있는 친구를 invalidate 해준뒤
            sessionID.get(compareId).invalidate();
            //Map에서 제거해준다
            sessionID.remove(compareId);
        }
    }
    
    public static void addSessionId(String userId,HttpSession session) {
        sessionID.put(userId, session);
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {

        if (sessionID.containsKey(hse.getSession().getAttribute("id"))) {
            sessionID.get(hse.getSession().getAttribute("id")).invalidate();
            sessionID.remove(hse.getSession().getAttribute("id"));
        }
    }

}

 */