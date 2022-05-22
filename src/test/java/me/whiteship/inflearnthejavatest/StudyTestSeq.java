package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;


/**
 * Test 실행 순서
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order Annotation으로 순서 조절(낮은 순으로 실행)
class StudyTestSeq {
    
    //Test간의 의존성을 삭제하기 위해 서로 다른 객체를 사용한다.
    //Test간의 의존성을 없게 만드는것이 좋다.
    int value = 1;


    @Order(2)
    @SlowTest
    void create_new_study() {
        System.out.println("this = " + this);
    }

    @Order(1) //Junit의 Order를 사용해야함
    @FastTest
    void create1_new_study_again() {
        System.out.println("value = " + value++);
        Study actual = new Study(1);
        System.out.println("create1");
    }

    @Order(3)
    @FastTest
    void create3() {

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