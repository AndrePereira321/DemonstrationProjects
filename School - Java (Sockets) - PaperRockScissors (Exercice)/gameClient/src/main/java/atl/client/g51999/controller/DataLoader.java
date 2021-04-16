package atl.client.g51999.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 * @author andre
 */
public class DataLoader {

    private final Properties file;
    private String path;

    public DataLoader() throws URISyntaxException, FileNotFoundException, IOException {
        this.file = new Properties();
        this.path = new URI(getClass().getClassLoader().getResource("./data/configs.properties").toString()).getPath();
        this.file.load(new FileInputStream(path));
    }

    public int getPort() {
        return Integer.parseInt(this.file.getProperty("port"));
    }
    
    public String getHost() {
        return this.file.getProperty("host");
    }
}
