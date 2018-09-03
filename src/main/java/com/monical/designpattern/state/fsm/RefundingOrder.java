package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:12:00
 */
public class RefundingOrder implements CanRefundSuccessOrder {

    @Override
    public Order refundSuccess(Order order) {
        return null;
    }
}
