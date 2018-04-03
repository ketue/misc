package com.monical.jdk.reflect;

/**
 * @author zijie.cao
 * @date 2018-03-08 12:59:05
 */
public interface PersonInterface<T> {

    default void print() {
        PrintUtils.print("person Integerface output : " + getClass());
    }
    /*private final Type type;

    protected PersonInterface() {
        Class<?> parameterizedTypeReferenceSubclass = findParameterizedTypeReferenceSubclass(getClass());
        Type type = parameterizedTypeReferenceSubclass.getGenericSuperclass();
        // Assert.isInstanceOf(ParameterizedType.class, type);
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // Assert.isTrue(parameterizedType.getActualTypeArguments().length == 1);
        this.type = parameterizedType.getActualTypeArguments()[0];
    }


    public Type getType() {
        return this.type;
    }

    // @Override
    // public boolean equals(Object obj) {
    //     return (this == obj || (obj instanceof ParameterizedTypeReference &&
    //             this.type.equals(((ParameterizedTypeReference<?>) obj).type)));
    // }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

    @Override
    public String toString() {
        return "ParameterizedTypeReference<" + this.type + ">";
    }


    private static Class<?> findParameterizedTypeReferenceSubclass(Class<?> child) {
        Class<?> parent = child.getSuperclass();
        if (Object.class == parent) {
            throw new IllegalStateException("Expected ParameterizedTypeReference superclass");
        } else if (ParameterizedTypeReference.class == parent) {
            return child;
        } else {
            return findParameterizedTypeReferenceSubclass(parent);
        }
    }

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void printGenericType() {
        System.out.println(type);
    }*/
}
