package neu.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SUNJUNYAN
 */
public class CountTestFile {
    private static String projectFolders = "/home/wwww/Wxq/out/projectSize.txt";
    private static List<String> getPomPath(String file) {
        ArrayList pomPaths = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!line.equals("")) {
                    String path = line.split(" ")[0] + "/src/test/";
                    File testFile = new File(path);
                    if (testFile.exists()) {
                        pomPaths.add(line.split(" ")[0]);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pomPaths;
    }

    private static List<String> countTestFiles() {
        ArrayList count = new ArrayList();
        for (String path : getPomPath(projectFolders)) {
            File file = new File(path + "/src/test/");
            int num = 0;
            num = findTestFiles(file, num);
            count.add(path + " " + num);
        }
        return count;
    }

    private static int findTestFiles(File father, int num) {
        File[] children = father.listFiles();
        for (File child : children) {
            if (child.getName().substring(child.getName().lastIndexOf(".") + 1).equals("java")) {
                num ++;
            }
            if (child.isDirectory()) {
                num = findTestFiles(child, num);
            }
        }
        return num;
    }

    public static void main(String []args) {
        try {
            PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(new File("/home/wwww/sensor/sjy/projectTestCount.txt"), true)));
            for (String info : countTestFiles()) {
                printer.println(info);
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
