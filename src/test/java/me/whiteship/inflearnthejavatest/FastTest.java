package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //이 어노테이션은 어디에 사용할 수 있는지 : Method에서 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME) // 런타임시에도 유지해라.
@Test
@Tag("fast")
public @interface FastTest {
    //@Test와 @Tag("fast")를 적용
}
