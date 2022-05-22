package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * chap. 조건에 따라 Test하기
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //해당 class에 속해 있는 모든 메서드에 적용
class StudyTest2 {


    @Test
    @DisplayName("스터디 만들기 ❤")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX}) //특정 OS에서만 실행되도록 설정할 수도 있다.
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11}) // 특정 Java 버전에서만 실행
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL") //assumingThat과 같은 역할
    void create_new_study() {
        //해당 환경일 때에만 실행해라.
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);
        //여기서 실패하면 뒤의 코드들은 실행되지 않는다.
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        /**
         * 환경에 따라 다르게 실행되도록 코드를 작성할 수도 있다.
         */
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("LOCAL");
            Study study = new Study(-10);
            assertNotNull(study);
        });

        assumingThat("keesun".equalsIgnoreCase(test_env), () -> {
            Study study = new Study(-10);
            assertNotNull(study);
        });
    }

    @Test
//    @Disabled //Test를 실행하지 마라
    void create1_new_study_again() {
        System.out.println("create1");
    }

    //BeforeAll은 static void로 작성해야한다.
    //Class 실행시 최초 1번만 실행
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    //Class 마지막 1번
    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    //작성된 메서드가 실행되기 전에 항상 실행됨
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    //작성된 메서드의 실행이 끝날 때 항상 실행됨
    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }
}