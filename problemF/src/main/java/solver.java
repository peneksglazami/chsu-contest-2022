import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Решение задачи "F - Лекарство для мух".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    private static boolean links[][];
    private static boolean v[];
    private static int p[];
    private static int q[];
    private static int n;
    private static int k;

    private static boolean tryFind(int i) {
        if (v[i]) {
            return false;
        }

        v[i] = true;
        for (int j = 0; j < n; j++) {
            if (links[i][j] && ((p[j] == -1) || tryFind(p[j]))) {
                p[j] = i;
                return true;
            }
        }

        return false;
    }

    static void solve(Scanner in, PrintStream out) {
        n = in.nextInt();
        k = in.nextInt();

        links = new boolean[k][n];
        p = new int[n];
        Arrays.fill(p, -1);

        for (int i = 0; i < k; i++) {
            int m = in.nextInt();
            while (m-- > 0) {
                links[i][in.nextInt() - 1] = true;
            }
        }

        int res = 0;
        for (int i = 0; i < k; i++) {
            v = new boolean[k];
            if (tryFind(i)) {
                res++;
            }
        }

        out.print(res);
    }
}