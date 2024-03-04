package Data;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class TestData {
    private Dotenv dotenv = Dotenv.configure().load();
    private final String ownGistId = dotenv.get("OWN_GIST_ID");
    private final String updateGistId = dotenv.get("UPDATE_GIST_ID");
    private final String gistToDelete = dotenv.get("DELETE_GIST_ID");
    private final String gitHubUserName = dotenv.get("GITHUB_USERNAME");;


    public String getOwnGistId() {
        return ownGistId;
    }

    public String getGistToDelete(){ return gistToDelete; }

    public String getUpdateGistId(){ return updateGistId; }
    public String getGitHubUserName(){ return gitHubUserName; }



}
