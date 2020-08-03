package neu.lab;

import java.io.*;
import java.util.*;

/**
 * @author SUNJUNYAN
 */
public class SortTestFile {

    private static String inputPath = "C:\\Users\\SUNJUNYAN\\Desktop\\projectTest.txt";

    public static void main(String []args) {
        try {
            PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(new File("C:\\Users\\SUNJUNYAN\\Desktop\\projectTestSort.txt"), true)));
            int i = 3000;
            Set<String>  path = getPomPath(inputPath);
            System.out.println("Size : " + path.size());
            while (!path.isEmpty() && i > 0) {
                Iterator<String> pathSize = path.iterator();
                while (pathSize.hasNext()) {
                    String line = pathSize.next();

                    if (line.split(" ")[1].equals(String.valueOf(i))) {
                        System.out.println(line);
                        printer.println(line);
                        pathSize.remove();
                    }

                }
                i--;
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Set<String> getPomPath(String file) {
        Set<String> pomPaths = new HashSet<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!line.equals("")) {
                    pomPaths.add(line);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pomPaths;
    }
}
