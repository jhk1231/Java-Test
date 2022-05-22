package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * 태깅,
 */
class StudyTest3 {


    /**
     * 상황
     * fast tag가 붙은것은 local에서만 실행시키고 싶고
     * slow tag가 붙은것은 ci 환경에서만 실행시키고 싶다.
     *
     * 방법1.
     * - Edit Configuration에서 Tag를 fast로 설정해서 실행시킨다.
     */

    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow")
    void create1_new_study_tag() {
        System.out.println("create1");
    }

    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast")
    void create1_new_study_tag2() {
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