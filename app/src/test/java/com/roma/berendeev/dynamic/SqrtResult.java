package com.roma.berendeev.dynamic;

import java.util.List;

class SqrtResult {

    final int sourceNumber;
    final List<Integer> numbers;

    public SqrtResult(int sourceNumber, List<Integer> numbers) {
        this.sourceNumber = sourceNumber;
        this.numbers = numbers;
    }

    public int count() {
        return numbers.size();
    }

    public String getNumbersString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer number : numbers) {
            stringBuilder.append(number);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
