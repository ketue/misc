package com.monical.jdk.reflect;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author zijie.cao
 * @date 2018-03-07 16:15:27
 */
public class TestHelper {
    public static void testParameterizedType() {
        Field f = null;
        try {
            Field[] fields = ParameterizedTypeBean.class.getDeclaredFields();
            // 打印出所有的 Field 的 TYpe 是否属于 ParameterizedType
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                PrintUtils.print(f.getName()
                        + " getGenericType() instanceof ParameterizedType "
                        + (f.getGenericType() instanceof ParameterizedType));
            }
            getParameterizedTypeMes("map");
            getParameterizedTypeMes("entry");


        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void getParameterizedTypeMes(String fieldName) throws NoSuchFieldException {
        Field f;
        f = ParameterizedTypeBean.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        PrintUtils.print(f.getGenericType());
        boolean b = f.getGenericType() instanceof ParameterizedType;
        PrintUtils.print(b);
        if (b) {
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            PrintUtils.print(pType.getRawType());
            for (Type type : pType.getActualTypeArguments()) {
                PrintUtils.print(type);
            }
            PrintUtils.print(pType.getOwnerType()); // null
        }
    }

    public static void main(String[] args) {
        // testParameterizedType();
        testG();
    }

    static <T> Person<List<T>> common(Class<T> clazz) {
        PersonInterface<List<T>> pi = new PersonInterface<List<T>>() {
        };

        pi.print();

        Person<List<T>> person = new Person<List<T>>() {
        };
        //
        // PrintUtils.process(new Person<List<T>>());
        ParameterizedTypeReference<List<T>> responseTypeRef =
                ParameterizedTypeReferenceBuilder.fromTypeToken(
                        new TypeToken<List<T>>() {}
                                .where(new TypeParameter<T>() {}, clazz));
        ParameterizedTypeReference<List<T>> pp = new ParameterizedTypeReference<List<T>>() {
            @Override
            public Type getType() {

                Type[] types = ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments();

                for (Type type : types) {
                    PrintUtils.print("inner types : " + type);
                }
                PrintUtils.print("super type : " + super.getType());
                return super.getType();
            }
        };
        PrintUtils.print("[1]pp type : " + pp.getType());

        pp = new ParameterizedTypeReference<List<T>>() {
            @Override
            public Type getType() {
                return responseTypeRef.getType();
            }
        };

        PrintUtils.print("[2]pp type : " + pp.getType());
        // abstract class/interface ==> getClass()

        // List<T>
        // construct generic type

        return null;
    }


    static <T> void testG() {

        Person<List<ParameterizedTypeBean>> person1 = TestHelper.common(ParameterizedTypeBean.class);

        // ParameterizedTypeReference<List<T>> pp = new ParameterizedTypeReference<List<T>>() {
        // };
        // System.out.println(pp.getType());
        Person<List<ParameterizedTypeBean>> person = new Person<List<ParameterizedTypeBean>>() {
        };
        person.printGenericType();
        person.getType();
        Object genericSc = person.getClass().getGenericSuperclass();
        System.out.println("type of class ===> " + person.getClass());
        for (Type type : person.getClass().getGenericInterfaces()) {
            System.out.println("generic interface type ==> " + type);
        }
        System.out.println("genericSc ==> " + genericSc);
        String typeName = person.getClass().getTypeName();

        for (TypeVariable<? extends Class<? extends Person>> variable : person.getClass().getTypeParameters()) {
            System.out.println("variable => " + variable);
            System.out.println(variable.getGenericDeclaration());
            System.out.println(variable.getName());
            System.out.println(variable.getTypeName());

            for (Type type : variable.getBounds()) {
                System.out.println("type ==> " + type);
            }

        }

        System.out.println(typeName);
    }
}

