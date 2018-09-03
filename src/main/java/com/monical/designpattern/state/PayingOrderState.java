package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:38:01
 */
public class PayingOrderState extends OrderState {

    public OrderState pay(Order order) {
        throw new IllegalStateException("已经在支付中");
    }

    public OrderState paySuccess(Order order) {
        return null;
    }

    public OrderState paySuccess(Order order, long fee) {
        doPaySuccess(order, fee);
        if (order.paidFee < order.totalFee) {
            order.setState(new PartialPaidOrderState());
        } else {
            order.setState(new PaidOrderState());
        }

        return order.getState();
    }

    public OrderState refund(Order order) {
        throw new IllegalStateException("尚未完成支付");
    }

    public OrderState refundSuccess(Order order) {
        throw new IllegalStateException("尚未完成支付");
    }


    private void doPaySuccess(Order order, long fee) {

    }
}
