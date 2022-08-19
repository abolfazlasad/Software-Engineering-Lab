import java.math.BigInteger;


public class Main {
    static BigInteger fact(int n) {
        BigInteger result = new BigInteger("1");
        if (n == 0) return result;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        return result;
    }

    static BigInteger select(int a, int b) {
        return fact(a).divide(fact(b).multiply(fact(a - b)));
    }

    static BigInteger paskal_sum_of_selections(int n){
        BigInteger result = new BigInteger("0");
        for (int i = 0; i <= n; i++) {
            result = result.add(select(n, i));
        }
        return result;
    }

    static BigInteger powof2(int n){
        BigInteger result = new BigInteger("2");
        result = result.pow(n);
        return result;
    }

    public static void main(String[] args) {
        int a = 3000;
        Utils.sleepUninterrruptable(10);
        System.out.println(powof2(a));
        Utils.sleepUninterrruptable(10);
    }
}

