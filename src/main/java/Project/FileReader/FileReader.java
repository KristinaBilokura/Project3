package Project.FileReader;


import org.apache.log4j.Logger;

import java.io.*;

public final class FileReader {
    static Logger log = Logger.getLogger(FileReader.class.getName());

    private FileReader() {
    }

    public static String readFile(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            while ((s = br.readLine()) != null)
                sb.append(s + "\n");
        } catch (IOException e) {
            log.error(e);
        }
        try {
            br.close();
        } catch (IOException e) {
            log.error(e);
        }
        if (sb.charAt(0) == '\uFEFF') sb.deleteCharAt(0);
        return sb.toString();
    }
}
