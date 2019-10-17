package com.abhijeet.firebasedatabasepoc.models;
import java.util.ArrayList;
public class SampleModel {
    String uniqueId;
    String name;
    ArrayList<String> hobbies;

    public SampleModel() {
    }

    public SampleModel(String uniqueId, String name, ArrayList<String> hobbies) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.hobbies = hobbies;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }
}
