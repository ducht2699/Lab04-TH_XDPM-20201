package com.oms.serverapi.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.bean.Order;
import com.oms.bean.OrderLine;

@RunWith(Parameterized.class)
public class TotalCostWhiteBoxTest {
	
	private Order order;
	
	private double expectedResult;
	
	public TotalCostWhiteBoxTest(Order order, double expectedResult) {
		super();
		this.order=order;
		this.expectedResult=expectedResult;
	}
	
	public static Order generateOrder(float weightProduct,float costProduct,String address) {
		Order order=new Order();
		ArrayList<OrderLine> orderLines=new ArrayList<OrderLine>();
		OrderLine orderLine=new OrderLine();
		Book book=new Book();
		
		book.setWeight(weightProduct);
		book.setCost(costProduct);
		
		orderLine.setProductCost(book.getCost());
		orderLine.setProductWeight(book.getWeight());
		orderLine.setProductQuantity(1);
		orderLines.add(orderLine);
		
		order.setCustomerAddress(address);
		order.setOrderLines(orderLines);
		
		return order;
	}
	
	//6 test cases
	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{ new Order(), 0 },
			{ generateOrder(5,505000,"Hai Phong"), 505000 }, 
			{ generateOrder(2,200000,"Ha Noi"), 222000 }, 
			{ generateOrder(5,200000,"Ha Noi"), 232000 }, 
			{ generateOrder(0.4f,200000,"Hai Phong"), 230000}, 
			{ generateOrder(1f,200000,"Hai Phong"), 232500 } 
		});
	}
	
	@Test
	public void testToalCost() {
		assertEquals("Wrong Total Cost", (int)expectedResult,(int)order.getTotalCost());
	}
}