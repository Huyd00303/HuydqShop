package com.huydq.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huydq.demo.model.OrderDetails;
import com.huydq.demo.model.Orders;

@Transactional
@Repository
public class OrdersDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<Orders> getAllOrder() {
		Session ses = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Orders> list = ses.createQuery("From Orders").list();
		return list;
	}

	public boolean updateOrder(Orders orders) {
		Session ses = sessionFactory.getCurrentSession();
			ses.update(orders);
			return true;
		
	}

	public Orders getOrdersById(String idOrder) {
		Session session = sessionFactory.getCurrentSession();
		Orders orders = session.get(Orders.class, idOrder);
		return orders;
	}

	public boolean createOrder(Orders orders) {
		Session ses = sessionFactory.getCurrentSession();
		ses.save(orders);
		return true;
	}

	public boolean deleteOrder(String idOrder) {
		Session ses = sessionFactory.getCurrentSession();
		try {
			Orders orders = ses.get(Orders.class, idOrder);
			ses.delete(orders);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public OrderDetails getOrderDetailsById(String idOrderDetails) {
		Session session = sessionFactory.getCurrentSession();
		OrderDetails orderDetails = session.get(OrderDetails.class, idOrderDetails);
		return orderDetails;
	}

}
