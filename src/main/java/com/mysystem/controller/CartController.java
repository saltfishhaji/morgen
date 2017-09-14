package com.mysystem.controller;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.Book;
import com.mysystem.entity.Cart;
import com.mysystem.entity.User;
import com.mysystem.entity.UserPoint;
import com.mysystem.entity.UserTrail;
import com.mysystem.entity.temp;
import com.mysystem.service.BookService;
import com.mysystem.service.CartService;
import com.mysystem.service.UserService;
import com.mysystem.service.UserTrailService;

import javax.ejb.*;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
@Controller
@RequestMapping(value="/cart")
@Stateful
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserTrailService userTrailService;//用户申请信息服务层
	
	@RequestMapping(value = "/showCart")
	public ModelAndView showCart(PageModel<Cart> pageModel,HttpSession session){
		if (pageModel == null) {
			pageModel = new PageModel<Cart>();
		}
		if (session.getAttribute("u")==null)
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  
			UserTrail uT=userTrailService.findByUsername(userDetails.getUsername());
	        User u=new User();
	        u=userService.findByUtid(uT.getUtid());
	        session.setAttribute("u",u);
	        session.setAttribute("uT",uT);
		}
		User tmp=(User) session.getAttribute("u");
		pageModel.setUid(tmp.getUid());
		List<Cart> cartList = cartService.findAll(pageModel);
		double sumall=cartService.getCartSum(tmp.getUid());
		pageModel.setDatas(cartList);
		pageModel.setTotalrecode(cartService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("sumall", sumall);
		modelAndView.setViewName("myCart");	
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/delete")
	public ModelAndView putCart(PageModel<Cart> pageModel,@RequestParam(value = "cartId") Integer cartId,HttpSession session){
		Cart c=cartService.findById(cartId);
		//Book b=bookService.findById(c.getBookId());
		cartService.delete(cartId);
		User tmp=(User) session.getAttribute("u");
		pageModel.setUid(tmp.getUid());
		double sumall=cartService.getCartSum(tmp.getUid());
		List<Cart> cartList = cartService.findAll(pageModel);
		pageModel.setDatas(cartList);
		pageModel.setTotalrecode(cartService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("sumall", sumall);
		modelAndView.setViewName("myCart");		
		return modelAndView;
	}
	

	
	@RequestMapping(value = "/buy")

	public ModelAndView buy(PageModel<Cart> pageModel,@RequestParam(value = "tps") String tps,HttpSession session){
		User tmp=(User) session.getAttribute("u");
		UserTrail tmp2=(UserTrail) session.getAttribute("uT");
		int u=tmp.getUid();
		double sumall=cartService.getCartSum(u);
		double oldP=tmp.getPoint();
		UserPoint up=new UserPoint();
		up.setUid(u);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String time = dateFormat.format( now ); 
		temp p=new temp();
		p.setUid(u);
		p.setTime(time);
		String x=tps;
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

		if (x.equals(y) && oldP>=sumall && tmp.getPoint()>0)
		{
			System.out.println("here");
			up.setPoint(oldP-sumall);	
			userService.updatePoint(up);
	
			// ConnectionFactory ：连接工厂，JMS 用它创建连接  
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(  
	                        ActiveMQConnection.DEFAULT_USER,  
	                        ActiveMQConnection.DEFAULT_PASSWORD,  
	                        "tcp://127.0.0.1:61616"); 
	        connectionFactory.setTrustAllPackages(true);
	        Connection connection;
			try {
				connection = connectionFactory.createConnection();
				connection.start();  
		        Session session1 = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);    
		        Destination destination = session1.createQueue("my-queue"+session.toString());  
		        MessageProducer producer = session1.createProducer(destination);  
		        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
		        sendMsg(session1,p,producer);  
		        session1.commit();  
		        connection.close();
		        cartService.receiver(session,p.getTime());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
			}  	        	
			
		}
		pageModel.setUid(tmp.getUid());
		User n=userService.findByUtid(tmp2.getUtid());
		session.setAttribute("u", n);
		List<Cart> cartList = cartService.findAll(pageModel);
		pageModel.setDatas(cartList);
		pageModel.setTotalrecode(cartService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("sumall", sumall);
		modelAndView.setViewName("myCart");		
		return modelAndView;
	}
	public static void sendMsg(Session session,temp p, MessageProducer producer) throws JMSException {  
           //创建一条文本消息  
           //TextMessage message = session.createTextMessage("hello");
		ObjectMessage message =(ObjectMessage) session.createObjectMessage(p); 
		message.setStringProperty("Selector", p.getTime());
           //通过消息生产者发出消息  
           producer.send(message); 
           System.out.println(p);
           System.out.println("send"+message);  
   }  
	
	@RequestMapping(value = "/showBuy")
	public ModelAndView showBuy(PageModel<Cart> pageModel,HttpSession session){
		if (pageModel == null) {
			pageModel = new PageModel<Cart>();
		}
		if (session.getAttribute("u")==null)
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  
			UserTrail uT=userTrailService.findByUsername(userDetails.getUsername());
	        User u=new User();
	        u=userService.findByUtid(uT.getUtid());
	        session.setAttribute("u",u);
	        session.setAttribute("uT",uT);
		}
		User tmp=(User) session.getAttribute("u");
		pageModel.setUid(tmp.getUid());
		Integer x=tmp.getUid();
		List<Cart> cartList = cartService.findAllBuy(pageModel);
		double sumall=cartService.getBuySum(x);
		pageModel.setDatas(cartList);
		pageModel.setTotalrecode(cartService.countAllBuy(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("sumall", sumall);
		modelAndView.setViewName("myOrder");	
		return modelAndView;
	}
	
	@RequestMapping(value = "/changeCart")
	public ModelAndView putCart(PageModel<Cart> pageModel,@RequestParam(value = "cartId") Integer cartId,@RequestParam(value = "bookId") Integer bookId,@RequestParam(value = "bookNum") Integer bookNum,HttpSession session){
		User tmp=(User) session.getAttribute("u");
		Book b=bookService.findById(bookId);
		double sum=b.getPrice()*bookNum;
		Integer n=b.getStock();
		Cart cart=cartService.findByCartId(cartId);
		b.setStock(n+cart.getNum());
		Integer anum=b.getStock()-bookNum;
		b.setStock(anum);
		cart.setNum(bookNum);
		cart.setSum(sum);
		bookService.updateStock(b);
		cartService.editCart(cart);
		pageModel.setUid(tmp.getUid());
		Integer x=tmp.getUid();
		double sumall=cartService.getCartSum(x);
		List<Cart> cartList = cartService.findAll(pageModel);
		pageModel.setDatas(cartList);
		pageModel.setTotalrecode(cartService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("sumall", sumall);
		modelAndView.setViewName("myCart");	;	
		return modelAndView;
	}
	
}
