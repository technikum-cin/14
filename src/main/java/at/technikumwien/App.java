package at.technikumwien;

public class App {
    public static void main(String[] a) {
        System.out.println("Start");
        CalculatorSimpleImpl calc = new CalculatorSimpleImpl();
        System.out.println(calc.sum(1,3));
        CalculatorExtendedImpl calc2 = new CalculatorExtendedImpl();
        System.out.println(calc2.sum(1,3,7,9));

    }
}
