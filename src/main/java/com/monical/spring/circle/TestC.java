package com.monical.spring.circle;

/**
 * @author zijie.cao
 * @date 2018-01-23 17:04:15
 */
public class TestC {
    private boolean beCallConstructor = false;
    private TestA testA;

    public TestC() {
        beCallConstructor = true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[testA：").append(testA == null ? "未初始化," : "已初始化,");
        builder.append("是否调用完构造方法:").append(beCallConstructor ? "是" : "否").append("]");
        return builder.toString();
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
