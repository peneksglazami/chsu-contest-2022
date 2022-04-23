import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "E - Распил".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static int[] d;
    static int [][] tt;

    private static int t(int n, int m) {
        if (tt[n][m] == -1) {
            tt[n][m] = Integer.MAX_VALUE;
            for (int i = n + 1; i <= m - 1; i++) {
                if (tt[n][m] > t(n, i) + t(i, m) + d[m] - d[n]) {
                    tt[n][m] = t(n, i) + t(i, m) + d[m] - d[n];
                }
            }
        }
        return tt[n][m];
    }

    static void solve(Scanner in, PrintStream out) {
        int l = in.nextInt();
        int n = in.nextInt();
        d = new int[n + 3];
        tt = new int [n + 3][n + 3];

        d[1] = 0;
        d[n + 2] = l;
        for (int i = 1; i <= n; i++) {
            d[i + 1] = in.nextInt();
        }

        for (int i = 1; i <= n + 1; i++) {
            for (int j = i + 1; j <= n + 2; j++) {
                tt[i][j] = -1;
            }
        }

        for (int i = 1; i <= n + 1; i++) {
            tt[i][i + 1] = 0;
        }

        out.print(t(1, n + 2));
    }
}


