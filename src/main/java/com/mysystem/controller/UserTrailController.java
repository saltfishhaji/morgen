package com.mysystem.controller;
import javax.ejb.Stateless;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.User;
import com.mysystem.entity.UserTrail;
import com.mysystem.service.UserService;
import com.mysystem.service.UserTrailService;
@Controller
@RequestMapping(value="/userTrail")
@Stateless
public class UserTrailController {
	@Autowired
	private UserTrailService userTrailService;//�û�������Ϣ�����
	@Autowired
	private UserService userService;//�û����������	
	@RequestMapping(value = "/hello")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("myIndex");
 
        return modelAndView;
    }
	@RequestMapping(value="/logOut")
	public String logOut(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{ 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		session.setAttribute("uT",null);	
		session.setAttribute("u",null);//���session
		new SecurityContextLogoutHandler().logout(request, response, auth);;
		return "../other";
	}

	
	//�����û��������˻�����
		@RequestMapping(value = "/findByName")
		public ModelAndView findByName(PageModel<UserTrail> pageModel,@RequestParam(value = "username") String username){
			if (pageModel == null) {
				pageModel = new PageModel<UserTrail>();
			}
			pageModel.setUsername(username);
			List<UserTrail> userList = userTrailService.findUnverified(pageModel);//�г�δ��˵��˻�
			pageModel.setDatas(userList);
			pageModel.setTotalrecode(userTrailService.countUnverified(pageModel));//ҳ��
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pageModel", pageModel);
			modelAndView.setViewName("accountVerify");	
			return modelAndView;
		}
		
		//�����˻�����
		@RequestMapping(value = "/rejectUser")
		public ModelAndView rejectUser(PageModel<UserTrail> pageModel,@RequestParam(value = "utid") Integer utid){
			userTrailService.rejectUser(utid);//�޸��˻�����״̬
			List<UserTrail> userList = userTrailService.findUnverified(pageModel);//�г�δ��˵��˻�
			pageModel.setDatas(userList);
			pageModel.setTotalrecode(userTrailService.countUnverified(pageModel));//ҳ��
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pageModel", pageModel);
			modelAndView.setViewName("accountVerify");	
			return modelAndView;
		}
		
		//ͨ���˻�����
		@RequestMapping(value = "/passUser")
		public ModelAndView passUser(PageModel<UserTrail> pageModel,@RequestParam(value = "utid") Integer utid,@RequestParam(value = "priority") Integer priority){
			userTrailService.passUser(utid);//�޸��˻�����״̬
			User us=new User();
			us.setUtid(utid);
			us.setPoint(0);
			us.setPriority(priority);
			userService.add(us);//�����˻���Ϣ
			List<UserTrail> userList = userTrailService.findUnverified(pageModel);//�г�δ��˵��˻�
			pageModel.setDatas(userList);
			pageModel.setTotalrecode(userTrailService.countUnverified(pageModel));//ҳ��
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pageModel", pageModel);
			modelAndView.setViewName("accountVerify");	
			return modelAndView;
		}
		
		
		@RequestMapping(value = "/userTrailList")
		public ModelAndView userTrailList(PageModel<UserTrail> pageModel){
			if (pageModel == null) {
				pageModel = new PageModel<UserTrail>();
			}
			List<UserTrail> userList = userTrailService.findUnverified(pageModel);//�г�δ��˵��˻�
			pageModel.setDatas(userList);
			pageModel.setTotalrecode(userTrailService.countUnverified(pageModel));//ҳ��
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pageModel", pageModel);
			modelAndView.setViewName("accountVerify");	
			return modelAndView;
		}

		
		@RequestMapping(value = "/userList")
		public ModelAndView userList(PageModel<UserTrail> pageModel){
			if (pageModel == null) {
				pageModel = new PageModel<UserTrail>();
			}
			List<UserTrail> userList = userTrailService.findAll(pageModel);//�г�δ��˵��˻�
			pageModel.setDatas(userList);
			pageModel.setTotalrecode(userTrailService.countAll(pageModel));//ҳ��
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pageModel", pageModel);
			modelAndView.setViewName("userList");	
			return modelAndView;
		}
		
		//�û�ע��
		@RequestMapping(value = "/register",method = RequestMethod.POST)
		public String add(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,@RequestParam(value = "tradePass") String tradePass,@RequestParam(value = "email") String email,@RequestParam(value = "age") Integer age){
			UserTrail us=userTrailService.findByUsername(username);//�����û����Ƿ����
			if (us!=null){	//�û����Ѵ���		
				return "../register";
			}
			else {
				UserTrail userTrail=new UserTrail();
				userTrail.setAge(age);
				userTrail.setEmail(email);
				userTrail.setPassword(password);
				String newPass=password;
			       try {  
			            MessageDigest md = MessageDigest.getInstance("MD5");  
			            md.update(tradePass.getBytes());  
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
			            newPass=buf.toString();  

			        } catch (NoSuchAlgorithmException e) {  
			            e.printStackTrace();   
			     }
				userTrail.setTradePass(newPass);
				userTrail.setUsername(username);
				userTrailService.register(userTrail);//�����û�������Ϣ
				return "../login";	
			}		
		}
	
		@RequestMapping(value = "/editUser",method = RequestMethod.POST)
		public String edit(@RequestParam(value = "utid") Integer utid,@RequestParam(value = "username") String username,@RequestParam(value = "email") String email,@RequestParam(value = "age") Integer age,HttpSession session){
			UserTrail userTrail=(UserTrail)session.getAttribute("uT");;
			userTrail.setAge(age);
			userTrail.setEmail(email);
			userTrailService.editUser(userTrail);//�޸ĸ�����Ϣ
			session.setAttribute("uT", userTrail);//����session
			return "/infoManager";
		}


}
