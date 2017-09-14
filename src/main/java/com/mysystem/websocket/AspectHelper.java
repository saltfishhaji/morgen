package com.mysystem.websocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.UserTrail;
@Component
@Aspect
public class AspectHelper {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	public AspectHelper(){System.out.println("*******");};

	
	
	
	@Before("execution(* com.mysystem.controller.CartController.buy(..))")
	public void before(JoinPoint point) throws Throwable  
	{
		
		HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
	    HttpSession session =request.getSession(); 
		UserTrail tmp2=(UserTrail) session.getAttribute("uT");
		Object[] args = point.getArgs();
		PageModel<UserTrail> pageModel=(PageModel<UserTrail>) args[0];
		String x=(String) args[1];
		String tps=x;
		try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(tps.getBytes());  
	        byte b[] = md.digest();  
	        int i;  
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < b.length; offset++) {  
	            i = b[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	         }  
	         x=buf.toString();  
        } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();   
	    }
	    String y=tmp2.getTradePass();
	    logger.info("Start Transaction!");
		if (x.equalsIgnoreCase(y))
		{

			logger.info("TradePassword is right!");
			session.setAttribute("isRight", true);
			return;
		}
		session.setAttribute("isRight", false);
		logger.info("TradePassword is wrong!");
		return;
    } 
	
	 @AfterReturning("execution(* com.mysystem.controller.CartController.buy(..))")
	    public void after(){
		 logger.info("Transaction end!");
	    }
	

}
