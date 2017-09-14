package com.mysystem.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysystem.service.BookService;

@RestController
@RequestMapping(value="/bookREST")
public class BookREST {
	@Autowired
	private BookService bookService;//用户操作服务层
	@RequestMapping(value="/getDetail/{bookId}",method=RequestMethod.POST)
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
}
