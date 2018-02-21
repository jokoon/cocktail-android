package com.epsi.jpk.cocktailv2;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by bidet on 21/02/2018.
 */

public class shoppinglist extends RealmObject {

    private String nom;
    private Date dateEcheance;
//    private ArrayList<ingredient> _listeIngredientsCourses;
    private RealmList<ingredient> _listeIngredientsCourses;

    public shoppinglist(){}

//    public shoppinglist(String nom, Date dateEcheance, ArrayList<ingredient> _listeIngredientsCourses) {
    public shoppinglist(String nom, Date dateEcheance, RealmList<ingredient> _listeIngredientsCourses) {
        this.nom = nom;
        this.dateEcheance = dateEcheance;
        this._listeIngredientsCourses = _listeIngredientsCourses;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

//    public ArrayList<ingredient> get_listeIngredientsCourses() {
    public RealmList<ingredient> get_listeIngredientsCourses() {
        return _listeIngredientsCourses;
    }

//    public void set_listeIngredientsCourses(ArrayList<ingredient> _listeIngredientsCourses) {
    public void set_listeIngredientsCourses(RealmList<ingredient> _listeIngredientsCourses) {
        this._listeIngredientsCourses = _listeIngredientsCourses;
    }
}
