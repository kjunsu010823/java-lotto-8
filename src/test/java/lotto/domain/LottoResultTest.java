package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        // 테스트에서 공통으로 사용할 당첨 번호 설정
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        winningLotto = new WinningLotto(lotto, 7); // 보너스 7
    }

    @DisplayName("수익률을 올바르게 계산한다 (예: 8000원 투자, 5000원 당첨)")
    @Test
    void 수익률_계산_테스트() {
        // 5등 (3개 일치) 1개
        Lotto fifthPlace = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        // 꽝 (2개 일치) 7개
        List<Lotto> lottos = List.of(
                fifthPlace,
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13))
        );
        // 총 8000원 구매, 5000원 당첨
        LottoResult result = new LottoResult(lottos, winningLotto);

        // 수익률 (5000 / 8000) * 100 = 62.5%
        assertThat(result.getProfitRate()).isEqualTo(62.5);
    }

    @DisplayName("getResults가 꽝(NONE)을 제외하고 오름차순으로 맵을 반환한다.")
    @Test
    void 결과_맵_순서_및_꽝_제외_테스트() {
        Lotto fifthPlace = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        List<Lotto> lottos = List.of(fifthPlace);

        LottoResult result = new LottoResult(lottos, winningLotto);
        Map<LottoRank, Integer> resultMap = result.getResults();

        // 1. 꽝(NONE)이 없는지 확인
        assertThat(resultMap).doesNotContainKey(LottoRank.NONE);

        // 2. 5등이 1개인지 확인
        assertThat(resultMap.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(resultMap.get(LottoRank.FOURTH)).isEqualTo(0);

        // 3. 순서가 5등 -> 1등 (오름차순)인지 확인
        assertThat(resultMap.keySet()).containsExactly(
                LottoRank.FIFTH,
                LottoRank.FOURTH,
                LottoRank.THIRD,
                LottoRank.SECOND,
                LottoRank.FIRST
        );
    }
}