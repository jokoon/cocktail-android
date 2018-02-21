package com.epsi.jpk.cocktailv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    RecyclerView ui_recycler_recette;
    MainActivity my_this;
    recette_adapter  adapter;
//    RealmList<Recipe>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        ui_recycler_recette = findViewById(R.id.recycler_recipes);
        ui_recycler_recette.setLayoutManager(new LinearLayoutManager(this));
        adapter = new recette_adapter();
        ui_recycler_recette.setAdapter(adapter);
        my_this = this;
    }
    public void add_new_cocktail(View view)
    {
        adapter.add_recipe();
    }

    @Override
    protected void onPause(Bundle savedInstanceState)
    {
        super.onStop();

    }
    ///////// adapter /////////
    class recette_adapter extends RecyclerView.Adapter<recette_adapter.recette_holder> {
//        ArrayList<String> recettes = new ArrayList<>();
        RealmList<recipe> recettes = new RealmList<>();
        RealmResults<recipe> recipe_list;
        public recette_adapter()
        {

            Realm realm = Realm.getDefaultInstance();
            recipe_list = realm.where(recipe.class).findAll();


//            realm.beginTransaction();
//            realm.copyToRealm(new recipe("djarabou au pouet", 432));
//            realm.copyToRealm(new recipe("tagada a la jean roger", 611));
//            realm.copyToRealm(new recipe("patatadodo au soupir", 1));
//            realm.copyToRealm(new recipe("youplababadoba a la troupilette", 99));
//            realm.commitTransaction();
        }

        public void add_recipe() {
            recettes.add(new recipe("troupilette", "pouet", 10, 0, 2, new RealmList<ingredient>()));
            notifyItemInserted(recettes.size() - 1);
        }

        void removeRow(int index) {
            recettes.remove(index);
            notifyItemRemoved(index);
        }
        void removewithrealm(recipe recp)
        {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            recp.deleteFromRealm();
            realm.commitTransaction();

        }

        @Override
        public recette_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.recette_cell, parent, false);
            recette_holder holder = new recette_holder(cell);
            return holder;
        }

        @Override
        public void onBindViewHolder(recette_holder holder, int position) {
            if (recettes.get(position).equals(""))
                holder.ui_titlelabel.setText("<no name, please edit>");
            else
                holder.ui_titlelabel.setText(recettes.get(position).getNom());
            holder.ui_categorylabel.setText("");
        }

        @Override
        public int getItemCount() {
            return recettes.size();
        }

        ///////// holder /////////
        class recette_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private final TextView ui_titlelabel, ui_categorylabel;

            public recette_holder(View itemView) {
                super(itemView);
                ui_titlelabel = itemView.findViewById(R.id.recettetitre);
                ui_categorylabel = itemView.findViewById(R.id.recettecat);
                Button delButton = itemView.findViewById(R.id.deleteButton);
                delButton.setOnClickListener(this);
                Button editButton = itemView.findViewById(R.id.editButton);
                editButton.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.deleteButton) {
                    removeRow(getAdapterPosition());
                } else if (view.getId() == R.id.editButton) {

                    Intent activintent = new Intent(my_this, RecipeEdit.class);
                    activintent.putExtra("recipe_id", getAdapterPosition());
                    startActivity(activintent);
                }
            }
        }
    }


}
