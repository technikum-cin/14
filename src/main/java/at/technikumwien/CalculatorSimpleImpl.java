package at.technikumwien;

public class CalculatorSimpleImpl implements Calculator {
    @Override
    public int sum(int... numbers) {
        if (numbers.length <= 0) {
            return 0;
        }

        if (numbers.length > 2) {
            throw new IllegalArgumentException("Too many numbers");
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
