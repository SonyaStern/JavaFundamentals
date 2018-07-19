package javase02.task6;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Inherited
@Retention(value= RetentionPolicy.SOURCE)
public @interface SubmarinAnno {
    String name() default "Mary";
}
