package annotation;

import java.lang.annotation.*;

/**水果名称注解
 * Created by YuanXiang on 2017/5/31.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
