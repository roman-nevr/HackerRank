package com.roma.berendeev.dynamic;

import org.junit.Assert;
import org.junit.Test;

import static com.roma.berendeev.dynamic.MinSquaresCount.funN;
import static com.roma.berendeev.dynamic.MinSquaresCount.getMinSquares;

public class Dynamic {

    @Test
    public void minimalSquaresForCertainNumber() {
        SqrtResult sqrtResult = funN(7*7 + 7*7);
        System.out.println(sqrtResult.getNumbersString());
    }

    @Test
    public void minimalSquares() {
        for (int i = 1; i < 1000_000; i++) {
            SqrtResult sqrtPair = getMinSquares(i);
            System.out.println(""+i+": "+sqrtPair.getNumbersString());
            Assert.assertEquals(i, sqrtPair.sourceNumber);
        }
    }

    @Test
    public void minimalSquares2ForCertainNumber() {
        int number = 18;
        SqrtResult result = getMinSquares(number);
        System.out.println(number+": "+result.getNumbersString());
        Assert.assertEquals(funN(number), result);
    }
}
