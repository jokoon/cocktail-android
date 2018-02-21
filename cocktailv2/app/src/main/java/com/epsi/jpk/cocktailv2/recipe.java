package com.epsi.jpk.cocktailv2;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by bidet on 20/02/2018.
 */

public class recipe extends RealmObject
{

    private String nom;
    private String description;
    private int duree_real;
    private int duree_repos;
    private int nb_personne;
//    private ArrayList<ingredient> _listeIngredients;
    private RealmList<ingredient> _listeIngredients;

    public recipe(){}

//    public recipe(String nom, String description, int duree_real, int duree_repos, int nb_personne, ArrayList<ingredient> _listeIngredients) {
    public recipe(String nom, String description, int duree_real, int duree_repos, int nb_personne, RealmList<ingredient> _listeIngredients) {
        this.nom = nom;
        this.description = description;
        this.duree_real = duree_real;
        this.duree_repos = duree_repos;
        this.nb_personne = nb_personne;
        this._listeIngredients = _listeIngredients;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree_real() {
        return duree_real;
    }

    public void setDuree_real(int duree_real) {
        this.duree_real = duree_real;
    }

    public int getDuree_repos() {
        return duree_repos;
    }

    public void setDuree_repos(int duree_repos) {
        this.duree_repos = duree_repos;
    }

    public int getNb_personne() {
        return nb_personne;
    }

    public void setNb_personne(int nb_personne) {
        this.nb_personne = nb_personne;
    }

//    public ArrayList<ingredient> get_listeIngredients() {
        public RealmList<ingredient> get_listeIngredients() {
        return _listeIngredients;
    }

//    public void set_listeIngredients(ArrayList<ingredient> _listeIngredients) {
    public void set_listeIngredients(RealmList<ingredient> _listeIngredients) {
        this._listeIngredients = _listeIngredients;
    }

}