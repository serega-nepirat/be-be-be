public class Ex4 {
    public static void main(String[] args) {
        int start = 0;
        int end = 90;
        int step = 1;
        int count = 0;

        System.out.println("Таблиця значень синуса від 0 до 90 градусів:");
        for (int degree = start; degree <= end; degree += step) {
            double rad = Math.toRadians(degree);
            double sin = Math.sin(rad);
            System.out.print(degree + "° = " + String.format("%.4f", sin) + "\t");
            count++;
            if (count == 10) {
                System.out.println();
                count = 0;
            }
        }
    }
}
