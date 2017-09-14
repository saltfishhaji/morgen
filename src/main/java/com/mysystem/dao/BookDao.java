package com.mysystem.dao;

import java.util.List;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.Book;

public interface BookDao {
	public List<Book> findAll(PageModel<Book> pageModel);
	public Integer countAll(PageModel<Book> pageModel);//计数
	
	public List<Book> findByName(PageModel<Book> pageModel);
	public Integer countByName(PageModel<Book> pageModel);//计数
	
	public List<Book> findByCat(PageModel<Book> pageModel);
	
	public Integer countByCat(PageModel<Book> pageModel);//计数
	public Book findById(Integer bookId);
	
	public void updateStock(Book b);
	public void addBook(Book b);
	
	public Book findByBookId(Integer bookId);
	public void editBook(Book b);
	public String getDetail(Integer bookId);
}
