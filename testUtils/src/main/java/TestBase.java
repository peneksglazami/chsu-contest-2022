import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class TestBase {

    @FunctionalInterface
    interface Solver {
        void solve(Scanner in, PrintStream out);
    }

    private boolean contentEquals(File file1, File file2) throws FileNotFoundException {
        Scanner in1 = new Scanner(file1);
        Scanner in2 = new Scanner(file2);
        while (in1.hasNext() && in2.hasNext()) {
            String st1 = in1.nextLine().trim();
            String st2 = in2.nextLine().trim();
            if (!st1.equals(st2)) {
                return false;
            }
        }
        return !(in1.hasNext() || in2.hasNext());
    }

    protected void checkSolution(Solver solver) {
        List<String> errors = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("./src/test/resources"))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().matches("[0-9]{2}"))
                    .forEach(path -> {
                        String outputFilename = path.getFileName() + ".a";
                        File outputFile = Paths.get("./src/test/resources/" + outputFilename).toFile();
                        File answerFile = new File("answer");
                        try (Scanner in = new Scanner(path).useLocale(Locale.US);
                             PrintStream out = new PrintStream(answerFile)
                        ) {
                            solver.solve(in, out);
                            if (!contentEquals(outputFile, answerFile)) {
                                String error = "Correct answer in " + outputFilename + ":\n" +
                                        FileUtils.readFileToString(outputFile, "UTF-8") + "\n" +
                                        "Wrong answer:\n" +
                                        FileUtils.readFileToString(answerFile, "UTF-8") + "\n";
                                errors.add(error);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            answerFile.delete();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            fail();
        }
    }
}
