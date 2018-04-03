package com.monical.jdk.reflect;

import com.google.common.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;

/**
 * @author zijie.cao
 * @date 2018-03-08 13:20:00
 */
public class TypeTokenParameterizedTypeReference<T> extends ParameterizedTypeReference<T> {

    private final Type type;

    protected TypeTokenParameterizedTypeReference(TypeToken<T> typeToken) {
        this.type = typeToken.getType();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj || (obj instanceof ParameterizedTypeReference &&
                this.type.equals(((ParameterizedTypeReference<?>) obj).getType())));
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

    @Override
    public String toString() {
        return "ParameterizedTypeReference<" + this.type + ">";
    }

}
