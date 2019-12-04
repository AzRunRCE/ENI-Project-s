package fr.eni.ecole.applicationevent.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class Personne implements Parcelable {

    private String nom;
    private String prenom;
    private int age;

    //Constructeur public a ajouter
    public Personne(){

    }

    //Constructeur protected
    protected Personne(Parcel in) {
        //Respecter l'ordre de la methode writeToParcel
        nom = in.readString();
        prenom = in.readString();
        age = in.readInt();
    }

    public static final Creator<Personne> CREATOR = new Creator<Personne>() {
        @Override
        public Personne createFromParcel(Parcel in) {
            return new Personne(in);
        }

        @Override
        public Personne[] newArray(int size) {
            return new Personne[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(age);
    }
}
