package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:06:04
 */
public interface CanPaySuccessOrder {
    Order paySuccess(Order order);
}
