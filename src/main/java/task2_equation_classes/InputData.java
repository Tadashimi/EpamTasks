package task2_equation_classes;

public class InputData {
    private Float a;
    private Float b;
    private Float c;

    public InputData(Float a, Float b, Float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isInputDataValid() {
        return (a != null) && a != 0;
    }

    public Float getA() {
        return a;
    }

    public Float getB() {
        return b;
    }

    public Float getC() {
        return c;
    }
}
