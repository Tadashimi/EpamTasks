package main.java.Task2_resolveEquationWithClasses;

public class Discriminant {
    public static final String ONE_SOLUTION = "oneSolution";
    public static final String TWO_SOLUTIONS = "twoSolution";
    public static final String NO_SOLUTIONS = "noSolutions";
    private InputData inputData;
    private float value;

    public Discriminant(InputData inputData) {
        this.inputData = inputData;
    }

    public void calculate() {
        value = (float) (Math.pow(inputData.getB(), 2)
                - 4 * inputData.getA() * inputData.getC());
    }

    public String getStatus() {
        if (value > 0) {
            return TWO_SOLUTIONS;
        } else if (value == 0) {
            return ONE_SOLUTION;
        } else {
            return NO_SOLUTIONS;
        }
    }

    public float getSqrt() {
        return (float) Math.sqrt(value);
    }
}