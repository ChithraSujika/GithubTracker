package com.example.androidapp.model_class;

public class RealmRepository extends RealmObject
{
    String name;
    String description;
//    String url;

    public RealmRepository(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
