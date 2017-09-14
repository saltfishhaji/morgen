package com.mysystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.io.ByteArrayOutputStream; 
import javax.xml.namespace.QName;
import javax.xml.ws.Service; 
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.openejb.server.httpd.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.Book;
import com.mysystem.entity.Cart;
import com.mysystem.entity.User;
import com.mysystem.entity.UserTrail;
import com.mysystem.service.BookService;
import com.mysystem.service.CartService;
import com.mysystem.service.UserService;
import com.mysystem.service.UserTrailService;
import com.mysystem.serviceImpl.BookServiceImpl;
import com.mysystem.webservice.BookSOAP;


@Controller
@RequestMapping(value="/book")
public class BookController {
	@Autowired
	private BookService bookService;//用户操作服务层
	@Autowired
	private CartService cartService;//用户操作服务层
	@Autowired
	private UserTrailService userTrailService;//用户操作服务层
	@Autowired
	private UserService userService;//用户操作服务层
	@RequestMapping(value="/getDetail/{bookId}",method=RequestMethod.POST)
	@ResponseBody
	public String getDetail(@PathVariable(value = "bookId")Integer bookId)
	{ 
		try{
			String result=bookService.getDetail(bookId);	
		if (result!=null) {
			return result;
		}//异常
		else return "x";
		}
	
		catch (Exception e)
		{
			return "x";
		}
	}
	
	public static byte[] read(InputStream inStream) throws Exception { 
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
        byte[] buffer = new byte[1024]; 
        int len = 0; 
        while ((len = inStream.read(buffer)) != -1) { 
            outputStream.write(buffer); 
        } 
        inStream.close(); 
        return outputStream.toByteArray(); 
    } 
	private static String getResponseString(InputStream inStream) throws Exception { 
        byte[] data = read(inStream); 
        String objectstring = new String(data); 
        return objectstring; 
    } 
	
	@RequestMapping(value="/getREST",method=RequestMethod.POST)
	@ResponseBody
	public String getREST( Integer bookId) throws Exception
	{ 
		System.out.println("getREST");
		
		 String base = "http://127.0.0.1:8080/MySystem/bookREST/getDetail/"+bookId;
         HttpURLConnection conn;
         String result ="";
		try {
			conn = (HttpURLConnection) new URL(base).openConnection();
	         conn.setRequestProperty("Accept", "application/json");
	         conn.setConnectTimeout(5000);
	         conn.setRequestMethod("POST");
	         int code = conn.getResponseCode();
	         InputStream inStream1 = conn.getInputStream();
             result = getResponseString(inStream1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	
	@RequestMapping(value="/getSOAP",method=RequestMethod.POST)
	@ResponseBody
	public String getSOAP(Integer bookId)
	{ 
		System.out.println("getSOAP");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        //注册WebService接口  
        factory.setServiceClass(BookSOAP.class);  
        //设置WebService地址  
        factory.setAddress("http://localhost:8080/MySystem/webservice/bookSOAP");  
        BookSOAP bookSoap = (BookSOAP)factory.create();  
		return bookSoap.getDetail(bookId);
	}
	
	
	@RequestMapping(value = "/showBooks")
	public ModelAndView showBooks(PageModel<Book> pageModel,HttpSession session){
		if (pageModel == null) {
			pageModel = new PageModel<Book>();
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
		User u=(User) session.getAttribute("u");
		if (u.getPriority()==1) {
			pageModel.setThre(-1);
			pageModel.setConf("Confidential");
		}
		else if (u.getPriority()==0)
		{
			pageModel.setThre(0);
			pageModel.setConf("");
		}
		List<Book> bookList = bookService.findAll(pageModel);
		pageModel.setDatas(bookList);
		pageModel.setTotalrecode(bookService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.setViewName("bookShelf");	
		return modelAndView;
	}
	
	@RequestMapping(value = "/findBook")
	public ModelAndView findByName(PageModel<Book> pageModel,@RequestParam(value = "choice") String choice,@RequestParam(value = "subInput") String subInput,HttpSession session){
		if (pageModel == null) {
			pageModel = new PageModel<Book>();
		}
		User u=(User) session.getAttribute("u");
		if (u.getPriority()==1) {
			pageModel.setThre(-1);
			pageModel.setConf("Confidential");
		}
		else if (u.getPriority()==0)
		{
			pageModel.setThre(0);
			pageModel.setConf("");
		}
		pageModel.setParam(subInput);
		if (choice.equals("bName"))
		{
			List<Book> bookList = bookService.findByName(pageModel,session);
			pageModel.setDatas(bookList);
			pageModel.setTotalrecode(bookService.countByName(pageModel));
		}
		else if (choice.equals("cat"))
		{
			List<Book> bookList = bookService.findByCat(pageModel);
			pageModel.setDatas(bookList);
			pageModel.setTotalrecode(bookService.countByCat(pageModel));
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.setViewName("bookShelf");	
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/putCart")
	public ModelAndView putCart(PageModel<Book> pageModel,@RequestParam(value = "bookId") Integer bookId,@RequestParam(value = "bookNum") Integer bookNum,HttpSession session){
		User tmp=(User) session.getAttribute("u");
		Book b=bookService.findById(bookId);
		double sum=b.getPrice()*bookNum;
		Integer anum=b.getStock()-bookNum;
		b.setStock(anum);
		bookService.updateStock(b);
		Cart ct=new Cart();
		ct.setBookId(bookId);
		ct.setUid(tmp.getUid());
		ct.setBookName(b.getBookName());
		Integer isExist=cartService.isExist(ct);
		ct.setNum(bookNum);
		ct.setSum(sum);
		System.out.println(isExist);
		if (isExist!=null) 
		{
			cartService.updateCart(ct);
		}
		else
		{
			ct.setBuy(0);
			cartService.add(ct);//插入账户信息
		}
		User u=(User) session.getAttribute("u");
		if (u.getPriority()==1) {
			pageModel.setThre(-1);
			pageModel.setConf("Confidential");
		}
		else if (u.getPriority()==0)
		{
			pageModel.setThre(0);
			pageModel.setConf("");
		}
		List<Book> bookList = bookService.findAll(pageModel);
		pageModel.setDatas(bookList);
		pageModel.setTotalrecode(bookService.countAll(pageModel));//页数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.setViewName("bookShelf");		
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value = "/addBook",method = RequestMethod.POST)
	public String add(@RequestParam(value = "bookName") String bookName,@RequestParam(value = "price") double price,@RequestParam(value = "stock") Integer stock,@RequestParam(value = "category") String category,@RequestParam(value = "description") String description,HttpSession session){
		PageModel pageModel=new PageModel();
		pageModel.setParam(bookName);
		List<Book> bookList = bookService.findByName(pageModel,session);
		if (bookList!=null)
		{
			return "addBook";
		}
		else
		{
			Book nBook=new Book();
			nBook.setBookName(bookName);
			nBook.setCategory(category);;
			nBook.setDescription(description);
			nBook.setPrice(price);
			nBook.setStock(stock);
			bookService.addBook(nBook);
			return "redirect:showBooks";
		}

	}
	
	
	@RequestMapping(value = "/getBook/{bookId}",method = RequestMethod.POST)
	public ModelAndView getBook(@PathVariable(value = "bookId") Integer bookId) throws Exception{
		Book book = bookService.findByBookId(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("book", book);
		modelAndView.setViewName("editBook");		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/editBook",method = RequestMethod.POST)
	public String editBook(@RequestParam(value = "bookId") Integer bookId,@RequestParam(value = "price") double price,@RequestParam(value = "stock") Integer stock,@RequestParam(value = "category") String category,@RequestParam(value = "description") String description){
		Book book=new Book();
		book.setBookId(bookId);
		book.setCategory(category);
		book.setDescription(description);
		book.setPrice(price);
		book.setStock(stock);
		bookService.editBook(book);
		return "redirect:showBooks";
	}
}
