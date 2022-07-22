package calculator;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public int mul(int a, int b) {
        return a * b;
    }
    public int div(int a, int b) {
        if (b == 0)
            return 0;
        return a / b;
    }
    public int pow(int a, int b) {
        if (b < 0)
            return 0;
        else if (b == 0)
            return 1;
        else
            return mul(pow(a, b-1), a);
    }
}
