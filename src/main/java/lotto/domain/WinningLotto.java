package lotto.domain;

import java.util.List;

public class WinningLotto {
    // 1. List<Integer>가 아닌 Lotto 객체를 멤버로 가짐
    private final Lotto winningLotto;
    private final int bonusNumber;

    /**
     * [수정] 생성자에서 List<Integer> 대신
     * 이미 생성(검증)된 Lotto 객체를 받습니다.
     */
    public WinningLotto(Lotto winningLotto, int bonus) {
        // 2. Lotto 객체는 이미 검증되었으므로, 보너스 번호만 검증합니다.
        validateBonus(winningLotto, bonus); // Lotto 객체 전달
        this.winningLotto = winningLotto;
        this.bonusNumber = bonus;
    }

    /**
     * [수정] 보너스 번호 검증 시 Lotto 객체를 받아서 사용합니다.
     */
    private void validateBonus(Lotto lotto, int bonus) {
        // 3. 보너스 번호 범위 검사 (1~45)
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
        }

        // 4. 보너스 번호가 당첨 번호와 중복되는지 검사
        if (lotto.containsBonus(bonus)) { // 전달받은 lotto 객체 사용
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    // 5. winningLotto 객체의 번호를 반환하도록 수정
    public List<Integer> getWinningNumbers() {
        return winningLotto.getNumbers(); // Lotto의 불변 리스트 반환
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}