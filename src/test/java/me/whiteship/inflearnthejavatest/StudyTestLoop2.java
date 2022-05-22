package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test 반복하기2
 */
class StudyTestLoop2 {

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

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //5는 기본제공 4는 서드파티 필요 message {0}은 파라미터의 순서 위치
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    @EmptySource // 비어있는 문자열 삽입
    @NullSource // null 삽입
    @NullAndEmptySource // Empty, Null 소스 결합
    void parameterizedTest2(String message) {
        System.out.println("message = " + message);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //5는 기본제공 4는 서드파티 필요 message {0}은 파라미터의 순서 위치
    @ValueSource(ints = {10,20,30})
    void parameterizedTest3(Integer limi) {
        System.out.println("limi = " + limi);
    }
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //5는 기본제공 4는 서드파티 필요 message {0}은 파라미터의 순서 위치
    @ValueSource(ints = {10,20,30})
    void parameterizedTest4(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println("limi = " + study.getLimit());
    }

    //1개의 Argument만 받을 수 있다.
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //5는 기본제공 4는 서드파티 필요 message {0}은 파라미터의 순서 위치
    @CsvSource({"10, 'java'", "20, 스프링"})
    void parameterizedTest5(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    /**
     * 반드시 static inner class이거나 public class로 작성해야 한다.
     */
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            Study st = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
            return st;
        }
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