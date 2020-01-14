package task2_equation_classes;

public class EquationResolverStarter {
    public static void main(String[] args) {
        QuadraticEquation quadraticEquation =
                new QuadraticEquation(
                        new InputData(1f, 2f, 3f)
                );
        OutputData result = quadraticEquation.resolve();
        result.printSolutions();
    }
}
