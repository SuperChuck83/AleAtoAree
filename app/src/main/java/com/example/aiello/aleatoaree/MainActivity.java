package com.example.aiello.aleatoaree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

// Le debut de l'application
public class MainActivity extends AppCompatActivity {

    int maxLenght = 38;
    ArrayList<EditText> InputListe = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Layout principal
        LinearLayout contentMain = (LinearLayout)findViewById(R.id.linearLayout1);



        EditText myEdit1 = new EditText(this);
        myEdit1.setWidth(200);
        myEdit1.setHeight(200);
        myEdit1.setHint("#1");
        myEdit1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLenght)});

        EditText myEdit2 = new EditText(this);
        myEdit2.setWidth(500);
        myEdit2.setHeight(200);
        myEdit2.setHint("#2");

        //on ajoute les 2 inputs de bases obligatoires
        InputListe.add(myEdit1);
        InputListe.add(myEdit2);

        for (EditText item : InputListe) { //pour chaque item de la liste d'input on l'ajoute à la vue
            contentMain.addView(item);
        }





        /*** Gestion du bouton flotant***/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Hello world", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, Resultat.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Item menu d'ajout de choix
        if (id == R.id.plus) {
            //return true;
            AjoutInput();
        }

        //Item menu de suppression du dernier choix
        if (id == R.id.moins) {
            //return true;
            SupprimeInput();
        }

        return super.onOptionsItemSelected(item);
    }

    //fonction qui permet d'ajouter des inputs
    public void AjoutInput()
    {

        //Layout principal
        LinearLayout contentMain = (LinearLayout)findViewById(R.id.linearLayout1);
        EditText myEdit = new EditText(this);
        myEdit.setWidth(200);
        myEdit.setHeight(200);
        myEdit.setHint("#"+ ((int) InputListe.size() + 1)); // le numéro de l'input est égale à la taille de la liste + 1
        myEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLenght)});

        //on ajoute l'inpjut à la liste
        InputListe.add(myEdit);
        contentMain.addView(myEdit);



    }

    public void SupprimeInput()
    {

        //Layout principal
        LinearLayout contentMain = (LinearLayout)findViewById(R.id.linearLayout1);

        //si y a plus de 2 élements dans lal iste alors on supprime le dernier élements
        if(InputListe.size()>2) {
            //on supprime le dernier item de la vue
            contentMain.removeView(InputListe.get(InputListe.size() - 1));
            //puis on supprime le dernier item de la liste
            InputListe.remove(InputListe.size() - 1);
        }
        else{
            //sinon il se passe rien on affichera par la suite une snacky d'information "Il faut laisser 2 choix ! "
        }

    }


}
