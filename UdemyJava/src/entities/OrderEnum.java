package entities;

import java.util.Date;

import entities.enums.OrderStatus;

public class OrderEnum {
	
	private Integer id;
	private Date moment;
	private OrderStatus status;
	
	public OrderEnum() {
		
	}

	public OrderEnum(Integer id, Date moment, OrderStatus status) {
		this.id = id;
		this.moment = moment;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order:\nID: " + id + "\nDate: " + moment + "\nStatus: " + status;
	}
	
	
}
