package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:00:42
 */
public interface OrderRepository {

    Order find(long orderId);

    void save(Order order);
}
