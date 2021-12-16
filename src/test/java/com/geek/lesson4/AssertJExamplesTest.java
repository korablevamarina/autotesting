package com.geek.lesson4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {
    @Test
    void assertJExamples() {
        assertThat(Functions.isPalindrome("123321")).isTrue();
    }

    @Test
    void testCollection() {
        List<String> strings = new ArrayList<>(Arrays.asList("test", "test1", "test2"));
        assertThat(strings).containsAnyOf("test", "test3");
        assertThat(1).isLessThan(2).isGreaterThan(0);
    }
}
