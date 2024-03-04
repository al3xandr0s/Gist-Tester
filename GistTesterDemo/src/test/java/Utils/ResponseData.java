package Utils;

import org.json.JSONObject;

public class ResponseData {

    public String getFirstGistFilename(String data){
        JSONObject gistDetails = new JSONObject(data);

        return gistDetails.getJSONObject("files").keySet().toArray()[0].toString();
    }

    public String getFilesFilenameJsonPath (String data) {

        return "files['"+data+"'].filename";
    }

    public String getFilesContentJsonPath (String data) {

        return "files['"+data+"'].content";
    }

}
