package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * Test 반복하기
 */
class StudyTestLoop {

    //반복 횟수
    @DisplayName("스터디 만들기 반복")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}") // name을 통해서 테스트의 DisplayName들도 변경할 수 있다.
    void create_study(RepetitionInfo repetitionInfo) {
        //몇번이나 반복해야하는지도 알 수 있다.
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //5는 기본제공 4는 서드파티 필요 message {0}은 파라미터의 순서 위치
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println("message = " + message);
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