import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {

            String data = "Just some text";
            FileWriter fw = new FileWriter("source.txt");
            fw.write(data);
            fw.close();


            FileReader fr = new FileReader("source.txt");

            try (FileWriter fr1 = new FileWriter("copy.txt")) {
                int writeCopy;
                while ((writeCopy = fr.read()) != -1) {
                    fr1.write(writeCopy);
                }
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            File newDIR = new File("newDIR/");
            if (!newDIR.exists()) {
                newDIR.mkdirs();
            } else {
                System.out.println("Directory OK");
            }


            Path sourceFilePath = Paths.get("source.txt");
            Path destinationFilePath = Paths.get("newDIR/source.txt");
            try {
                if (Files.exists(destinationFilePath)) {
                    System.out.println("source in newDIR checked");
                    Files.delete(sourceFilePath);
                } else {
                    Files.move(sourceFilePath, destinationFilePath);
                    System.out.println("file has been removed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            Path copyFilePath = Paths.get("copy.txt");
            if (Files.mismatch(copyFilePath, destinationFilePath) == -1) {
                System.out.println("content is the same");
            } else {
                System.out.println("content is differ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}