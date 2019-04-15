package com.huydq.demo.model;

// default package
// Generated Mar 27, 2019 5:27:12 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * OrderDetails generated by hbm2java
 */
@Entity
@Table(name = "ORDER_DETAILS", schema = "C##MYSHOP")
public class OrderDetails implements java.io.Serializable {

	private String id;
	private Products products;
	private Orders orders;
	private double amount;
	private double price;
	private long quanity;

	public OrderDetails() {
	}

	public OrderDetails(String id, Products products, Orders orders, double amount, double price, long quanity) {
		this.id = id;
		this.products = products;
		this.orders = orders;
		this.amount = amount;
		this.price = price;
		this.quanity = quanity;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 126, scale = 0)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "PRICE", nullable = false, precision = 126, scale = 0)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "QUANITY", nullable = false, precision = 10, scale = 0)
	public long getQuanity() {
		return this.quanity;
	}

	public void setQuanity(long quanity) {
		this.quanity = quanity;
	}

}