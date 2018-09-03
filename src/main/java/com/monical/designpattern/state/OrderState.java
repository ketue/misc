package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:36:41
 */
public abstract class OrderState {

    abstract OrderState pay(Order order);

    public abstract OrderState paySuccess(Order order, long fee);

    public abstract OrderState refund(Order order);

    public abstract OrderState refundSuccess(Order order);
}
