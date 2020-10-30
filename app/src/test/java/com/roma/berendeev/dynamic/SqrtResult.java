package com.roma.berendeev.dynamic;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqrtResult that = (SqrtResult) o;
        return sourceNumber == that.sourceNumber &&
                numbers.equals(that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceNumber, numbers);
    }

    @Override
    public String toString() {
        return "SqrtResult{" +
                "sourceNumber=" + sourceNumber +
                ", numbers=" + numbers +
                '}';
    }
}
