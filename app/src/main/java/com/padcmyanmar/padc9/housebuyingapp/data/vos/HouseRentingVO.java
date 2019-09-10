package com.padcmyanmar.padc9.housebuyingapp.data.vos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "house", indices = {@Index(value = "id", unique = true)})
public class HouseRentingVO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "house_id_pk")
    private int houseIdPK;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("house_image_url")
    @ColumnInfo(name = "house_image_url")
    private String houseImageUrl;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("price")
    @ColumnInfo(name = "price")
    private long price;

    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

    @SerializedName("square_feet")
    @ColumnInfo(name = "square_feet")
    private int squareFeet;

    @SerializedName("latitude")
    @ColumnInfo(name = "latitude")
    private float latitude;

    @SerializedName("longitude")
    @ColumnInfo(name = "longitude")
    private float longitude;

    public int getHouseIdPK() {
        return houseIdPK;
    }

    public void setHouseIdPK(int houseIdPK) {
        this.houseIdPK = houseIdPK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseImageUrl() {
        return houseImageUrl;
    }

    public void setHouseImageUrl(String houseImageUrl) {
        this.houseImageUrl = houseImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}

