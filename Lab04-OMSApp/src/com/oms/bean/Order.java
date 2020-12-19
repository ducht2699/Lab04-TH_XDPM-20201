package com.oms.bean;

import java.util.*;

public class Order {
	private String id;
	private String code;
	private String customerName;
	private String customerPhoneNumber;
	private String customerAddress = new String();
	private ArrayList<OrderLine> orderLines;
	private float totalCost;
	
	public Order() {
		orderLines = new ArrayList<OrderLine>();
		
		this.id = new String();
		this.code = new String();
		this.customerName = new String();
		this.customerPhoneNumber = new String();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getTotalCost() {
		float res = 0;
		if (customerAddress.equals("")) return 0;
		String addr = customerAddress.toUpperCase();
		
		if (orderLines!= null) {
			Iterator<OrderLine> iter = orderLines.iterator();
			float totalWeight = 0f;
			while (iter.hasNext()) {
				OrderLine ol = iter.next();
				res +=  ol.getProductCost() * ol.getProductQuantity();
				totalWeight += ol.getProductWeight() * ol.getProductQuantity();
			}
			
			if (addr.contains("HN") || addr.contains("HÀ NỘI") || addr.contains("HCM") || addr.contains("HỒ CHÍ MINH")) {
				if (totalWeight > 3.0f) {
					res += 22000.0f;
					totalWeight -= 3.0f;
					do {
						res += 2500.0f;
						totalWeight -= 0.5f;
					} while (totalWeight >= 0);
				}
				
			} else {
				if (totalWeight > 0.5f) {
					res += 30000.0f;
					totalWeight -= 0.5f;
					do {
						res += 2500.0f;
						totalWeight -= 0.5f;
					} while (totalWeight >= 0);
				}
				
			}
			
		}
		
		return res;
	}
	
	
	
	public void addOrderLine(OrderLine orderLine) {
		boolean existed = false;
		for (OrderLine ol: orderLines) {
			if (ol.getProductId().equals(orderLine.getProductId())) {
				ol.setProductQuantity(ol.getProductQuantity() + orderLine.getProductQuantity());
				existed = true;
				break;
			}
		}
		
		if (!existed) {
			orderLines.add(orderLine);
		}
	}

	public boolean search(Order order) {
		if (this.id != null && !this.id.equals("") && !this.id.contains(order.id)) {
			return false;
		}
		if (this.code != null && !this.code.equals("") && !this.code.contains(order.code)) {
			return false;
		}
		if (this.customerName != null && !this.customerName.equals("") && !this.customerName.contains(order.customerName)) {
			return false;
		}
		if (this.totalCost != 0 && this.totalCost != order.totalCost) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			return this.code.equals(((Order) obj).code);
		}
		return false;
	}
}