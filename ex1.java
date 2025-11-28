public class ex1 {
    public static void main(String[] args) {

        int[][] array = new int[5][];
        int counter = 1;

        for (int i = 0; i < array.length; ++i) {
            array[i] = new int[i + 1];

            for (int j = 0; j < array[i].length; ++j) {
                array[i][j] = counter++;
            }
        }

        System.out.println("Звичайна піраміда");
        for (int i = 0; i < array.length; ++i) {

            for (int s = 0; s < array.length - i; ++s) {
                System.out.print("  ");
            }

            for (int j = 0; j < array[i].length; ++j) {
                System.out.printf("%3d ", array[i][j]);
            }
            System.out.println();
        }

        System.out.println("Зворотна піраміда");
        for (int i = array.length - 1; i >= 0; --i) {

            for (int s = 0; s < array.length - i; ++s) {
                System.out.print("  ");
            }

            for (int j = 0; j < array[i].length; ++j) {
                System.out.printf("%3d ", array[i][j]);
            }
            System.out.println();
        }
    }
}