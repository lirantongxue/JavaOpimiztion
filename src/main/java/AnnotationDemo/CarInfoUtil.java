package AnnotationDemo;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 测试:
 *
 * @auther liran
 * @create 2018-09-14-下午8:38
 */
public class CarInfoUtil {

    public static void getFruitInfo(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        for(Field field :fields){
            if(field.isAnnotationPresent(CheckNull.class)){
                CheckNull name = (CheckNull) field.getAnnotation(CheckNull.class);
                if(StringUtils.isBlank(name.salerName())){
                    System.out.println("name 不能为空");
                }
            }

        }
    }

}
