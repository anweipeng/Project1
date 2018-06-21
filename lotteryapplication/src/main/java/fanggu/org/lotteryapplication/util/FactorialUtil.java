package fanggu.org.lotteryapplication.util;

/**
 * Created by 77386 on 2018/5/28.
 */

public class FactorialUtil {

    public static void main(String[] args){

        System.out.println(factorial(3));
    }

    /**
     * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     * @param n
     * @return
     */
    private static long factorial(int n) {
        long sum = 1;
        while( n > 0 ) {
            sum = sum * n--;
        }
        return sum;
    }

    /**
     * 排列计算公式A<sup>m</sup><sub>n</sub> = n!/(n - m)!
     * @param m
     * @param n
     * @return
     */
    public static long arrangement(int n,int m) {
        return m <= n ? factorial(n) / factorial(n - m) : 0;
    }


    /**
     * 组合计算公式C<sup>m</sup><sub>n</sub> = n! / (m! * (n - m)!)
     * @param m
     * @param n
     * @return
     */
    public static long combination(int n,int m) {
        return m <= n ? factorial(n) / (factorial(m) * factorial((n - m))) : 0;
    }

}
