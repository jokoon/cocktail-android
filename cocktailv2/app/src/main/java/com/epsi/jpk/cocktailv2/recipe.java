package com.epsi.jpk.cocktailv2;

import io.realm.RealmObject;

/**
 * Created by bidet on 20/02/2018.
 */

public class recipe extends RealmObject
{
    private String title;
    private int minutes;

    public recipe(){}
    public recipe(String name, int minutes){ title=name; minutes = minutes;}

}