package com.example.macbookpro.designexample.activity.model;

/**
 * Created by macbookpro on 30/09/15.
 */
public class NavContactItem {
    private String nom;
    private String prenom;
    private String numero;
    private String mail;
    private String type;

    public NavContactItem(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public NavContactItem() {

    }

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
