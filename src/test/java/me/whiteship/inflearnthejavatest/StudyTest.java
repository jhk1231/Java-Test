package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //해당 class에 속해 있는 모든 메서드에 적용
class StudyTest {


    @Test
    @DisplayName("스터디 만들기 ❤")
    @Tag("fast")
    void create_new_study() {
        Study study = new Study(-10);
        assertNotNull(study);
        /**
         * 1번 방식으로 구현을 하게 된다면 Test 성공 유무에 상관없이 String 연산을 실행한다.
         * 하지만 2번 방식처럼 람다식을 활용한다면 Test가 실패했을 때에만 연산을 수행한다.
         * 문자열 처리 비용을 신경쓴다면 람다를 활용하는것이 유리할 수 있다.
         *
         * 1. assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이" + StudyStatus.DRAFT + "여야 한다.");
         * 2. assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이" + StudyStatus.DRAFT + "여야 한다.");
         */
        //assertEquals(기댓값, 실제값, 실패시 message)
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");

        //같은 방법 lambda, non lambda
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "스터디를 처음 만들면 DRAFT 상태다.";
            }
        });
        assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 가능 인원은 0보다 커야 한다.");

    }

    /**
     * create_new_study1의 방식으로 하면
     * 만약 assertEquals에서 Test가 깨진다면 밑에 있는 assertTrue는 실행조차 되지 않는다.
     * 이 때 assert 코드를 모두 동시에 실행을 하는 방법이 있는데
     * create_new_study2에 작성해 보겠다.
     */
    @Test
    @DisplayName("스터디 만들기 ❤")
    void create_new_study2() {
        Study study = new Study(-10);

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );
    }

    @Test
    @DisplayName("예외처리 Test")
    void create_new_Exception_test() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        //Exception의 Message가 내가 예상한것이 맞는지 확인
        assertEquals("limit는 0보다 커야 한다.", exception.getMessage());
    }

    @Test
    @DisplayName("Timeout Test")
    void create_new_timeout_test() {
        // assertTimeout(A, B); B를 실행 했을 때 A 시간 안에 끝나야한다.
        // 단점: 내가 설정한 시간만큼 기다려야 하기 때문에 시간이 너무 오래 걸릴 수가 있다.
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        //내가 설정한 A시간이 넘으면 B를 모두 기다리지 않고 Test를 끝내라.
        //ThreadLocal 때문에 예상치 못한 문제가 발생할 수 있다.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        // TODO ThreadLocal를 찾아보아라. (예상치 못한 문제가 발생할 가능성이 있다.)
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