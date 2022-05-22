package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;


/**
 * 복사용 Sample
 */
class StudyTestCustomTag {

//    @Tag("fast") : 문자열을 집어넣기 때문에 오타가 발생할 수 있는 확률이 있다.
//  custom tag를 만들어서 하는 장점이 있다.

    @FastTest
    @DisplayName("스터디 만들기 ❤")
    void create_new_study() {
    }

    @SlowTest
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