package kr.co.ant.study.seomyeongjoo;

import kr.co.ant.study.reflect.ReflectQuestion;

import java.lang.reflect.Field;

public class ExampleSMJ extends ReflectQuestion {

    @Override
    public Object getValue(Object vo, String fieldName) throws NoSuchFieldException, Exception {
        Class clazz = vo.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(vo);
    }

    @Override
    public void setValue(Object vo, Object value, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Class clazz = vo.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        field.set(vo, value);
    }

    @Override
    public void copyProperties(Object orig, Object dest) throws Exception {
        Class clazz1 = orig.getClass();
        Class clazz2 = dest.getClass();

        Field[] origF = clazz1.getDeclaredFields();
        Field[] destF = clazz2.getDeclaredFields();

        for (Field field1 : origF) {
            for (Field field2 : destF) {
                if(field2.getName().equals(field1.getName())) {
                    field1.setAccessible(true);
                    field2.setAccessible(true);
                    field2.set(dest, field1.get(orig));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExampleSMJ e = new ExampleSMJ();
        e.startTest();
    }

}