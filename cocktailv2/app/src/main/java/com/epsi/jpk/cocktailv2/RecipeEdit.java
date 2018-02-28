package com.epsi.jpk.cocktailv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
//import android.util.Log;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RecipeEdit extends AppCompatActivity implements TextWatcher{

    RecyclerView ui_recycler_ingredients;
    RecipeEdit my_this;
    ingredient_adapter adapter;
    recipe recipe_current;

    RealmList<recipe> recettes = new RealmList<>();
    RealmResults<recipe> recipe_list;

    EditText ui_recipe_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);
        ui_recycler_ingredients = findViewById(R.id.recycler_ingredients);
        ui_recycler_ingredients.setLayoutManager(new LinearLayoutManager(this));
        int recipe_id = getIntent().getIntExtra("recipe_id", -1);
        Log.i("fdsfds", "object id passed: "+recipe_id);

        // realm stuff
        Realm realm = Realm.getDefaultInstance();
        if(recipe_id != -1)
        {
            recipe_list = realm.where(recipe.class).findAll();
            Log.d("dsadsa",Integer.toString(recipe_id));
            recipe_current = recipe_list.get(recipe_id);
            ui_recipe_name = findViewById(R.id.recipe_name);
            ui_recipe_name.setText(recipe_current.getNom());
            Log.i("fdsfds", "found object "+recipe_id);

        }
        adapter = new ingredient_adapter();
        ui_recycler_ingredients.setAdapter(adapter);
        my_this = this;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    public void add_new_ingredient(View view)
    {
        adapter.add_ingredient();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onDestroy()
    {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate();
//        realm.commitTransaction();

        super.onDestroy();
    }
    // text change event only for recipe title
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void afterTextChanged(Editable editable) {
        String inputtext = editable.toString();
        if(inputtext.equals(""))
        {
//                    removeRow(getAdapterPosition());
        }
        else
        {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            recipe_current.setNom(inputtext);
            realm.commitTransaction();
        }
    }





    class ingredient_adapter extends RecyclerView.Adapter<ingredient_adapter.ingredient_holder> {
//        ArrayList<String> ingredients = new ArrayList<>();
        RealmList<ingredient> ingredients = new RealmList<>();
        public ingredient_adapter()
        {
            ingredients = recipe_current.get_listeIngredients();
        }
        public void add_ingredient() {

//            ingredients.add(new ingredient("Rhum", 8, "cL"));

            // todo
            // check if the last line is empty when adding an ingredient
//            if(recipe_current.get_listeIngredients().size() > 0 &&
//                    recipe_current.get_listeIngredients().last().getNomIngredient().equals(""))
//            {
//
//            }
//            else
//            {
            Realm r = recipe_current.getRealm();
            r.beginTransaction();
                ingredients.add(new ingredient());
                r.commitTransaction();
                notifyItemInserted(ingredients.size() - 1);
//            }
        }

        void removeRow(int index) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
//            recipe_list.get(index).deleteFromRealm();
            ingredients.remove(index);
            realm.commitTransaction();
            notifyItemRemoved(index);
        }

        @Override
        public ingredient_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_cell, parent, false);
            ingredient_holder holder = new ingredient_holder(cell);
            return holder;
        }

        @Override
        public void onBindViewHolder(ingredient_holder holder, int position) {
            holder.ui_ingredient.setText(ingredients.get(position).getNomIngredient());
        }

        @Override
        public int getItemCount() {
            return ingredients.size();
        }

        class ingredient_holder extends RecyclerView.ViewHolder
                implements View.OnClickListener, TextWatcher {
            private final EditText ui_ingredient;

            public ingredient_holder(View itemView) {
                super(itemView);
                ui_ingredient = itemView.findViewById(R.id.ingredient);
//                ui_ingredient.setText(ingredients.get(getAdapterPosition()).getNomIngredient());
                Button delButton = itemView.findViewById(R.id.button_delete_ingredient);
                delButton.setOnClickListener(this);
                ui_ingredient.addTextChangedListener(this);
            }

            @Override
            public void onClick(View view) {
                removeRow(getAdapterPosition());
            }

            // text change event only for ingredient names
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String inputtext = editable.toString();
                if(inputtext.equals(""))
                {
//                    removeRow(getAdapterPosition());
                }
                else
                {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    ingredients.get(getAdapterPosition()).setNomIngredient(inputtext);
//                    realm.copyToRealmOrUpdate(ingredients);
                    realm.commitTransaction();
                }
            }
        }
    }


}
