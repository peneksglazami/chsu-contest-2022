import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "A - Раздвоение личности".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int m = n;
        int sum = n;
        while (sum < k) {
            m = m * 2;
            sum = sum + m;
        }

        int res = (k - sum + m) % (m / n) == 0
                ? (k - sum + m) / (m / n)
                : (k - sum + m) / (m / n) + 1;

        out.print(res);
    }
}