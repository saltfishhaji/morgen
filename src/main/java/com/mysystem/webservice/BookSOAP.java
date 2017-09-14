package com.mysystem.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
public interface BookSOAP {
	public String getDetail( Integer bookId);
}
