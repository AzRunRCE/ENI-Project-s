package fr.eni.ecole.listviewdemo.bo;

public class Candy {

    private String nom;
    private String couleur;
    private float prix;


    public Candy(String nom, String couleur, float prix) {
        this.nom = nom;
        this.couleur = couleur;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
