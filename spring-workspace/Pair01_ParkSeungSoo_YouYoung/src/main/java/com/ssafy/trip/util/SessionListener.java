 package com.ssafy.trip.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{
    
    private static final ArrayList<Map<String, HttpSession>> sessions = new ArrayList<Map<String, HttpSession>>(Arrays.asList(new ConcurrentHashMap<>(),new ConcurrentHashMap<>(),new ConcurrentHashMap<>(),new ConcurrentHashMap<>(),new ConcurrentHashMap<>()));
    //중복로그인 지우기
    public synchronized static void getSessionidCheck(String type,String compareId){
        
        int targetIdx = StringToIdx(compareId);
        System.out.println(compareId);
        String result = "";
        for( String key : sessions.get(targetIdx).keySet() ){
            HttpSession value = sessions.get(targetIdx).get(key);
            if(value != null &&  value.getAttribute(type) != null && value.getAttribute(type).toString().equals(compareId) ){
                result =  key.toString();
                removeSessionForDoubleLogin(result);
                break;
            }
        }

    }
    
    private static void removeSessionForDoubleLogin(String userId){        
        System.out.println("remove userId : " + userId);
        int targetIdx = StringToIdx(userId);
        if(userId != null && userId.length() > 0){
            sessions.get(targetIdx).get(userId).invalidate();
            sessions.get(targetIdx).remove(userId);            
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println("세션 생성");
        int targetIdx = StringToIdx(hse.getSession().getId()); 
        sessions.get(targetIdx).put(hse.getSession().getId(), hse.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        int targetIdx = StringToIdx(hse.getSession().getId());
        
        if(sessions.get(targetIdx).get(hse.getSession().getId()) != null){
            sessions.get(targetIdx).get(hse.getSession().getId()).invalidate();
            sessions.get(targetIdx).remove(hse.getSession().getId());    
        }
    }
    
    public static int StringToInt(String target) {
        int sum = 0;
        for (int i = 0; i < target.length(); i++) {
            sum += target.charAt(i);
        }
        return sum;
    }
    
    public static int sumToIdx(int sum) {
        return sum % 5;
    }
    
    public static int StringToIdx(String target) {
        return sumToIdx(StringToInt(target));
    }
}