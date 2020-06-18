package com.example.demoshop.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("desc")
    @Expose
    private String description;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("img")
    @Expose
    private String imageUrl;


    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return imageUrl;
    }
}

