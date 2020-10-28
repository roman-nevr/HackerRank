package com.roma.berendeev.dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MinSquaresCount {

    private static int power2(int number) {
        return number * number;
    }

    private static int roundSqrt(int number) {
        return (int) Math.sqrt(number);
    }

    public static SqrtResult funN(int number) {
        List<SqrtResult> results = new ArrayList<>(number + 1);
        results.add(new SqrtResult(0, Collections.<Integer>emptyList()));
        for (int i = 1; i <= number; i++) {
            results.add(findMin(i, results));
        }
        return results.get(number);
    }

    private static SqrtResult findMin(int number, List<SqrtResult> results) {
        int currentNumber = roundSqrt(number);
        SqrtResult minResult = results.get(number - power2(currentNumber));

        for (int i = roundSqrt(number) - 1; i > 0; i--) {
            int rest = number - power2(i);
            SqrtResult sqrtResult = results.get(rest);
            if (minResult.count() > sqrtResult.count()) {
                minResult = sqrtResult;
                currentNumber = i;
            }
        }
        return createResult(number, currentNumber, minResult);
    }

    private static SqrtResult createResult(int sourceNumber, int currentNumber, SqrtResult previousResult) {
        List<Integer> resultNumbers = new ArrayList<>();
        resultNumbers.add(currentNumber);
        resultNumbers.addAll(previousResult.numbers);
        return new SqrtResult(sourceNumber, resultNumbers);
    }

}
