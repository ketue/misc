package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:48:59
 */
public class PartialPaidOrderState extends OrderState {

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

    @Override
    public OrderState refundSuccess(Order order) {
        return null;
    }
}
