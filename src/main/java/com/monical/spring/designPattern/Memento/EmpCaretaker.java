package com.monical.spring.designPattern.Memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zijie.cao
 * @date 2018-01-24 14:02:26
 */
public class EmpCaretaker {
    final Deque<EmpMemento> mementos = new ArrayDeque<>();

    public EmpMemento getMemento() {
        EmpMemento empMemento = mementos.pop();
        return empMemento;
    }

    public void addMemento(EmpMemento memento) {
        mementos.push(memento);
    }
}
