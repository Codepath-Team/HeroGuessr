package com.example.heroguessr.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

    @ParseClassName("User")
    public class User extends ParseObject {

        public static final String KEY_USER = "user";
        public static final String KEY_IMAGE = "image";
        public static final String KEY_WINS = "Wins";
        public static final String KEY_LOSSES = "Losses";
        public static final String KEY_WINRATE = "winrate";
        public static final String KEY_USERNAME = "username";

        public ParseFile getImage() {
            return getParseFile(KEY_IMAGE);
        }

        public void setImage(ParseFile image) {
            put(KEY_IMAGE, image);
        }

        public ParseUser getUser() {
            return getParseUser(KEY_USER);
        }

        public void setUser(ParseUser user) { put(KEY_USER, user); }

        public int getWins() { return getInt(KEY_WINS); }

        public void setWins(ParseUser user) { put(KEY_WINS, user); }

        public int getLosses() { return getInt(KEY_LOSSES); }

        public void setLosses(ParseUser user) { put(KEY_LOSSES, user); }

        public double getWinrate() { return getInt(KEY_WINRATE); }

        public void setWinrate(ParseUser user) { put(KEY_WINRATE, user); }

        public String getUsername() { return getString(KEY_USERNAME); }

        public void setUsername(ParseUser user) { put(KEY_USERNAME, user); }
    }

