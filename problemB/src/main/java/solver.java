import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Решение задачи "B - Закон обезнуления".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        String a = in.nextLine();
        String b = in.nextLine();
        String c = new BigInteger(a).add(new BigInteger(b)).toString();

        a = a.replace("0", "");
        b = b.replace("0", "");
        c = c.replace("0", "");
        if (new BigInteger(a).add(new BigInteger(b)).equals(new BigInteger(c))) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }
}