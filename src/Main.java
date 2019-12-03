public class Main {

    public static void main(String[] args) {
        resolveAcq(4, -7, 0);
        resolveAcq(5, 0, 0);
        resolveAcq(1, 2, 3);
    }

    public static void resolveAcq(float a, float b, float c) {
        System.out.println("a = " + a + " b = " + b + " c = " + c);
        float d = b * b - 4 * a * c;
        if (d > 0) {
            float x1 = (float) (-b + Math.sqrt(d)) / (2 * a);
            float x2 = (float) (-b - Math.sqrt(d)) / (2 * a);
            System.out.printf("Results:\n x1 = %.4f \n x2 = %.4f\n", x1, x2);
        } else if (d == 0) {
            float x = -b / (2 * a);
            System.out.printf("Result:\n x = %.4f\n", x);
        } else {
            float realPart = -b / (2 * a);
            float imgPart = (float) Math.sqrt(-d) / (2 * a);
            System.out.printf("Results:\n x1 = %.4f + %.4f*i", realPart, imgPart);
            System.out.printf("\n x2 = %.4f - %.4f*i", realPart, imgPart);
        }
    }
}
