package com.monical.designpattern.state;

import com.monical.designpattern.state.fsm.CanPayOrder;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:44:33
 */
public class UnpaidOrder extends OrderState {

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
        throw new IllegalStateException("未支付不能退款");
    }

    @Override
    public OrderState refundSuccess(Order order) {
        throw new IllegalStateException("未支付不能退款成功");
    }
}
