package com.monical.designpattern.state;

import com.monical.designpattern.state.fsm.CanPayOrder;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:37:20
 */
public class Order {


    long paidFee;

    long totalFee;

    private OrderState state = new UnpaidOrder();


    // 付款
    public void pay(long paidFee) {
        state.pay(this);
    }

    public void paySuccess(long fee) {
        state.paySuccess(this, fee);
    }

    public void refund() {
        state.refund(this);
    }

    public void refundSuccess() {
        state.refundSuccess(this);
    }


    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public CanPayOrder asCanPayOrder() {
        return null;
    }
}
