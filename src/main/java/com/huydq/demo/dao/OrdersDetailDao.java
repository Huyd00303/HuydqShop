package com.huydq.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huydq.demo.model.OrderDetails;
import com.huydq.demo.model.Orders;
import com.huydq.demo.model.Products;

@Transactional
@Repository
public class OrdersDetailDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<OrderDetails> read(){
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<OrderDetails> list = session.createQuery("from OrderDetails").list();
		return list;
	}
	
	public boolean update(OrderDetails orderDetails) {
		Session ses = sessionFactory.getCurrentSession();
		ses.update(orderDetails);
		return true;
	}
	
	public OrderDetails getOrderDetailsById(String id) {
		Session session = sessionFactory.getCurrentSession();
		OrderDetails orderDetails = session.get(OrderDetails.class, id);
		return orderDetails;
	}
	
	public boolean create(OrderDetails orderDetails) {
		Session ses = sessionFactory.getCurrentSession();
		ses.save(orderDetails);
		return true;
	}
	
	public boolean delete(String id) {
		Session ses = sessionFactory.getCurrentSession();
		OrderDetails orderDetails = ses.get(OrderDetails.class, id);
		ses.delete(orderDetails);
		return true;
	}
	
	public Products getProductById(String idProduct) {
		Session session = sessionFactory.getCurrentSession();
		Products products = session.get(Products.class, idProduct);
		return products;
	}
	
	public Orders getOrdersById(String idOrders) {
		Session session = sessionFactory.getCurrentSession();
		Orders orders= session.get(Orders.class, idOrders);
		return orders;
	}
	

	
	
}
