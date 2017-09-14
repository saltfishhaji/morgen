package com.mysystem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.Book;
import com.mysystem.entity.UserPoint;
import com.mysystem.entity.UserTrail;

public interface BookService {
	public List<Book> findAll(PageModel<Book> pageModel);
	public Integer countAll(PageModel<Book> pageModel);
	
	public List<Book> findByName(PageModel<Book> pageModel,HttpSession sesion);
	public Integer countByName(PageModel<Book> pageModel);
	
	public List<Book> findByCat(PageModel<Book> pageModel);
	public Integer countByCat(PageModel<Book> pageModel);
	
	public Book findById(Integer bookId);
	public void updateStock(Book b);
	public void addBook(Book b);
	public Book findByBookId(Integer bookId);
	public void editBook(Book b);
	
	public String getDetail(Integer bookId);
}
