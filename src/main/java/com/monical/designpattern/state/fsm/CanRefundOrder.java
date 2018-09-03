package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:07:03
 */
public interface CanRefundOrder {

    Order refund(Order order);
}
