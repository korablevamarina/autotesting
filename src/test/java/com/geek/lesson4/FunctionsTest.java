package com.geek.lesson4;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    //TRACE, DEBUG, INFO, WARN, ERROR
    @BeforeAll
    static void beforeAll() {
        System.out.println("Метод выполнился перед всеми тестами 1 раз");
        logger.info("Метод выполнился перед всеми тестами 1 раз");
        logger.trace("trace level log");
        logger.error("error log message");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод выполнился перед каждым тестом");
    }

    @Test
    @DisplayName("Проверка метода isPrime с простым числом")
    void givenPrimeNumberWhenIsPrimeThenTrue() {
        //Шаг тест кейса = вызвать метод с числом 7
        boolean result = Functions.isPrime(7);
        //Ожидаемый результат
        assertTrue(result);   //проверить что result == true
    }

    //@Test
    //void isPalindromeWithEvenNumberTest() {
    //    Assertions.assertTrue(Functions.isPalindrome("123321"));
    //}

    @ParameterizedTest
    @ValueSource(strings = {"123321", "12321"})
    void isPalindromeTest(String word) {
        assertTrue(Functions.isPalindrome(word));
    }

    @ParameterizedTest
    @CsvSource({"123321, true", "12, false"})
    void isNotPalindromeTest(String word, boolean result) {
        Assertions.assertEquals(result, Functions.isPalindrome(word));
    }

    @ParameterizedTest
    @MethodSource("catDataProvider")
    void parametrizedByObjectTest(Cat testCat) {
        Assertions.assertEquals(10, testCat.getAge());
    }

    private static List<Cat> catDataProvider() {
        return Arrays.asList(new Cat("Moris", 13), new Cat("Barsik", 10));
    }

    @ParameterizedTest
    @MethodSource("catDataProvider")
    void parametrizedByAnyObjectTest(Cat testCat, Boolean result, Integer a) {
        Assertions.assertEquals(10, testCat.getAge());
    }

    private static Stream<Arguments> catAndBooleanDataProvider() {
        return Stream.of(
                Arguments.of(new Cat("Moris", 13), true),
                Arguments.of(new Cat("Barsik", 10), false));
    }

    @Test
    void assumptionTest() {
        assumeTrue(1 == 2);
        assertTrue(1 == 1);
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertTrue(1 == 1),
                () -> assertTrue(1 == 2),
                () -> assertTrue(1 == 3),
                () -> assertTrue(1 == 1)
        );
    }

    @AfterEach
    void afterEach() {
        //driver.quit()
        System.out.println("Метод выполнится после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Метод выполнится после всех тестов");
    }

}
