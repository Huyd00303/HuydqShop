package com.huydq.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huydq.demo.model.Products;


@Transactional
@Repository
public class ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Products> getAllProduct() {
		Session ses = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Products> list = ses.createQuery("From Products").list();
		return list;
	}
	
	public boolean updateProduct(Products products) {
		Session ses = sessionFactory.getCurrentSession();
		ses.update(products);
		return true;
	}
	
	public Products getProductById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Products products = session.get(Products.class, id);
		return products;
	}
	
	public boolean addProduct(Products products) {
		Session ses = sessionFactory.getCurrentSession();
		ses.save(products);
		return true;
	}
	
	public boolean deleteProduct(String id) {
		Session ses = sessionFactory.getCurrentSession();
		Products products = ses.get(Products.class, id);
		ses.delete(products);
		return true;
	}
	
	
	
	
}
