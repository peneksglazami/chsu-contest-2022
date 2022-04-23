import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "G - Загадки местности".
 */
public class solver {

    private static final char[][][] DETAILS = {
            {
                    {'o', 'x', ' '},
                    {'x', ' ', ' '},
                    {' ', ' ', ' '}
            },
            {
                    {' ', 'x', 'x'},
                    {'x', 'o', ' '},
                    {' ', ' ', ' '}
            },
            {
                    {'x', 'o', 'x'},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            },
            {
                    {'x', 'x', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            },
            {
                    {'x', ' ', ' '},
                    {'x', 'x', 'x'},
                    {' ', ' ', ' '}
            },
            {
                    {' ', ' ', 'o'},
                    {'x', 'x', 'x'},
                    {' ', ' ', ' '}
            },
    };


    private static boolean[][] trees;
    private static int[][] res;

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        trees = new boolean[4][5];
        for (int i = 0; i < 4; i++) {
            String s = in.nextLine();
            for (int j = 0; j < 5; j++) {
                trees[i][j] = s.charAt(j) == '1';
            }
        }

        res = new int[4][5];
        out.print(find(0, out) ? "YES" : "NO");
    }

    private static boolean find(int detailNum, PrintStream out) {
        if (detailNum == DETAILS.length) {
            return true;
        }

        for (int rot = 0; rot < 4; rot++) {

            DETAILS[detailNum] = rotate(DETAILS[detailNum]);

            for (int i = -2; i < 4; i++) {
                for (int j = -2; j < 5; j++) {
                    boolean correct = true;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (DETAILS[detailNum][k][l] != ' ') {
                                if ((i + k < 0) || (j + l < 0)
                                        || (i + k >= 4) || (j + l >= 5)
                                        || res[i + k][j + l] != 0
                                        || ((DETAILS[detailNum][k][l] == 'x') && trees[i + k][j + l])
                                        || ((DETAILS[detailNum][k][l] == 'o') && !trees[i + k][j + l])) {
                                    correct = false;
                                }
                            }
                        }
                    }

                    if (correct) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (DETAILS[detailNum][k][l] != ' ') {
                                    res[i + k][j + l] = detailNum + 1;
                                }
                            }
                        }
                        if (find(detailNum + 1, out)) {
                            return true;
                        }
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (DETAILS[detailNum][k][l] != ' ') {
                                    res[i + k][j + l] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static char[][] rotate(char[][] detail) {
        char[][] res = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[j][2 - i] = detail[i][j];
            }
        }
        return res;
    }
}