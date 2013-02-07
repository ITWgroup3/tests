package Schoolware.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class SaveScore {

    JSONObject json;
    File file;
    FileOutputStream fop = null;

    public SaveScore() {
    }

    /**
     * Stores the builded JSON score in the file path given in .json format
     *
     * @param filepath
     * @param json
     */
    public void saveBuildedScore(String filepath, JSONObject json) {
        this.json = json;
        String path = filepath;
        file = new File(path);

        String content = json.toString();
        System.out.println(content);
        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Retrieves the JSONObject Stores in the file given
     *
     * @param filepath
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject getSavedScore(String filepath) throws IOException, JSONException {
        FileReader f = new FileReader(filepath);
        BufferedReader br = new BufferedReader(f);
        String currentJSONString = "";
        JSONObject currentObject = new JSONObject();
        while ((currentJSONString = br.readLine()) != null) {
            currentObject = new JSONObject(currentJSONString);
        }
        br.close();
        return currentObject;
    }
}
