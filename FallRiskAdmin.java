import java.net.*;
import java.io.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;


public class FallRiskAdmin {
    private static String devURL = "http://127.0.0.1:5000";
    private static String prdURL = "http://fallriskdb-vm.main.ad.rit.edu:5000";
    private String token;
    private String expiration;
    private String email;
    private String password;
    private String baseURL;


    public FallRiskAdmin(String _email, String _password, boolean useDevURL) {
        token = null;
        expiration = null;
        email = _email;
        password = _password;

        if (useDevURL) {
            baseURL = devURL;
        } else {
            baseURL = prdURL;
        }
    }


    public void getToken() throws IOException {
        URL url = new URL(baseURL + "/api/tokens");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String auth = email + ":" + password;

        byte[] authBytes = auth.getBytes(StandardCharsets.UTF_8);

        String encodedAuth = Base64.getEncoder().encodeToString(authBytes);

        String authHeaderValue = "Basic " + encodedAuth;

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", authHeaderValue);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = null;
        StringBuilder sb = new StringBuilder();

        while ((line = in.readLine()) != null) {
            sb.append(line);
        }

        String resp = sb.toString();

        Gson gson = new Gson();

        Object object = gson.fromJson(resp, TokenResp.class);

        token = ((TokenResp) object).token;

        expiration = ((TokenResp) object).expiration;

        in.close();
    }


    public UserData[] getUserData() throws IOException {
        URL url = new URL(baseURL + "/api/admin/data");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String authHeaderValue = "Bearer " + token;

        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", authHeaderValue);

        InputStream in = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = null;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        in.close();

        String resp = sb.toString();

        Gson gson = new Gson();

        return (UserData[]) gson.fromJson(resp, UserData[].class);
    }


    public String postUserResults(UserResult[] results) throws IOException {
        URL url = new URL(baseURL + "/api/admin/results");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String authHeaderValue = "Bearer " + token;

        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", authHeaderValue);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        
        Gson gson = new Gson();

        String body = gson.toJson(results);

        OutputStream os = con.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        bw.write(body);
        bw.flush();
        bw.close();
        os.close();

        if (con.getResponseCode() == 200) {
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            in.close();

            String resp = sb.toString();

            return resp;
        } else {
            return null;
        }
    }
}
