package org.example;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class SplitServiceTest {

    @DisplayName("커스텀 구분자 추출")
    @Test
    void test() {
        // given
        // when
        boolean result = SplitService.isCustom("//a\n1a2a3");

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("")
    @Test
    void sum() {
        // given

        // when
        int result = SplitService.sum("//a\n1a2a3");

        // then
        Assertions.assertThat(result).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "//a\n"})
    @NullSource
    void emtry(String value) {
        // given
        int sum = SplitService.sum(value);

        // when


        // then
        Assertions.assertThat(sum).isEqualTo(0);
    }

    @DisplayName("")
    @Test
    void negative() {
        // given
        Assertions.assertThatThrownBy(() -> SplitService.sum("-1")).isInstanceOf(RuntimeException.class);

        // when


        // then

    }
}