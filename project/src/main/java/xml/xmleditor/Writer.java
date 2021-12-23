package xml.xmleditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void write(String s)
    {
        String username = System.getProperty("user.name");
        try {
            File myObj = new File("C:\\Users\\" + username + "\\Desktop\\compressed.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("C:\\Users\\" + username + "\\Desktop\\compressed.txt", false);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
