package com.monical.jdk.reflect;

import com.google.common.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;

/**
 * @author zijie.cao
 * @date 2018-03-08 13:19:31
 */
public class ParameterizedTypeReferenceBuilder {


    public static <T> ParameterizedTypeReference<T> fromTypeToken(TypeToken<T> typeToken) {
        return new TypeTokenParameterizedTypeReference<>(typeToken);
    }


}
