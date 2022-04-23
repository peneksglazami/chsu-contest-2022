import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * Решение задачи "C - Колорадский жук".
 */
public class solver {

    private static double EPSILON = 0.0000000000001;

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt")).useLocale(Locale.US)) {
            solve(in, System.out);
        }
    }

    // Точное решение
    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        double r = in.nextDouble();

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextDouble();
            y[i] = in.nextDouble();
        }

        int max = 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dist(x[i], y[i], x[j], y[j]) - 2 * r <= EPSILON) {
                    // Оба жука могут лежать на окружности.
                    // Найдём центры обеих окружностей и посчитаем количество жуков, которые попадают в эти окружности.

                    double x0 = (x[i] + x[j]) / 2;
                    double y0 = (y[i] + y[j]) / 2;
                    double d = dist(x[i], y[i], x[j], y[j]) / 2;
                    double h = Math.sqrt(r * r - d * d);
                    double l = dist(x[i], y[i], x[j], y[j]);
                    double deltaX = (y[j] - y[i]) * h / l;
                    double deltaY = (x[i] - x[j]) * h / l;

                    double xc = x0 + deltaX;
                    double yc = y0 + deltaY;
                    int cnt = 0;
                    for (int k = 0; k < n; k++) {
                        if (dist(x[k], y[k], xc, yc) - r <= EPSILON) {
                            cnt++;
                        }
                    }
                    if (cnt > max) {
                        max = cnt;
                    }

                    xc = x0 - deltaX;
                    yc = y0 - deltaY;
                    cnt = 0;
                    for (int k = 0; k < n; k++) {
                        if (dist(x[k], y[k], xc, yc) - r <= EPSILON) {
                            cnt++;
                        }
                    }
                    if (cnt > max) {
                        max = cnt;
                    }
                }
            }
        }

        out.print(max);
    }

    private static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Приближенное решение, которое проходит все тесты
    static void solve2(Scanner in, PrintStream out) {
        int n = in.nextInt();
        double r = in.nextDouble();

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextDouble();
            y[i] = in.nextDouble();
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 360; j++) {
                double rad = Math.toRadians(j);
                double xc = x[i] + r * Math.cos(rad);
                double yc = y[i] + r * Math.sin(rad);

                int cnt = 0;
                for (int k = 0; k < n; k++) {
                    if (dist(x[k], y[k], xc, yc) - r <= EPSILON) {
                        cnt++;
                    }
                }
                if (cnt > max) {
                    max = cnt;
                }
            }
        }

        out.print(max);
    }

    // Приближенное решение, которое проходит все тесты
    static void solve3(Scanner in, PrintStream out) {
        int n = in.nextInt();
        double r = in.nextDouble();

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextDouble();
            y[i] = in.nextDouble();
        }

        int max = 1;
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {

                double xc = ((double) i) / 10;
                double yc = ((double) j) / 10;

                int cnt = 0;
                for (int k = 0; k < n; k++) {
                    if (dist(x[k], y[k], xc, yc) - r <= EPSILON) {
                        cnt++;
                    }
                }
                if (cnt > max) {
                    max = cnt;
                }
            }
        }

        out.print(max);
    }
}