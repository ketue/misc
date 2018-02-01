package com.monical.spring.circle;

/**
 * @author zijie.cao
 * @date 2018-01-23 17:04:01
 */
public class TestB {
    private boolean beCallConstructor = false;
    private TestC testC;

    public TestB() {
        beCallConstructor = true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[testC：").append(testC == null ? "未初始化," : "已初始化,");
        builder.append("是否调用完构造方法:").append(beCallConstructor ? "是" : "否").append("]");
        return builder.toString();
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}
