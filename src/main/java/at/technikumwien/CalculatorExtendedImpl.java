package at.technikumwien;

public class CalculatorExtendedImpl implements Calculator {
    @Override
    public int sum(int... numbers) {
        if (numbers.length <= 0) {
            return 0;
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
