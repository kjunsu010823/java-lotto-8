package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String ERROR_INVALID_NUMBER = "[ERROR] 유효한 숫자를 입력해야 합니다.";
    private static final String ERROR_INVALID_WINNING_NUMBERS = "[ERROR] 쉼표(,)로 구분된 숫자 6개를 올바르게 입력해야 합니다.";

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                return Integer.parseInt(input); // 성공 시 값 반환 및 루프 종료
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INVALID_NUMBER); // 실패 시 오류 출력 후 루프 계속
            }
        }
    }

    public WinningLotto inputWinningLotto() {
        List<Integer> winningNumbers = readWinningNumbers(); // 당첨 번호 읽기
        int bonus = readBonusNumber(); // 보너스 번호 읽기

        return new WinningLotto(winningNumbers, bonus);
    }

    private List<Integer> readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                String[] numbers = Console.readLine().split(",");
                List<Integer> winningNumbers = new ArrayList<>();
                for (String n : numbers) {
                    winningNumbers.add(Integer.parseInt(n.trim()));
                }

                return winningNumbers; // 성공 시 리스트 반환
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INVALID_WINNING_NUMBERS); // 숫자 변환 실패
            }
        }
    }

    private int readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                return Integer.parseInt(input); // 성공 시 값 반환
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INVALID_NUMBER); // 실패 시 오류 출력
            }
        }
    }
}
