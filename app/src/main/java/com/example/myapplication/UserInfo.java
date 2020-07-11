package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;

import androidx.annotation.NonNull;

import java.io.IOException;

public class UserInfo{

    private int id;
    private String fio, login, pass;
    private roles role;

    public enum roles {
        user,
        admin,
        buyer;

        public static roles convert(int value) {
            return roles.values()[value];
        }
        public int toInt(){return this.ordinal();}

        @Override
        public String toString() {
            if(this==user){
                return  "User";
            }else if(this==admin){
                return "Administrator";
            }else if(this==buyer){
                return "Buyer";
            }
            return super.toString();
        }
    }

    public int getId() {
        return id;
    }

    public roles getRole() {
        return role;
    }

    public String getFio() {
        return fio;
    }

    public String getLogin() {
        return login;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(roles role) {
        this.role = role;
    }

    public UserInfo(int id, String fio, String login, String pass, roles role) {
        this.fio = fio;
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
    public UserInfo() {}

    public static UserInfo parseJson(JsonReader reader) throws IOException {
        UserInfo user = new UserInfo();
        reader.beginObject();
        while(reader.hasNext()) {
            switch(reader.nextName()) {
                case "users_id":
                    user.setId(reader.nextInt());
                    break;
                case "users_fio":
                    user.setFio(reader.nextString());
                    break;
                case "users_login":
                    user.setLogin(reader.nextString());
                    break;
                case "users_pass":
                    user.setPass(reader.nextString());
                    break;
                case "users_role":
                    user.setRole(roles.convert(reader.nextInt()));
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return user;
    }
}
