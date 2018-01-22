package com.monical.jdk.exception;

/**
 * @author zijie.cao
 * @date 2018-01-22 14:14:00
 */
public class TestCase3 {

    void testMultiExp() {
        try {
            method2();
        } catch (ChildExp1 e) {
            System.out.println("error occus as of ChildExp1 class " + e.getMessage());
        } catch (ParentExp1 e) {
            System.out.println("error occus as of ParentExp1 class " + e.getMessage());
            if (e instanceof ChildExp2) {
                throw e;
            }
        } catch (AlternativeExp1 e) {
            System.out.println("error occus as of AlternativeExp1 class " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("error occus as of RuntimeException class " + e.getMessage());
        }
    }

    void method2() {
        throw new ChildExp2("ChildExp2 method2 throws!");
    }

    public static void main(String[] args) {
        TestCase3 testCase = new TestCase3();
        // testCase.testSameExceptionClass();
        // testCase.testSuperExceptionClass();
        testCase.testMultiExp();
    }


}
