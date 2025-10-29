package lotto.view;

import lotto.domain.*;

import java.util.List;

public class OutputView {
    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printResult(LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        result.getResults().forEach((rank, count) ->
                System.out.println(rank.getDescription() + " - " + count + "개")
        );
        System.out.println("총 수익률: " + result.getProfitRate() + "%");
    }
}
