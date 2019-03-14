package com.example.xyzreader.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Parcelable {

    @JsonProperty("id")
    private String mID = "";

    @JsonProperty("title")
    private String mTitle = "";

    @JsonProperty("author")
    private String mAuthor = "";

    @JsonProperty("published_date")
    private String mPublishedDate = "";

    @JsonProperty("thumb")
    private String mThumb = "";

    @JsonProperty("photo")
    private String mPhoto = "";

    @JsonProperty("body")
    private String mBody = "";

    public Item() {

    } //Retrofit utiliza.

    public String getID() {
        return mID;
    }

    public void setID(String id) {
        this.mID = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.mPublishedDate = publishedDate;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        this.mThumb = thumb;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        this.mPhoto = photo;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mID);
        dest.writeString(this.mTitle);
        dest.writeString(this.mAuthor);
        dest.writeString(this.mPublishedDate);
        dest.writeString(this.mThumb);
        dest.writeString(this.mPhoto);
        dest.writeString(this.mBody);
    }

    private Item(Parcel in) {
        this.mID = in.readString();
        this.mTitle = in.readString();
        this.mAuthor = in.readString();
        this.mPublishedDate = in.readString();
        this.mThumb = in.readString();
        this.mPhoto = in.readString();
        this.mBody = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}