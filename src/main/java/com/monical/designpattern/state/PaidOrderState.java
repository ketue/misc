package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:49:59
 */
public class PaidOrderState extends OrderState {

    @Override
    OrderState pay(Order order) {
        throw new IllegalStateException("已支付订单无法再支付");
    }

    @Override
    public OrderState paySuccess(Order order, long fee) {
        return null;
    }

    @Override
    public OrderState refund(Order order) {
        return null;
    }

    @Override
    public OrderState refundSuccess(Order order) {
        return null;
    }
}
