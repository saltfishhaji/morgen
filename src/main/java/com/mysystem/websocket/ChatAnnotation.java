package com.mysystem.websocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysystem.websocket.GetHttpSessionConfigurator;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.mysystem.entity.Book;
import com.mysystem.entity.UserTrail;
  

@ServerEndpoint(value = "/websocket/chat",configurator=GetHttpSessionConfigurator.class)  
public class ChatAnnotation {  
	MemCachedClient client;
	SockIOPool pool;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String username;
    static Map<String,Session> sessionMap = new Hashtable<String,Session>();
    
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
    	HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        UserTrail tmp=(UserTrail)httpSession.getAttribute("uT");
        username=tmp.getUsername();
   
        System.out.println(username);
        List<String> ul=new ArrayList<String>();
		String key =  "UserList";
		String js="";
		try {
			getClient();
			js=(String) client.get(key);
			try
			{
			   //将字符串转换成jsonObject对象
				if (js!=null)
				{
					 JSONObject jsonObj=new JSONObject(js);
					 JSONArray userList = jsonObj.getJSONArray("users");
					 for (int i=0;i<userList.length();i++)
					 {
						 String obj=userList.getString(i);
						 System.out.println(obj);
						 ul.add(obj);
					 }		
				}


			 }
			  catch (JSONException e)
			  {
				  e.printStackTrace();
			  };
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ul.isEmpty() ) {
			JSONObject json=new JSONObject();			
			try {
				ul.add(username);
				HashSet h = new HashSet(ul);
				ul.clear();
				ul.addAll(h);
				json.put("users", ul);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("New user");

		}else{
			JSONObject json=new JSONObject();			
			try {
				ul.add(username);
				HashSet h = new HashSet(ul);
				ul.clear();
				ul.addAll(h);
				json.put("users", ul);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("Get users from MemCached");

		}
		System.out.println(ul);
        session.getUserProperties().put("un", username);
    	sessionMap.put(session.getId(), session);
    	broadcastAll("userList",ul.toString());
    }

    @OnMessage
    public void onMessage(String unscrambledWord, Session session) {
    	if ( session.getUserProperties().get("un")!=null)
    	{
        	String tmp=(String) session.getUserProperties().get("un");
        	unscrambledWord=tmp+": "+unscrambledWord;
    	}

    	broadcastAll("message",unscrambledWord);
    }
    /**
     * 广播给所有人
     * @param message
     */
    public static void broadcastAll(String type,String message){
        Set<Map.Entry<String,Session>> set = sessionMap.entrySet();
        for(Map.Entry<String,Session> i: set){
            try {
            	System.out.println(type);
            	i.getValue().getBasicRemote().sendText("{type:'"+type+"',text:'"+message+"'}");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        username=(String) session.getUserProperties().get("un");
    	 List<String> ul=new ArrayList<String>();
 		String key =  "UserList";
 		String js="";
 		try {
 			getClient();
 			js=(String) client.get(key);
 			try
 			{
 			   //将字符串转换成jsonObject对象
 				if (js!=null)
 				{
 					 JSONObject jsonObj=new JSONObject(js);
 					 JSONArray userList = jsonObj.getJSONArray("users");
 					 for (int i=0;i<userList.length();i++)
 					 {
 						 String obj=userList.getString(i);
 						 System.out.println(obj);
 						 ul.add(obj);
 					 }		
 				}


 			 }
 			  catch (JSONException e)
 			  {
 				  e.printStackTrace();
 			  };
 		  
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
		JSONObject json=new JSONObject();			
		try {
				ul.remove(username);
				HashSet h = new HashSet(ul);
				ul.clear();
				ul.addAll(h);
				json.put("users", ul);	    
			} catch (JSONException e) {
				e.printStackTrace();
		}
		client.set(key, json.toString());

 		
 		System.out.println(ul);
 		broadcastAll("userList",ul.toString());
    	sessionMap.remove(session.getId());
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
    
    @OnError
    public void error(Session session, Throwable throwable){
        username=(String) session.getUserProperties().get("un");
    	 List<String> ul=new ArrayList<String>();
 		String key =  "UserList";
 		String js="";
 		try {
 			getClient();
 			js=(String) client.get(key);
 			try
 			{
 			   //将字符串转换成jsonObject对象
 				if (js!=null)
 				{
 					 JSONObject jsonObj=new JSONObject(js);
 					 JSONArray userList = jsonObj.getJSONArray("users");
 					 for (int i=0;i<userList.length();i++)
 					 {
 						 String obj=userList.getString(i);
 						 System.out.println(obj);
 						 ul.add(obj);
 					 }		
 				}


 			 }
 			  catch (JSONException e)
 			  {
 				  e.printStackTrace();
 			  };
 		  
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
		JSONObject json=new JSONObject();			
		try {
				ul.remove(username);
				HashSet h = new HashSet(ul);
				ul.clear();
				ul.addAll(h);
				json.put("users", ul);	    
			} catch (JSONException e) {
				e.printStackTrace();
		}
		client.set(key, json.toString());

 		
 		System.out.println(ul);
 		broadcastAll("userList",ul.toString());
    	sessionMap.remove(session.getId());
        System.err.println("session "+session.getId()+" error:"+throwable);
    } 
    private void getClient() {
		client = new MemCachedClient();
		String[] addr = { "127.0.0.1:11211" };
		Integer[] weights = { 3 };
		pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
		System.out.println("getClient");
	}
 
} 
