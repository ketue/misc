package com.monical.designpattern.state.fsm;

import com.monical.designpattern.state.Order;

/**
 * @author zijie.cao
 * @date 2018-07-09 10:08:29
 */
public class UnpaidOrder implements CanPayOrder {

    @Override
    public Order pay() {
        return null;
    }
}
