package com.mysystem.webservice;

import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysystem.service.BookService;

@WebService(endpointInterface = "com.mysystem.webservice.BookSOAP")
public class BookSOAPImpl implements BookSOAP{
	@Autowired
	private BookService bookService;

	public String getDetail(@WebParam(name = "bookId") Integer bookId)
	{ 
		try{
			String result=bookService.getDetail(bookId);	
		if (result!=null) {
			return result;
		}//“Ï≥£
		else return "x";
		}
	
		catch (Exception e)
		{
			return "x";
		}
	}
}
