package com.monical.spring.circle;

/**
 * @author zijie.cao
 * @date 2018-01-23 17:03:46
 */
public class TestA {
    private boolean beCallConstructor = false;
    private TestB testB;

    public TestA() {
        beCallConstructor = true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[TestB：").append(testB == null ? "未初始化," : "已初始化,");
        builder.append("[TestD：").append(testD == null ? "未初始化," : "已初始化,");
        builder.append("是否调用完构造方法:").append(beCallConstructor ? "是" : "否").append("]");
        return builder.toString();
    }

    private TestD testD;

    public void setTestD(TestD testD) {
        this.testD = testD;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
