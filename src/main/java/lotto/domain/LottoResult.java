package lotto.domain;

import java.util.*;

public class LottoResult {
    private final Map<LottoRank, Integer> resultMap = new LinkedHashMap<>();
    private double profitRate = 0.0;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Arrays.stream(LottoRank.values()).forEach(rank -> resultMap.put(rank, 0));

        int totalPrize = 0;
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatching(winningLotto);
            boolean bonusMatch = lotto.containsBonus(winningLotto.getBonusNumber());
            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);

            resultMap.put(rank, resultMap.get(rank) + 1);
            totalPrize += rank.getPrize();
        }

        int totalSpent = lottos.size() * 1000;
        this.profitRate = Math.round((double) totalPrize / totalSpent * 100 * 100) / 100.0;
    }

    public Map<LottoRank, Integer> getResults() {
        return resultMap;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
