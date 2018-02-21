package com.epsi.jpk.cocktailv2;

import io.realm.RealmObject;

/**
 * Created by bidet on 21/02/2018.
 */

public class ingredient extends RealmObject{

    private String nomIngredient;
    private int quantite;
    private String uniteMesure;

    public ingredient(){}

    public ingredient(String ingredient, int quantite, String uniteMesure) {
        this.nomIngredient = nomIngredient;
        this.quantite = quantite;
        this.uniteMesure = uniteMesure;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String ingredient) {
        this.nomIngredient = ingredient;
    }



}
