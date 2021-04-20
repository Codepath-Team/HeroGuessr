package com.example.heroguessr.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class User {
    @ParseClassName("Post")
    public class Post extends ParseObject {

        public static final String KEY_USER = "user";
        public static final String KEY_IMAGE = "image";

        public ParseFile getImage() {
            return getParseFile(KEY_IMAGE);
        }

        public void setImage(ParseFile image) {
            put(KEY_IMAGE, image);
        }

        public ParseUser getUser() {
            return getParseUser(KEY_USER);
        }

        public void setUser(ParseUser user) {
            put(KEY_USER, user);
        }
    }
}
