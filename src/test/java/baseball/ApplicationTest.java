package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Nested
    class ComputerTest {
        Computer computer = new Computer();
        @Test
        void 컴퓨터_숫자_3자리인지_테스트() {
            //given
            List<Integer> numberList = computer.getRandomNumber();
            //then
            assertThat(3).isEqualTo((numberList.size()));
        }

        @Test
        void 컴퓨터_숫자_중복없는지_테스트() {
            //given
            List<Integer> numberList = computer.getRandomNumber();
            //when
            Set<Integer> numberSet = new HashSet<>(numberList);
            //then
            assertThat(3).isEqualTo((numberSet.size()));
        }

        @Test
        void 컴퓨터_첫번째_자리수를_올바르게_가져오는지_테스트() {
            //given
            List<Integer> numberList = computer.getRandomNumber();
            //when
            int firstNumber = computer.getFirstRandomNumber();
            //then
            assertThat(numberList.get(0)).isEqualTo((firstNumber));
        }

        @Test
        void 컴퓨터_두번째_자리수를_올바르게_가져오는지_테스트() {
            //given
            List<Integer> numberList = computer.getRandomNumber();
            //when
            int secondNumber = computer.getSecondRandomNumber();
            //then
            assertThat(numberList.get(1)).isEqualTo((secondNumber));
        }

        @Test
        void 컴퓨터_세번째_자리수를_올바르게_가져오는지_테스트() {
            //given
            List<Integer> numberList = computer.getRandomNumber();
            //when
            int thirdNumber = computer.getThirdRandomNumber();
            //then
            assertThat(numberList.get(2)).isEqualTo((thirdNumber));
        }
    }

    @Nested
    class BaseBallGameTest {
        BaseBallGame baseBallGame = new BaseBallGame();
        @Test
        void 유저_숫자_입력_테스트() {
            //given
            String userNumber = "123";
            InputStream in = new ByteArrayInputStream(userNumber.getBytes());
            System.setIn(in);
            //when
            baseBallGame.inputUserNumber();
            //then
            assertThat(baseBallGame.getUserNumber()).isEqualTo(userNumber);
        }
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
