package com.tektrill.learning.java8.functional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class FPExample {

        public static void main(String[] args) throws IOException {
            FPExample ob = new FPExample();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            PerformOperation op;
            boolean ret = false;
            String ans = null;
            while (T--> 0) {
                String s = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(s);
                int ch = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                if (ch == 1) {
                    op = ob.isOdd();
                    ret = ob.checker(op, num);
                    ans = (ret) ? "ODD" : "EVEN";
                } else if (ch == 2) {
                    op = ob.isPrime();
                    ret = ob.checker(op, num);
                    ans = (ret) ? "PRIME" : "COMPOSITE";
                } else if (ch == 3) {
                    op = ob.isPalindrome();
                    ret = ob.checker(op, num);
                    ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

                }
                System.out.println(ans);
            }
        }

    private PerformOperation isPalindrome() {
        return (num) -> {
            String numStr = String.valueOf(num);
            return numStr.equals(new StringBuffer(numStr).reverse().toString());
        };
    }

    private PerformOperation isPrime() {
            return (num)->{
                IntPredicate isDivisible = index -> num % index == 0;
                return num > 1 && IntStream.range(2, num - 1).noneMatch(isDivisible);
            };
    }

    private PerformOperation isOdd() {
            return (num)->num%2!=0;
    }

    interface PerformOperation {
        boolean check(int a);
    }

    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

}
