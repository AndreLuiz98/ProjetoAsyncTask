package util;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpService {

    private static final String URL_CONTEXT = "http://192.168.0.5:8080/rest-servlet-service/";;

    public static HttpURLConnection sendGetRequest(String servicoservlet)

        throws MalformedURLException, IOException {

            HttpURLConnection connection = null;

        try {
            URL url = new URL(URL_CONTEXT);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            connection.setConnectTimeout(100);
            connection.setReadTimeout(150);
            connection.connect();

    } finally {

        connection.disconnect();
    }

    return connection;
}


    public static Response sendJsonPostRequest(JSONObject jsonObject, String service)

            throws MalformedURLException, IOException {


            HttpURLConnection connection = null;

            URL url = new URL(service);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();

            DataOutputStream stream = new DataOutputStream(connection.getOutputStream());

            stream.writeBytes(jsonObject.toString());

            stream.flush();
            stream.close();

            int httpCode = connection.getResponseCode();
            String content = getHttpContent(connection);

            connection.disconnect();

            Response response = new Response(httpCode, content);

            return response;
        }

    public static String getHttpContent(HttpURLConnection connection) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    content, "iso-8859-1"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content.close();

        } catch (IOException e) {

            Log.e("NotificationWearApp", "IOException: " + e);
        }

        return builder.toString();
    }
}

    