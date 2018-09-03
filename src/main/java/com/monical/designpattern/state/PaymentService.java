package com.monical.designpattern.state;

/**
 * 复杂业务状态的处理：从状态模式到 FSM
 * https://juejin.im/entry/5a26b17df265da4319562263
 * @author zijie.cao
 * @date 2018-07-09 09:59:47
 */
public class PaymentService {

    private OrderRepository orderRepository;

    public void payOrder(long orderId) {
        Order order = orderRepository.find(orderId);
        order.pay(order.totalFee);
        orderRepository.save(order);
    }

    // ocp open/close principal

}
