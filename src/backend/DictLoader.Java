package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DictLoader {
    private final HashSet<String> dict = new HashSet<String>();

    public void ReadDict(String fileName) {
        File myObj = new File(fileName);
        Scanner myReader = null;
        try {
            myReader = new Scanner (myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dict.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error euyy!");
            e.printStackTrace();
        } finally {
            if (myReader != null) {
                myReader.close();
            }
        }
    }

    public Set<String> getDict() {
        return this.dict;
    }
}