class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль заборонено.");
        }
        return a / b;
    }

    public double squareRoot(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Корінь із від'ємного числа не визначений.");
        }
        return Math.sqrt(a);
    }
}
