package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;
import com.monical.designpattern.state.OrderRepository;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:12:29
 */
public class PaymentService {


    private OrderRepository orderRepository;
    public void pay(long orderId) {
        Order order = orderRepository.find(orderId);
        CanPayOrder orderToPay = order.asCanPayOrder();
    }

}
