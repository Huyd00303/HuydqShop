package com.huydq.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.huydq.demo.dao.OrdersDetailDao;
import com.huydq.demo.model.OrderDetails;
import com.huydq.demo.model.Orders;
import com.huydq.demo.model.Products;

@RestController
@RequestMapping(value = "/orderDetail")
public class OrdersDetailController {

	@Autowired
	OrdersDetailDao ordersDetailDao;

	@GetMapping(value = "/read")
	public List<OrderDetails> read() {
		List<OrderDetails> list = ordersDetailDao.read();
		return list;
	}

	@RequestMapping(value = "/update")
	public List<OrderDetails> update(@RequestBody OrderDetails orderDetails) {
		ordersDetailDao.update(orderDetails);
		List<OrderDetails> list = ordersDetailDao.read();
		return list;
	}

	@PostMapping(value = "/create")
	public List<OrderDetails> create(@RequestBody OrderDetails orderDetails) {
		ordersDetailDao.create(orderDetails);
		List<OrderDetails> list = ordersDetailDao.read();
		return list;
	}

	@GetMapping(value = "/get/{idOrderDetails}")
	public OrderDetails getOrderDetailsById(@PathVariable("idOrderDetails") String idOrderDetails) {
		OrderDetails orderDetails = ordersDetailDao.getOrderDetailsById(idOrderDetails);
		return orderDetails;
	}

	@DeleteMapping(value = "/{id}")
	public List<OrderDetails> dalete(@PathVariable("id") String id) {
		ordersDetailDao.delete(id);
		List<OrderDetails> list = ordersDetailDao.read();
		return list;
	}

}
