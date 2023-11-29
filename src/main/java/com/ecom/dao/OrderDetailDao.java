package com.ecom.dao;

import java.util.List;

import com.ecom.pojo.OrderDetail;

/**
 * @author pratay.roy
 */
public interface OrderDetailDao {
	public List<OrderDetail> getOrderDetailsById();

	public List<OrderDetail> getOrderDetailsByIds(List<Integer> orderDetailId);

	public Integer insertOrderDetail(List<OrderDetail> orderDetails);

	public Integer updateOrderDetailByOrderDetailId(Integer orderDetailId, OrderDetail orderDetail);

	public Integer updateOrderDetailByProductId(Integer productId, OrderDetail orderDetail);

	public Integer updateOrderDetailByOrderId(Integer orderId, OrderDetail orderDetail);

	public Integer deleteOrderDetail(Integer orderDetailId);

	public Integer deleteOrderDetails(List<Integer> orderDetailIds);

	String getErrorMessage();

}