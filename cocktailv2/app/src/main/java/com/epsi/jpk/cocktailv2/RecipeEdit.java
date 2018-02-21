package com.epsi.jpk.cocktailv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import io.realm.RealmList;

public class RecipeEdit extends AppCompatActivity {

    RecyclerView ui_recycler_ingredients;
    RecipeEdit my_this;
    ingredient_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);
        ui_recycler_ingredients = findViewById(R.id.recycler_ingredients);
        ui_recycler_ingredients.setLayoutManager(new LinearLayoutManager(this));
        int val = getIntent().getIntExtra("somekey", 432);
        adapter = new ingredient_adapter();
        ui_recycler_ingredients.setAdapter(adapter);
        my_this = this;

    }
    public void add_new_ingredient(View view)
    {
        adapter.add_ingredient();
    }

    class ingredient_adapter extends RecyclerView.Adapter<ingredient_adapter.recette_holder> {
//        ArrayList<String> ingredients = new ArrayList<>();
        RealmList<ingredient> ingredients = new RealmList<>();

        public void add_ingredient() {
            ingredients.add(new ingredient("Rhum", 8, "cL"));
            notifyItemInserted(ingredients.size() - 1);

            // todo
            // check if the last line is empty when adding an ingredient
        }

        void removeRow(int index) {
            ingredients.remove(index);
            notifyItemRemoved(index);
        }

        @Override
        public recette_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_cell, parent, false);
            recette_holder holder = new recette_holder(cell);
            return holder;
        }

        @Override
        public void onBindViewHolder(recette_holder holder, int position) {
            holder.ui_ingredient.setText(ingredients.get(position).getNomIngredient());
        }

        @Override
        public int getItemCount() {
            return ingredients.size();
        }

        class recette_holder extends RecyclerView.ViewHolder
                implements View.OnClickListener, TextWatcher {
            private final EditText ui_ingredient;

            public recette_holder(View itemView) {
                super(itemView);
                ui_ingredient = itemView.findViewById(R.id.ingredient);

                Button delButton = itemView.findViewById(R.id.button_delete_ingredient);
                delButton.setOnClickListener(this);
                ui_ingredient.addTextChangedListener(this);
            }

            @Override
            public void onClick(View view) {
                removeRow(getAdapterPosition());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                ingredients.set(getAdapterPosition(),editable.toString());
                ingredients.get(getAdapterPosition()).setNomIngredient(editable.toString());
                // todo
                // check if line is empty, and call removerow
                //
            }
        }
    }


}
