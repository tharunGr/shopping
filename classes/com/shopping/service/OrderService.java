package com.shopping.service;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.OrderDAO;
import com.shopping.model.Order;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.model.OrderDetail;
import com.shopping.utils.CartServiceUtil;
import com.shopping.service.UserService;
import com.shopping.service.ProductService;
import com.shopping.exception.ShopException;
import com.shopping.utils.DateUtil;
import com.shopping.utils.ProductServiceUtil;

/**
 * <p> 
 * Performing Order module bussiness logics by getting informations from users 
 * and access the order database to perform various operatoins 
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();

    /** 
     * <p>
     * Creating the new products in a order.
     * </p>
     *
     * @param order      - It is the Object of order. 
     */
    public void createOrder(String[] cartIds, Order order) throws ShopException {
        order.setStatus(Boolean.TRUE);
        order.setCreatedDate(DateUtil.getDate());
        order.setUpdatedDate(DateUtil.getDate());
        order.setOrderItem(createOrderByCartId(cartIds, order));
        order.setTotalPrice(calculateAmount(order.getOrderItem()));
        orderDAO.createOrder(order);
    }
  
    /** 
     * <p>
     * creating order details by cart id
     * </p>
     *
     * @param orderItem - It is the overall order details of an order.
     * @param order     - It is the object of order
     * @param user      - It is the object of user.
     * @return          - It returns the cart details of a user. 
     */     
    private Set<OrderDetail> createOrderByCartId(String[] cartIds, Order order) 
                                      throws ShopException {
        Set<OrderDetail> orderItem = new HashSet<OrderDetail>();
        for(String id : cartIds) {
            OrderDetail orderDetail = new OrderDetail();
            Cart cart = CartServiceUtil.getInstance().getCartByCartId(Integer.parseInt(id));
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setOrder(order);
            orderItem.add(orderDetail);
        }
        return orderItem;
    } 


    /** 
     * <p>
     * Deleting the order from overall orders.
     * </p>
     *
     * @param orderid  - Order id to get deleted.  
     */
    public void deleteOrder(int orderId) throws ShopException {
        orderDAO.removeOrder(getOrderByOrderId(orderId));
    }

    /** 
     * <p>
     * Calculating totalPrice of the product based on quantity and its price.
     * </p>
     *
     * @param price   - price of the product
     * @param quantity- quantity of the product 
     * @return        - It returns total cost of the product  back to controller                    
     */
    public int totalPrice(int price, int quantity) {
        int total = price * quantity;
        return total;
    }

    /** 
     * <p>
     * Calculating totalAmount of the overall products in cart.
     * </p>
     *
     * @param orderItem - It contains order Details
     * @return          - It returns total cost of the cart                     
     */
    public int calculateAmount(Set<OrderDetail> orderItem) throws ShopException {
        int sum = 0;
        ProductService productService = new ProductService();
        for (OrderDetail orderDetail : orderItem) {
            int quantity = orderDetail.getQuantity();
            int productId = orderDetail.getProduct().getId();
            Product product = ProductServiceUtil.getInstance().getProductByProductId(productId);
            int price = product.getPrice();
            sum = sum + totalPrice(price, quantity);
        }
        return sum;  
    }
   
    /** 
     * <p>
     * Getting the order by user id.
     * </p>
     *
     * @param userid - User id to get display their cart.
     * @return       - It returns the cart details of a user. 
     */
    public List<Order> getOrder() throws ShopException {
        return orderDAO.getOrder();
    }

    /** 
     * <p>
     * getting the cart by user id.
     * </p>
     *
     * @param userid - User id to get display their cart.
     * @return       - It returns the cart details of a user. 
     */
    public List<Order> getOrderByUserId(int userId) throws ShopException {
        return orderDAO.getOrderByUserId(userId);
    }

    /**
     * <p>
     * check cart id does exist..
     * </p>
     *
     * @param cartId - cart Id to check.
     * @return       - if equals returns true or else returns false.
     */
    public Order getOrderByOrderId(int orderId) throws ShopException {
        Order order = orderDAO.getOrderByOrderId(orderId);
        if (order == null) {
            throw new ShopException("Invalid order id");
        }
        return order;
    }

    /**
     * <p>
     * Getting user id
     * </p>
     *
     * @param userId - It has the user id.
     */
    public int getUserId(int userId) throws ShopException {
        UserService userService = new UserService();
        return userService.getUserById(userId).getId();  
    }
}





