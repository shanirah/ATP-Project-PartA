package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.*;

public class Configurations {
    private static Configurations single_instance = null;
    Properties prop = new Properties();


    private Configurations() throws IOException {
        try {

            OutputStream output = new FileOutputStream("resources/config.properties");

            // set the properties value
            prop.setProperty("threadPoolSize", "3");
            prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            prop.setProperty("mazeSearchingAlgorithm", "BreadthFirstSearch");

            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configurations getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new Configurations();
        return single_instance;
    }

    public Properties getProperties(){
        return prop;
    }

}