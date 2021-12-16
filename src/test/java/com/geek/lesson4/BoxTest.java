package com.geek.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoxTest {
    Box box;

    @Nested
    class WhenEmpty {
        @BeforeEach
        void initializeBox() {
            box = new Box();
        }

        @Test
        void exceptionWhenTryToShuffleEmptyBox() {
            Assertions.assertThrows(ZeroBallsCountException.class, () -> box.shuffleBalls());
            assertThatExceptionOfType(ZeroBallsCountException.class).isThrownBy(() -> box.shuffleBalls());
        }

        @Test
        void exceptionWhenTryToRemoveBallFromEmptyBox() {
            assertThatNullPointerException().isThrownBy(() -> box.removeBall());
        }

        @Nested
        class WhenOneBall {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void deleteBallTest() {
                box.removeBall();
                assertThat(box.getBallsCounter()).isEqualTo(0);
            }
        }
    }
}
