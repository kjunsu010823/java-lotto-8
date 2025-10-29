package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;

public class LottoMachine {
    private static final int PRICE_PER_TICKET = 1000;

    public List<Lotto> generateLottos(int purchaseAmount) {
        int count = purchaseAmount / PRICE_PER_TICKET;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }
}
