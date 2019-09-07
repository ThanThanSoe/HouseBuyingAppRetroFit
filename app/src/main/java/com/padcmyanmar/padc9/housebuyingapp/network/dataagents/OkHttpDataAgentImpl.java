package com.padcmyanmar.padc9.housebuyingapp.network.dataagents;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.padc9.housebuyingapp.network.responses.GetEventResponse;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements EventDataAgent{
    public static OkHttpDataAgentImpl objInstance;
    private OkHttpClient mOkHttpClient;

    public OkHttpDataAgentImpl(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance(){
        if (objInstance == null){
            objInstance = new OkHttpDataAgentImpl();
        }return objInstance;
    }

    @Override
    public void getEvents(String accessToken, GetEventForNetworkDelegate delegate) {
        new GetHouseTask(mOkHttpClient,accessToken,delegate).execute();
    }

    public static class GetHouseTask extends AsyncTask<Void,Void, GetEventResponse> {

        private OkHttpClient mOkHttpClient;
        private String accessToken;
        private EventDataAgent.GetEventForNetworkDelegate mDelegate;

        public GetHouseTask(OkHttpClient mOkHttpClient,String accessToken,EventDataAgent.GetEventForNetworkDelegate delegate){
            this.mOkHttpClient = mOkHttpClient;
            this.accessToken = accessToken;
            this.mDelegate = delegate;
        }

        @Override
        protected GetEventResponse doInBackground(Void... voids) {
            RequestBody formBody = new FormBody.Builder()
                    .add(HouseRentingConstants.DUMMY_ACCESS_TOKEN,accessToken)
                    .build();

            Request request = new Request.Builder()
                    .url(HouseRentingConstants.BASE_URL + HouseRentingConstants.GET_HOUSES)
                    .post(formBody)
                    .build();

            try {
                Response response = mOkHttpClient.newCall(request).execute();

                if (response.isSuccessful()){
                    String responseString = response.body().string();

                    GetEventResponse getEventsResponses = new Gson().fromJson(responseString,GetEventResponse.class);
                    return getEventsResponses;
                }
            } catch (IOException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(GetEventResponse getEventResponse) {
            super.onPostExecute(getEventResponse);

            if (getEventResponse != null) {
                if (getEventResponse.isResponseOK()) {
                    mDelegate.onSuccess(getEventResponse.getHouseRentingList());
                } else {
                    mDelegate.onFailure(getEventResponse.getMessage());
                }
            } else {
                mDelegate.onFailure(HouseRentingConstants.ERROR_MESSAGE);
            }
        }
    }
}

