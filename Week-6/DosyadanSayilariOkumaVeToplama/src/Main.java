import java.io.*;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        File file = new File("src/toplama.txt");


        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write("5\n10\n20\n12\n33");
            bufferedWriter.close();

            FileReader readFile = new FileReader(file);
            BufferedReader buffRead = new BufferedReader(readFile);
            String line = buffRead.readLine();
            while (line != null) {
                int number = Integer.parseInt(line);
                result += number;
                line = buffRead.readLine();
            }
            buffRead.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sonuç : " + result);


    }
}