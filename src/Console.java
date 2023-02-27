import java.util.Scanner;

public class Console {
    private Scanner sc = new Scanner(System.in);

    public String promptAndGetString(String prompt) {
        System.out.print(prompt);
        String ret = sc.nextLine();
        return ret;
    }

    public int promptAndGetInt(String prompt) {
        System.out.print(prompt);
        int num = sc.nextInt();
        sc.nextLine(); // Clear buffer
        return num;
    }

    public int promptAndGetInt(String prompt, int min, int max) {
        System.out.print(prompt);
        try {
            int num = sc.nextInt();

            if (num < min || num > max)
                throw new RuntimeException("Número fora do intervalo permitido!");

            return num;
        } finally {
            sc.nextLine(); // Clear buffer
        }
    }

    public byte promptAndGetByte(String prompt) {
        System.out.print(prompt);
        byte ret = sc.nextByte();
        sc.nextLine();
        return ret;
    }

    public <T> void printSquareMatrix(T[][] matrix, int size) {
        for (int i=0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
