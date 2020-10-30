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

    //     1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
    //1^2: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
    //2^2: 1 2 3 1 2 3 4 2 3 4  5  3  4  5  6  4  5  6
    //3^2: 1 2 3 1 2 3 4 2 1 2  3  3  2  3  4  5  2  2
    //4^2: 1 2 3 1 2 3 4 2 1 2  3  3  2  3  4  1  2  2

    public static SqrtResult getMinSquares(int number) {
        int maxSquareRoot = roundSqrt(number);
        int[][] results = new int[maxSquareRoot + 1][number + 1];
        for (int j = 0; j <= number; j++) {
            results[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= maxSquareRoot; i++) {
            for (int j = 1; j <= number; j++) {
                if (j < i*i) {
                    results[i][j] = results[i - 1][j];
                } else {
                    results[i][j] = Math.min(results[i][j - i * i] + 1, results[i - 1][j]);
                }
            }
        }

        //restore numbers
        int j = number;
        int i = maxSquareRoot;
        int count = results[i][j];
        List<Integer> numbers = new ArrayList<>();
        while (count > 0) {
            if (count < results[i - 1][j]) {
                j = j - i*i;
                numbers.add(i);
                count = results[i][j];
            } else {
                i--;
            }
        }
        return new SqrtResult(number, numbers);
    }

}
