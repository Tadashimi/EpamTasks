package Task2_resolveEquationWithClasses;

public class OutputData {

    private Float firstSolution;
    private Float secondSolution;

    public void printSolutions() {
        if (firstSolution == null && secondSolution == null) {
            System.out.println("No solutions!");
        } else {
            System.out.println("First root " + firstSolution);
            System.out.println("Second root " + secondSolution);
        }
    }

    public Float getFirstSolution() {
        return firstSolution;
    }

    public void setFirstSolution(Float firstSolution) {
        this.firstSolution = firstSolution;
    }

    public Float getSecondSolution() {
        return secondSolution;
    }

    public void setSecondSolution(Float secondSolution) {
        this.secondSolution = secondSolution;
    }
}
