package com.oms.serverapi.test;

import static org.junit.Assert.assertEquals;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.bean.Order;
import com.oms.bean.OrderLine;

@RunWith(Parameterized.class)
public class AddOrderLineTest {
	private Order order;
	private OrderLine orderLineAdd;
	private int expectedResultSize;
	
	public AddOrderLineTest(Order order,OrderLine orderLine ,int expectedResult) {
		super();
		this.order=order;
		this.orderLineAdd=orderLine;
		this.expectedResultSize=expectedResult;
	}
	
	public static Order generateOrder(OrderLine orderLine) {
		ArrayList<OrderLine> orignalOrderLines=new ArrayList<OrderLine>();
		if(orderLine!=null)
			orignalOrderLines.add(orderLine);
		Order order=new Order();
		order.setOrderLines(orignalOrderLines);
		return order;
	}
	
	
	// 5 test cases
	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{generateOrder(null),new OrderLine("1","Sach Giao Khoa",32000,2,2.5f),1}, 
			{generateOrder(new OrderLine("1","Sach Giao Khoa",32000,2,2.5f)),new OrderLine("1","Sach Giao Khoa",32000,2,2.5f),1}, 
			{generateOrder(new OrderLine("2","Sach The Thao",32000,2,2.5f)),new OrderLine("1","Sach Giao Khoa",32000,2,2.5f),2}
		});
	}
	
	@Test
	public void testToalCost() {
		order.addOrderLine(orderLineAdd);
		assertEquals("Wrong Total Cost",expectedResultSize,order.getOrderLines().size());
	}

}
