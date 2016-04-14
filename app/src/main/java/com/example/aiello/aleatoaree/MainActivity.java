package com.example.aiello.aleatoaree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

// Le debut de l'application
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Layout principal
        LinearLayout contentMain = (LinearLayout)findViewById(R.id.linearLayout1);

        ArrayList<EditText> InputListe = new ArrayList<EditText>();

        EditText myEdit1 = new EditText(this);
        myEdit1.setWidth(200);
        myEdit1.setHeight(200);
        myEdit1.setHint("#1");

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //return true;
            Snackbar.make(this.findViewById(android.R.id.content), "Hello world", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
