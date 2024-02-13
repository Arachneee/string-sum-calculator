package org.example;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class SplitServiceTest {

    @DisplayName("커스텀 구분자 추출")
    @Test
    void isCustom() {
        // given
        // when
        boolean result = SplitService.isCustom("//a\n1a2a3");

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("더하기")
    @ParameterizedTest
    @CsvSource({"'//a\n1a2a3',6", "'//ab\n2ab5ab7',14", "'1,2,3;4',10", "'//\n1234',10"})
    void sum(String input, int expect) {
        // given
        // when
        int result = SplitService.sum(input);

        // then
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("빈문자는 0이 나온다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "//a\n"})
    @NullSource
    void empty(String value) {
        // given
        // when
        int sum = SplitService.sum(value);

        // then
        assertThat(sum).isEqualTo(0);
    }

    @DisplayName("음수는 예외가 발생한다.")
    @Test
    void negative() {
        // given
        // when
        // then
        assertThatThrownBy(() -> SplitService.sum("-1")).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("구분자로 분할되지 않으면 예외가 발생한다.")
    @Test
    void test() {
        // given
        // when
        // then
        assertThatThrownBy(() -> SplitService.sum("//a\n1a2,3")).isInstanceOf(RuntimeException.class);
    }
}