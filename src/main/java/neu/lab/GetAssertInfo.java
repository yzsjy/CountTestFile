package neu.lab;

import java.io.*;
import java.nio.file.Files;

/**
 * @author SUNJUNYAN
 */
public class GetAssertInfo {

    public static void main(String[] args) throws IOException {
        String targetPath = "C:\\Users\\SUNJUNYAN\\Desktop\\Assert\\";
        File file = new File("E:\\Sensor\\autoChangeDependencyVersionLog\\");
        int num = 0;
        if (file.exists()) {

            if (file.listFiles().length > 0) {
                for (File innerDir : file.listFiles()) {
                    if (!innerDir.exists() && innerDir.isDirectory()) {
                        continue;
                    }
                    File[] innerList = innerDir.listFiles();
                    if (innerList != null) {
                        for (File logFile : innerList) {
                            System.out.println(logFile.getPath());
                            if (check(logFile)) {
                                System.out.println("target");
                                Files.copy(logFile.toPath(), new File(targetPath + logFile.getParentFile().getName() + logFile.getName()).toPath());
                            }
                            num++;
                        }
                    }
                }
            }
        }
        System.out.println(num);
    }

    public static boolean check(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str;
        while ((str = bufferedReader.readLine()) != null) {
            if (str.contains("Assertion")) {
                return true;
            }
        }

        //close
        inputStream.close();
        bufferedReader.close();

        return false;
    }

    public void getInfo(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            int i = 1;
            while ((str = bufferedReader.readLine()) != null) {
                if (i == 1) {

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
