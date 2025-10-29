package lotto.domain;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호가 있습니다.");
        }

        boolean outOfRange = numbers.stream().anyMatch(n -> n < 1 || n > 45);
        if (outOfRange) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 범위여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int countMatching(WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto.getWinningNumbers()::contains)
                .count();
    }

    public boolean containsBonus(int bonus) {
        return numbers.contains(bonus);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
