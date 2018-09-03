package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:11:08
 */
public class PayingOrder implements CanPaySuccessOrder {
    @Override
    public Order paySuccess(Order order) {
        return null;
    }
}
