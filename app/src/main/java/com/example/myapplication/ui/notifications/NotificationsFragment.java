package com.example.myapplication.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.UserInfo;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

  /*  private TextView getTextView(String str) {
        TextView view = new TextView(getContext());
        view.setText(str);
        return view;
    }*/

  /*  public NotificationsFragment(){
        Log.d("Test","Create Freagment");
    }


    private String makeStringUser(UserInfo u) {
        return u.getId() + "; " + u.getLogin() + "; " + u.getPass() + "; " + u.getFio() + "; " + u.getRole() + ";";
    }*/

    private class UserAdapter extends ArrayAdapter<UserInfo>{
        public UserAdapter(Context context, ArrayList<UserInfo> users){
            super(context, R.layout.user_fragment, users);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            UserInfo user = getItem(position);

            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_fragment, null);
            }
            TextView users_idView = convertView.findViewById(R.id.users_idView);
            TextView users_fioView = convertView.findViewById(R.id.users_fioView);
            TextView users_loginView = convertView.findViewById(R.id.users_loginView);
            TextView users_passView = convertView.findViewById(R.id.users_passView);
            TextView users_roleView = convertView.findViewById(R.id.users_roleView);

            users_idView.setText(String.valueOf(user.getId()));
            users_fioView.setText(user.getFio());
            users_loginView.setText(user.getLogin());
            users_passView.setText(user.getPass());
            users_roleView.setText(user.getRole().toString());

            return convertView;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        /*Log.d("Test", "testCreateFragment");
        Log.d("Test", "F1 " + String.valueOf(t));
        t=10;
        Log.d("Test", "F2 " +String.valueOf(t));*/

        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        super.onCreate(savedInstanceState);
    }
    int t = 5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  Log.d("Test", "testCreateView");
        Log.d("Test", "V0 " +String.valueOf(t));*/
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final ListView spisok = root.findViewById(R.id.spisok);
        Button refreshbutton = root.findViewById(R.id.refresh);

        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    notificationsViewModel.refreshUsers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
           // notificationsViewModel.refreshUsers();
            notificationsViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList<UserInfo>>() {
                @Override
                public void onChanged(ArrayList<UserInfo> userInfos) {
                   // spisok.removeAllViews();

                    UserAdapter adapter = new UserAdapter(getContext(), userInfos);
                    spisok.setAdapter(adapter);
                    /*FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/

                    /*for(UserInfo user : userInfos) {
                        //spisok.addView(getTextView(makeStringUser(user)));
                        UserFragment fragment = UserFragment.newInstance(user);
                        getChildFragmentManager().beginTransaction().add(R.id.spisok, fragment).commit();
                        //getChildFragmentManager().beginTransaction().add()
                     /*   UserFragment fragment = UserFragment.newInstance(user);
                        fragmentTransaction.add(R.id.spisok, fragment, String.valueOf((user.getId())));*/

//                        spisok.addView(fragment.getView());
                    //}

                    //fragmentTransaction.commit();
                    /*final Button btn = new Button(getContext());
                    btn.setText("Добавить");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btn.setText("Добавление");
                        }
                    });
                    spisok.addView(btn);*/
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}