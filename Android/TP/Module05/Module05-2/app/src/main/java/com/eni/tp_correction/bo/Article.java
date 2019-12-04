package com.eni.tp_correction.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by quentin for TP_Correction on 2019-11-30.
 */
public class Article implements Parcelable {
    private String titre;
    private String description;
    private String url;
    private float prix;
    private float rating;
    private boolean isBought;
    private long id;



    public Article(String titre, String description, String url, float prix, float rating, boolean isBought) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.rating = rating;
        this.isBought = isBought;
    }

    public Article() {

    }

    public Article(String titre, String description, String url, float prix, float rating, boolean isBought, long id) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.rating = rating;
        this.isBought = isBought;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected Article(Parcel in) {
        titre = in.readString();
        description = in.readString();
        url = in.readString();
        prix = in.readFloat();
        rating = in.readFloat();
        isBought = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeFloat(prix);
        dest.writeFloat(rating);
        dest.writeByte((byte) (isBought ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
