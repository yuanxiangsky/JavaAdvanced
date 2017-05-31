package annotation;

import java.lang.reflect.Field;

/**注解处理器，注解需要生效需要配合注解处理器一起使用
 * Created by YuanXiang on 2017/5/31.
 */
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> cl){

        String strFruitName = "水果名称：";
        String strFruitColor ="水果颜色：";
        String strFruitProvider ="水果供应商：";

        Field[] fields = cl.getDeclaredFields();

        for(Field field:fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName)field.getAnnotation(FruitName.class);
                strFruitName +=fruitName.value();
                System.out.println(strFruitName);
            }else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor = (FruitColor)field.getAnnotation(FruitColor.class);
                strFruitColor += fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvider =strFruitProvider + fruitProvider.id() + fruitProvider.name() +fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }

    }

    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}
