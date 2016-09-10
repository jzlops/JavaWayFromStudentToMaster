package ru.stikhonov.term4;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class Factorial {
    /**
     * @param index факториал требуемой велечины
     * @return фаториал значения index
     */
    public float calculate(int index) {
        long result = 1;
        if (index <= 0) {
            return 1;
        } else {
            for (int i = 2; i <= index; i++) {
                result = result * i;
            }
            return result;
        }
    }
}