package com.monical.designpattern.state;

/**
 * @author zijie.cao
 * @date 2018-07-09 09:51:59
 */
public class RefundedOrderState extends OrderState {
    @Override
    OrderState pay(Order order) {
        throw new IllegalStateException("已退款状态不支持支付");
    }

    @Override
    public OrderState paySuccess(Order order, long fee) {
        return null;
    }

    @Override
    public OrderState refund(Order order) {
        throw new IllegalStateException("已退款状态不支持退款");
    }

    @Override
    public OrderState refundSuccess(Order order) {
        throw new IllegalStateException("已退款状态不支持退款成功");
    }
}
