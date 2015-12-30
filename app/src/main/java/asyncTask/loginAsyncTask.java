package asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import util.HttpService;

public class loginAsyncTask extends AsyncTask<String, Void, HttpURLConnection>{

    Context context;

    public loginAsyncTask(Context activity) {

        this.context = activity;
    }

    @Override

    protected void onPreExecute() {

        Log.i("NotificationWearApp", "OnPreExecute");
    }

    @Override

    protected HttpURLConnection doInBackground(String... valores) {

        Log.i("NotificationWearApp", "doInBackground: " + valores[0]);


        HttpURLConnection connection = null;
        JSONObject jo = new JSONObject();

        try {

            jo.put("nome", valores[0]);
            jo.put("senha", valores[1]);


        } catch (Exception e) {
            Log.e("", "" + e.getMessage());
        }

        try {


            connection = HttpService.sendGetRequest("servicoservlet");


        } catch (MalformedURLException e) {
            Log.e("NotificationWearApp","MalformedURLException");

        } catch (IOException ex) {

            Log.e("NotificationWearApp","MalformedURLException");
        }

        return connection;
    }

    @Override

    protected void onPostExecute(HttpURLConnection connection) {

        try {

            int status = connection.getResponseCode();

            String contentValue = HttpService.getHttpContent(connection);
            JSONObject json = new JSONObject(contentValue);


            String senha = json.getString("senha");
            if (status==200) {
                String key = json.getString("key");
                Toast.makeText(context, "Key: "+key, Toast.LENGTH_LONG).show();
            }else{
                String erro = json.getString("mensagem");
                Toast.makeText(context, "Mensagem: "+erro, Toast.LENGTH_LONG).show();
            }


        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {

            Log.e("NotificationWearApp", "JSONException");
        }
    }


}

