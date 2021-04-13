package com.example.heroguessr.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    String heroID;
    String name;

    //powerstats
    String intelligence;
    String strength;
    String speed;
    String durability;
    String power;
    String combat;

    //bio
    String fullName;
    String placeOfBirth;
    String firstAppearance;
    String publisher;
    String alignment;

    //appearance
    String gender;
    String race;
    String height;
    String weight;

    //connections
    String groupAffiliation;

    //image
    String imageURL;

    public Hero(JSONObject jsonObject) throws JSONException {
        JSONObject powerStats = jsonObject.getJSONObject("powerstats");
        JSONObject bio = jsonObject.getJSONObject("biography");
        JSONObject appearance = jsonObject.getJSONObject("appearance");
        JSONArray heights = appearance.getJSONArray("height");
        JSONArray weights = appearance.getJSONArray("weight");
        JSONObject connections = jsonObject.getJSONObject("connections");
        JSONObject image = jsonObject.getJSONObject("image");

        heroID = jsonObject.getString("id");
        name = jsonObject.getString("name");

        intelligence = powerStats.getString("intelligence");
        strength = powerStats.getString("strength");
        speed = powerStats.getString("speed");
        durability = powerStats.getString("durability");
        power = powerStats.getString("power");
        combat = powerStats.getString("combat");

        fullName = bio.getString("full-name");
        placeOfBirth = bio.getString("place-of-birth");
        firstAppearance = bio.getString("first-appearance");
        publisher = bio.getString("publisher");
        alignment = bio.getString("alignment");

        gender = appearance.getString("gender");
        race = appearance.getString("race");
        height = heights.getString(0);
        weight = weights.getString(0);

        groupAffiliation = connections.getString("group-affiliation");

        imageURL = image.getString("url");
    }

    public static List<Hero> fromJsonArray(JSONArray heroJsonArray) throws JSONException {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < heroJsonArray.length(); i++) {
            heroes.add(new Hero(heroJsonArray.getJSONObject(i)));
        }
        return heroes;
    }

    public String getHeroID() {
        return heroID;
    }

    public String getName() {
        return name;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getStrength() {
        return strength;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDurability() {
        return durability;
    }

    public String getPower() {
        return power;
    }

    public String getCombat() {
        return combat;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public String getImageURL() {
        return imageURL;
    }
}
