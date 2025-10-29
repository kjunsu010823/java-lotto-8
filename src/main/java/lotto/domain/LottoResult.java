package lotto.domain;

import java.util.*;

public class LottoResult {
    private final Map<LottoRank, Integer> statisticsMap = new LinkedHashMap<>();
    private double profitRate = 0.0;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Arrays.stream(LottoRank.values()).forEach(rank -> statisticsMap.put(rank, 0));

        int totalPrize = 0;
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatching(winningLotto);
            boolean bonusMatch = lotto.containsBonus(winningLotto.getBonusNumber());
            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);

            statisticsMap.put(rank, statisticsMap.get(rank) + 1);
            totalPrize += rank.getPrize();
        }

        int totalSpent = lottos.size() * 1000;

        if (totalSpent > 0) {
            double rate = (double) totalPrize / totalSpent * 100.0;
            this.profitRate = Math.round(rate * 10) / 10.0; // * 10 / 10.0
        }
    }

    public Map<LottoRank, Integer> getResults() {
        Map<LottoRank, Integer> resultsForPrint = new LinkedHashMap<>();

        resultsForPrint.put(LottoRank.FIFTH, statisticsMap.get(LottoRank.FIFTH));
        resultsForPrint.put(LottoRank.FOURTH, statisticsMap.get(LottoRank.FOURTH));
        resultsForPrint.put(LottoRank.THIRD, statisticsMap.get(LottoRank.THIRD));
        resultsForPrint.put(LottoRank.SECOND, statisticsMap.get(LottoRank.SECOND));
        resultsForPrint.put(LottoRank.FIRST, statisticsMap.get(LottoRank.FIRST));

        return resultsForPrint;
    }

    public double getProfitRate() {
        return profitRate;
    }
}