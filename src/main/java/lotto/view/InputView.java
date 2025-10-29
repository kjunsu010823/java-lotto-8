package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.WinningLotto;
import java.util.*;

public class InputView {

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
    }

    public WinningLotto inputWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] numbers = Console.readLine().split(",");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String n : numbers) {
            winningNumbers.add(Integer.parseInt(n.trim()));
        }

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(Console.readLine());

        return new WinningLotto(winningNumbers, bonus);
    }
}
