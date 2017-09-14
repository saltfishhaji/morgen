package com.mysystem.serviceImpl;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysystem.PageModel.PageModel;
import com.mysystem.dao.CartDao;
import com.mysystem.entity.Cart;
import com.mysystem.entity.temp;
import com.mysystem.service.CartService;
@Service(value="cartService")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDao cartDao;
	public void add(Cart ct) 
	{
		cartDao.add(ct);		
	}	
	public List<Cart> findAll(PageModel<Cart> pageModel)
	{
		return cartDao.findAll(pageModel);
	}
	public Integer countAll(PageModel<Cart> pageModel)
	{
		return cartDao.countAll(pageModel);
	}
	public Cart findById(Integer cartId)
	{
		return cartDao.findById(cartId);
	}
	public void delete(Integer cartId)
	{
		cartDao.delete(cartId);
	}
	public double getCartSum(Integer uid)
	{
		return cartDao.getCartSum(uid);
	}
	public void receiver(HttpSession session1,String ss)
	{
		try {
			ActiveMQConnectionFactory connectionFactory =  
	            new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,  
	                "tcp://127.0.0.1:61616");
			connectionFactory.setTrustAllPackages(true);
	        Connection connection ;
			connection = connectionFactory.createConnection();
			 connection.start();  

		        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE); 

		        Destination destination = session.createQueue("my-queue"+session1);  


		        MessageConsumer consumer = session.createConsumer(destination);  
		        while (true) {
		        	ObjectMessage textMessage = (ObjectMessage) consumer.receive(1000);
		            if(textMessage != null){	            	
		            	temp x=(temp)textMessage.getObject();
		                System.out.println("收到的消息:"+ss+" &" + x.getTime()+ss.equals(x.getTime()));
		                if (ss.equals(x.getTime()))
		                {
		                	buy(x);
		                }
		                
		               
		            }else {
		                break;
		            }
		        }  
		        session.close();  
		        connection.close();  
		} catch (JMSException e) {
			System.out.println("error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void buy(temp p)
	{  
		cartDao.buy(p);
	}
	
	public Integer isExist(Cart cart)
	{
		return cartDao.isExist(cart);
	}
	public void updateCart(Cart cart)
	{
		cartDao.updateCart(cart);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cart> findAllBuy(PageModel<Cart> pageModel) {
		return cartDao.findAllBuy(pageModel);
	}
	public double getBuySum(Integer uid) {
		return cartDao.getBuySum(uid);
	}
	public Integer countAllBuy(PageModel<Cart> pageModel) {
		return cartDao.countAllBuy(pageModel);
	}
	public Cart findByCartId(Integer cartId)
	{
		return cartDao.findByCartId(cartId);
	}
	
	public void editCart(Cart cart){
		cartDao.editCart(cart);
	}
			
}
