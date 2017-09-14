package com.mysystem.serviceImpl;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysystem.PageModel.PageModel;
import com.mysystem.dao.BookDao;
import com.mysystem.dao.UserTrailDao;
import com.mysystem.entity.Book;
import com.mysystem.entity.UserTrail;
import com.mysystem.service.BookService;
@Service(value="bookService")
@Transactional
public class BookServiceImpl implements BookService{
	MemCachedClient client;
	SockIOPool pool;
	@Autowired
	private BookDao bookDao;
	
	public List<Book> findAll(PageModel<Book> pageModel)
	{
		return bookDao.findAll(pageModel);
	}
	
	//计数
	public Integer countAll(PageModel<Book> pageModel){
		return bookDao.countAll(pageModel);
	}

	public List<Book> findByName(PageModel<Book> pageModel,HttpSession session) {
		List<Book> Books=new ArrayList<Book>(); 
		String bookname = pageModel.getParam();
		String key =  bookname;
		String books=null;
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
					 JSONArray bookList = jsonObj.getJSONArray("books");
					 for (int i=0;i<bookList.length();i++)
					 {
						 JSONObject obj=bookList.getJSONObject(i);
						 Book b=new Book();
						 b.setBookId(obj.getInt("bookId"));
						 b.setBookName(obj.getString("bookName"));
						 b.setCategory(obj.getString("category"));
						 b.setDescription(obj.getString("description"));
						 b.setPrice(obj.getDouble("price"));
						 b.setStock(obj.getInt("stock"));
						 Books.add(b);
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
		System.out.println(js);
		System.out.println(Books);
		if (Books.isEmpty() ) {
			Books=bookDao.findByName(pageModel);
			JSONObject json=new JSONObject();			
			try {
				json.put("books", Books);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("Get Books from DB");

		}else{
            System.out.println("Get Books from MemCached");

		}
		return Books;
	}

	public Integer countByName(PageModel<Book> pageModel) {
		String bookname = pageModel.getParam();
		String key =  "num"+bookname;
		Integer num=-1;
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
					 num=jsonObj.getInt("num");
				}


			 }
			  catch (JSONException e)
			  {
				  e.printStackTrace();
			  };
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(js);
		System.out.println(num);
		if (num==-1 ) {
			num=bookDao.countByName(pageModel);
			JSONObject json=new JSONObject();			
			try {
				json.put("num", num);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("Get Books from DB");

		}else{
            System.out.println("Get Books from MemCached");

		}
		return num;
	}

	public List<Book> findByCat(PageModel<Book> pageModel) {
		List<Book> Books=new ArrayList<Book>(); 
		String categoryname = pageModel.getParam();
		String key =  categoryname;
		String books=null;
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
					 JSONArray bookList = jsonObj.getJSONArray("books");
					 for (int i=0;i<bookList.length();i++)
					 {
						 JSONObject obj=bookList.getJSONObject(i);
						 Book b=new Book();
						 b.setBookId(obj.getInt("bookId"));
						 b.setBookName(obj.getString("bookName"));
						 b.setCategory(obj.getString("category"));
						 b.setDescription(obj.getString("description"));
						 b.setPrice(obj.getDouble("price"));
						 b.setStock(obj.getInt("stock"));
						 Books.add(b);
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
		System.out.println(js);
		System.out.println(Books);
		if (Books.isEmpty() ) {
			Books=bookDao.findByCat(pageModel);
			JSONObject json=new JSONObject();			
			try {
				json.put("books", Books);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("Get Books from DB");

		}else{
            System.out.println("Get Books from MemCached");

		}
		return Books;
	}

	public Integer countByCat(PageModel<Book> pageModel) {
		String categoryname= pageModel.getParam();
		String key =  "num"+categoryname;
		Integer num=-1;
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
					 num=jsonObj.getInt("num");
				}


			 }
			  catch (JSONException e)
			  {
				  e.printStackTrace();
			  };
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(js);
		System.out.println(num);
		if (num==-1 ) {
			num=bookDao.countByCat(pageModel);
			JSONObject json=new JSONObject();			
			try {
				json.put("num", num);	    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			client.set(key, json.toString());
            System.out.println("Get Books from DB");

		}else{
            System.out.println("Get Books from MemCached");

		}
		return num;
	}

	public Book findById(Integer bookId){
		return bookDao.findById(bookId);
	}
	public void updateStock(Book b)
	{
		bookDao.updateStock(b);
	}
	public void addBook(Book b)
	{
		bookDao.addBook(b);
	}

	public Book findByBookId(@WebParam(name="bookId") Integer bookId)
	{
		return bookDao.findByBookId(bookId);
	}
	
	public void editBook(Book b)
	{
		bookDao.editBook(b);
	}
	public String getDetail(Integer bookId)
	{
		return bookDao.getDetail(bookId);
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
