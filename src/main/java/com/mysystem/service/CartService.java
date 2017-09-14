package com.mysystem.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.Cart;
import com.mysystem.entity.temp;;
public interface CartService {
	public void add(Cart ct);
	public List<Cart> findAll(PageModel<Cart> pageModel);
	public Integer countAll(PageModel<Cart> pageModel);
	public Cart findById(Integer cartId);
	public void delete(Integer cartId);
	public double getCartSum(Integer uid);
	public void buy(temp p);
	public void receiver(HttpSession session1,String ss);
	public Integer isExist(Cart cart);
	public void updateCart(Cart cart);
	public List<Cart> findAllBuy(PageModel<Cart> pageModel);
	public double getBuySum(Integer uid);
	public Integer countAllBuy(PageModel<Cart> pageModel);
	public Cart findByCartId(Integer cartId);
	public void editCart(Cart cart);
	
}
