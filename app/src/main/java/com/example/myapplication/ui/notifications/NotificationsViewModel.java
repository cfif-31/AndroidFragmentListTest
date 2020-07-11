package com.example.myapplication.ui.notifications;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.UserInfo;
import com.example.myapplication.jsonParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private class JsonGetTask extends AsyncTask<Integer, Integer, ArrayList<UserInfo>>{

        @Override
        protected ArrayList<UserInfo> doInBackground(Integer... ints) {
            try {
                return jsonParse.getListUsers();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<UserInfo>();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<UserInfo> userInfos) {
            super.onPostExecute(userInfos);
            users.setValue(userInfos);
        }
    }

    private MutableLiveData<ArrayList<UserInfo>> users = new MutableLiveData<>();

    public NotificationsViewModel() throws IOException {
        //Log.d("Test", "CreateViewModel");
        refreshUsers();
    }

    public void refreshUsers() throws IOException {
        JsonGetTask task = new JsonGetTask();
        task.execute();
    }

    public MutableLiveData<ArrayList<UserInfo>> getUsers() throws IOException {
        return users;
    }


}