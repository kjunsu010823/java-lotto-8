package lotto;

import lotto.domain.*;
import lotto.view.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        // 1. 입력
        int purchaseAmount = inputView.inputPurchaseAmount();

        // 2. 로또 생성
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> purchasedLottos = lottoMachine.generateLottos(purchaseAmount);

        // 3. 구매한 로또 출력
        outputView.printPurchasedLottos(purchasedLottos);

        // 4. 당첨 번호 입력
        WinningLotto winningLotto = inputView.inputWinningLotto();

        // 5. 결과 계산
        LottoResult lottoResult = new LottoResult(purchasedLottos, winningLotto);

        // 6. 결과 출력
        outputView.printResult(lottoResult);
    }
}
