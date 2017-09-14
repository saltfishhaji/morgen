package com.mysystem.security;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

//import com.mysystem.dao.UserDao;
import com.mysystem.dao.UserTrailDao;
import com.mysystem.entity.UserTrail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class MyUserDetailService implements UserDetailsService{
	@Autowired
	private UserTrailDao userTrailDao;
	//@Autowired
	//private UserDao userDao;
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException, DataAccessException {   
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();      
        GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN"); 
        GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER"); 
         UserTrail uT=userTrailDao.findByUsername(username);
         if (uT==null) throw new UsernameNotFoundException("no find");  
         //com.mysystem.entity.User u=userDao.findByUtid(uT.getUtid());
		return null;
         
        /*if(u.getPriority()==1){ 
            auths=new ArrayList<GrantedAuthority>(); 
            auths.add(auth1);
            auths.add(auth2);      
        }     
         
        if(u.getPriority()==0){ 
            auths=new ArrayList<GrantedAuthority>(); 
            auths.add(auth1);      
        }  
        User user = new User(username, uT.getPassword(), true, true, true, true, auths); 
        return user;  */
    } 
} 

