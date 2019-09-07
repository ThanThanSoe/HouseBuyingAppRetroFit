package com.padcmyanmar.padc9.housebuyingapp.network.dataagents;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.padc9.housebuyingapp.network.responses.GetEventResponse;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionDataAgentImpl implements EventDataAgent{
    public static HttpUrlConnectionDataAgentImpl objInstance;

    public HttpUrlConnectionDataAgentImpl() {
    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance(){
        if (objInstance == null){
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void getEvents(String accessToken, GetEventForNetworkDelegate delegate) {
        new GetEventsTask(accessToken, delegate).execute();
    }

    public static class GetEventsTask extends AsyncTask<Void,Void, GetEventResponse> {
        private String accessToken;
        private GetEventForNetworkDelegate delegate;

        public GetEventsTask(String accessToken, GetEventForNetworkDelegate delegate) {
            this.accessToken = accessToken;
            this.delegate = delegate;
        }

        @Override
        protected GetEventResponse doInBackground(Void... voids) {
            URL url;
            BufferedReader reader = null;
            StringBuilder stringBuilder;

            try {
                //create the HTTPURL CONNECTION

                url = new URL(HouseRentingConstants.BASE_URL + HouseRentingConstants.GET_HOUSES); //1

                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //2

                //just want to do an HTTP here
                connection.setRequestMethod("POST"); //3
                connection.setReadTimeout(15 * 1000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //put the request parameter into NmaeValuePair list
               /* List<NameValuePair> params = new ArrayList(); //6
                params.add(new BasicNameValuePair(HouseRentingConstants.DUMMY_ACCESS_TOKEN, accessToken));

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                outputStream.close();*/

                connection.connect(); //7.

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }

                String responseString=  stringBuilder.toString();

                GetEventResponse responses = new Gson().fromJson(responseString,GetEventResponse.class);

                return responses;

            } catch (IOException e) {

            } finally {

                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        /*private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException{
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (NameValuePair pair : params){
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("*");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }

            return result.toString();
        }*/

        @Override
        protected void onPostExecute(GetEventResponse getEventsResponses) {
            super.onPostExecute(getEventsResponses);

            if (getEventsResponses != null){
                if (getEventsResponses.isResponseOK()){
                    delegate.onSuccess(getEventsResponses.getHouseRentingList());
                }else {
                    delegate.onFailure(getEventsResponses.getMessage());
                }
            }else {
                delegate.onFailure(HouseRentingConstants.ERROR_MESSAGE);
            }
        }
    }

}

