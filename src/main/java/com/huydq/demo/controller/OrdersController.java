package com.huydq.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huydq.demo.dao.OrdersDao;
import com.huydq.demo.model.Orders;


@RestController
@RequestMapping(value = "/order")
public class OrdersController {

	@Autowired
	OrdersDao ordersDao;

	@GetMapping(value = "/read")
	public List<Orders> getAllOrders() {
		List<Orders> list = ordersDao.getAllOrder();
		return list;
		
	}
	
	@PutMapping(value = "/update" )
	public List<Orders> updateOrder(@RequestBody Orders order) {
		ordersDao.updateOrder(order);
		List<Orders> list = ordersDao.getAllOrder();
		return list;
	}

	@PostMapping(value="/create")
	public List<Orders> createOrder(@RequestBody Orders orders){
		ordersDao.createOrder(orders);
		List<Orders> list = ordersDao.getAllOrder();
		return list;
	}

	@DeleteMapping(value = "/{id}")
	public List<Orders> deleteOrdersById(@PathVariable ("id") String idOrder) {
		ordersDao.deleteOrder(idOrder);
		List<Orders> list = ordersDao.getAllOrder();
		return list;
	}

}
