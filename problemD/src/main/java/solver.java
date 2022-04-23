import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "D - Робот-доставщик".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] x = new int[m];
        int[] y = new int[m];

        for (int i = 0; i < m; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if ((x[i] > x[j]) || (x[i] == x[j] && y[i] > y[j])) {
                    int tmp = x[i];
                    x[i] = x[j];
                    x[j] = tmp;
                    tmp = y[i];
                    y[i] = y[j];
                    y[j] = tmp;
                }
            }
        }

        boolean ok = true;
        for (int i = 0; i < m - 1; i++) {
            if (y[i] > y[i + 1]) {
                ok = false;
                break;
            }
        }

        out.print(ok ? "YES" : "NO");
    }
}