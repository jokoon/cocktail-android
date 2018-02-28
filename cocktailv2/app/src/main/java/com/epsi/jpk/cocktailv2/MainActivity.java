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

    public void onAddButtonClock(View view)
    {
        adapter.add_recipe();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();
    }

    ///////// adapter /////////
    class recette_adapter extends RecyclerView.Adapter<recette_adapter.recette_holder> {
//        ArrayList<String> recettes = new ArrayList<>();
        RealmList<recipe> recettes = new RealmList<>();
        RealmResults<recipe> recipe_list;
//        static int auto_increment = 0;
        public recette_adapter()
        {
            Realm realm = Realm.getDefaultInstance();
            recipe_list = realm.where(recipe.class).findAll();

            realm.beginTransaction();
            if(recipe_list.size() == 0)
            {
//                realm.copyToRealm(new recipe("djarabou au pouet", "Miam", 432, 1, 1, ;
//                realm.copyToRealm(new recipe("tagada a la jean roger", "Tres Miam",6111, 1,  1, new ingredient("curcuma", 2, 1)));
//                realm.copyToRealm(new recipe("patatadodo au soupir", "Miam beaucoup",1, 1, 3, new ingredient("curcuma", 2, 1)));
//                realm.copyToRealm(new recipe("youplababadoba a la troupilette", "Miam++ mmmmmh",991, 1, 2, new ingredient("curcuma", 2, 1)));
                recipe rcp = new recipe();
                rcp.setDescription("Miam++ mmmmmh");
                rcp.setDuree_real(43);
                rcp.setDuree_repos(123);
                rcp.setNom("youplababadoba a la troupilette");
                rcp.setNb_personne(2);

                realm.copyToRealm(new recipe());

            }
            realm.commitTransaction();
        }

        public void add_recipe() {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            recipe rcp = new recipe("cocktail", "pouet", 10, 0, 2, new RealmList<ingredient>());
            realm.copyToRealm(rcp);
            realm.commitTransaction();

            notifyItemInserted(recettes.size() - 1);
        }

        void removeRow(int index) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            recipe_list.get(index).deleteFromRealm();
            realm.commitTransaction();
            notifyItemRemoved(index);
        }

        @Override
        public recette_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.recette_cell, parent, false);
            recette_holder holder = new recette_holder(cell);
            return holder;
        }

        @Override
        public void onBindViewHolder(recette_holder holder, int position) {
//            if (recettes.get(position).equals(""))
//                holder.ui_titlelabel.setText("<no name, please edit>");
//            else
//                holder.ui_titlelabel.setText(recettes.get(position).getNom());
//            holder.ui_categorylabel.setText("");
            holder.ui_titlelabel.setText(recipe_list.get(position).getNom());
            holder.ui_categorylabel.setText(recipe_list.get(position).getDescription());

        }

        @Override
        public int getItemCount() {
            return recipe_list.size();
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

                    Intent activintent = new Intent(MainActivity.this, RecipeEdit.class);
                    activintent.putExtra("recipe_id", getAdapterPosition());
//                    activintent.putExtra("recipe", recettes.get(getAdapterPosition()).getNom());
                    startActivity(activintent);
                }
            }
        }
    }


}
