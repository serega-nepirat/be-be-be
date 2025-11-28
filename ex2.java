public class ex2 {
    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        double[][] array = new double[rows][cols];

        System.out.println("Генерація та обробка масиву");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                double originalValue = Math.random() * 100;
                array[i][j] = originalValue;

                if (i % 2 != 0 || j % 2 != 0) {
                    array[i][j] = Math.sqrt(array[i][j]);
                }
            }
        }

        System.out.println("Результуючий масив:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%.2f\t", array[i][j]);
            }
            System.out.println();
        }
    }
}