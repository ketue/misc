package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:11:37
 */
public class PaidOrder implements CanRefundOrder {
    @Override
    public Order refund(Order order) {
        return null;
    }
}
