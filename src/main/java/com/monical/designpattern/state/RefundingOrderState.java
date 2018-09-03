package com.monical.designpattern.state;

import com.monical.designpattern.state.fsm.CanRefundSuccessOrder;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:51:26
 */
public class RefundingOrderState extends OrderState {

    @Override
    OrderState pay(Order order) {
        return null;
    }

    @Override
    public OrderState paySuccess(Order order, long fee) {
        return null;
    }

    @Override
    public OrderState refund(Order order) {
        return null;
    }

    public OrderState refundSuccess(Order order) {
        return null;
    }
}
