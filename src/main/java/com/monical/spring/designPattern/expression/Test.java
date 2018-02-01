package com.monical.spring.designPattern.expression;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author zijie.cao
 * @date 2018-01-23 22:57:57
 */
public class Test {

    @org.junit.Test
    public void method1() {
        ExpressionParser parser = new SpelExpressionParser();
        Pojo pojo = new Pojo();
        StandardEvaluationContext ctx = new StandardEvaluationContext(pojo);
        ctx.setVariable("name", "Overwritten pojo's name");
        parser.parseExpression("name = #name").getValue(ctx);

        System.out.println("Pojo's name is : " + pojo.getName());
    }

    private static class Pojo {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @org.junit.Test
    public void test() {
        CarComponent car = new Car();
        Mechanic mechanic = new QualifiedMechanic();
        car.accept(mechanic);
        assertTrue("After qualified mechanics visit, the car should be broken",
                car.isBroken());
        Mechanic nonqualifiedMechanic = new NonQualifiedMechanic();
        car.accept(nonqualifiedMechanic);
        assertFalse("Car shouldn't be broken because non qualified mechanic " +
                " can't see breakdowns", car.isBroken());
    }
}

// visitor
interface Mechanic {
    public void visit(CarComponent element);

    public String getName();
}

class QualifiedMechanic implements Mechanic {
    @Override
    public void visit(CarComponent element) {
        element.setBroken(true);
    }

    @Override
    public String getName() {
        return "qualified";
    }
}

class NonQualifiedMechanic implements Mechanic {
    @Override
    public void visit(CarComponent element) {
        element.setBroken(false);
    }

    @Override
    public String getName() {
        return "unqualified";
    }
}

// visitable
abstract class CarComponent {
    protected boolean broken;

    public abstract void accept(Mechanic mechanic);

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isBroken() {
        return this.broken;
    }
}

class Car extends CarComponent {
    private boolean broken = false;
    private CarComponent[] components;

    public Car() {
        components = new CarComponent[]{
                new Wheels(), new Engine(), new Brake()
        };
    }

    @Override
    public void accept(Mechanic mechanic) {
        this.broken = false;
        if (mechanic.getName().equals("qualified")) {
            int i = 0;
            while (i < components.length && this.broken == false) {
                CarComponent component = components[i];
                mechanic.visit(component);
                this.broken = component.isBroken();
                i++;
            }
        }
        // if mechanic isn't qualified, we suppose that
        // he isn't able to see breakdowns and so
        // he considers the car as no broken
        // (even if the car is broken)
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }
}

class Wheels extends CarComponent {
    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}

class Engine extends CarComponent {
    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}

class Brake extends CarComponent {
    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}