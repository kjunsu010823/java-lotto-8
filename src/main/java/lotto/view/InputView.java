package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto; // [수정] Lotto 임포트
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String ERROR_INVALID_NUMBER = "[ERROR] 유효한 숫자를 입력해야 합니다.";
    private static final String ERROR_INVALID_WINNING_NUMBERS = "[ERROR] 쉼표(,)로 구분된 숫자 6개를 올바르게 입력해야 합니다.";

    /**
     * 구입 금액을 입력받습니다.
     * 숫자가 아니면 [ERROR] 메시지 출력 후 다시 입력받습니다.
     */
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

    /**
     * [수정] 당첨 번호와 보너스 번호 입력을 분리된 루프로 처리합니다.
     */
    public WinningLotto inputWinningLotto() {
        // 1. 유효한 'Lotto'(당첨 번호)를 얻을 때까지 반복
        Lotto lotto = readValidWinningNumbers();

        // 2. 유효한 'WinningLotto'(보너스 번호)를 얻을 때까지 반복
        return readValidBonusNumber(lotto);
    }

    /**
     * [신규] 유효한 Lotto(당첨 번호) 객체가 생성될 때까지 입력을 반복하는 메소드
     */
    private Lotto readValidWinningNumbers() {
        while (true) {
            try {
                List<Integer> numbers = readWinningNumbersInternal(); // 숫자 형식만 검증
                return new Lotto(numbers); // 6개, 중복, 범위 검증 (예외 발생 가능)
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Lotto 예외 출력 후 다시 시도
            }
        }
    }

    /**
     * [신규] 유효한 WinningLotto(보너스 번호) 객체가 생성될 때까지 입력을 반복하는 메소드
     */
    private WinningLotto readValidBonusNumber(Lotto lotto) {
        while (true) {
            try {
                int bonus = readBonusNumberInternal(); // 숫자 형식만 검증
                return new WinningLotto(lotto, bonus); // 보너스 중복, 범위 검증 (예외 발생 가능)
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // WinningLotto 예외 출력 후 다시 시도
            }
        }
    }

    /**
     * [이름 변경] 당첨 번호를 입력받아 List로 반환 (숫자 형식만 검증)
     * (기존: readWinningNumbers)
     */
    private List<Integer> readWinningNumbersInternal() {
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

    /**
     * [이름 변경] 보너스 번호를 입력받아 int로 반환 (숫자 형식만 검증)
     * (기존: readBonusNumber)
     */
    private int readBonusNumberInternal() {
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