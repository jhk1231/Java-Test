package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;


/**
 * Test Instance
 */

/**
 *  Class마다 Instance를 사용하기 때문에 모두 공유하게 된다.
 *  PER_CLASS 전략을 사용한다면 BeforeAll과 AfterAll를 static으로 구현하지 않아도 된다.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyTestInstance {

    //Test간의 의존성을 삭제하기 위해 서로 다른 객체를 사용한다.
    //Test간의 의존성을 없게 만드는것이 좋다.
    int value = 1;


    @SlowTest
    void create_new_study() {
        System.out.println("this = " + this);
    }

    @FastTest
    void create1_new_study_again() {
        System.out.println("value = " + value++);
        Study actual = new Study(1);
        System.out.println("create1");
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    //Class 마지막 1번
    @AfterAll
    void afterAll() {
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