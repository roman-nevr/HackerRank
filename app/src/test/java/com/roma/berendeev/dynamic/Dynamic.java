package com.roma.berendeev.dynamic;

import org.junit.Assert;
import org.junit.Test;

import static com.roma.berendeev.dynamic.MinSquaresCount.funN;

public class Dynamic {

    @Test
    public void minimalSquares2ForCertainNumber() {
        SqrtResult sqrtResult = funN(7*7 + 7*7);
        System.out.println(sqrtResult.getNumbersString());
    }

    @Test
    public void minimalSquares2() {

        for (int i = 1; i < 1000_000; i++) {
            SqrtResult sqrtPair = funN(i);
            System.out.println(""+i+": "+sqrtPair.getNumbersString());
            Assert.assertEquals(i, sqrtPair.sourceNumber);
        }
    }
}
