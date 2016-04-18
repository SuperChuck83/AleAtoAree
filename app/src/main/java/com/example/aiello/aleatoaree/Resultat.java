package com.example.aiello.aleatoaree;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Resultat extends AppCompatActivity {

    private  TextToSpeech t1;
    ArrayList<String> ListeChoixUser = new ArrayList<String>();
    private int Result;
    private String Resultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);



        /* Gestion publicitaire */
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.bringToFront(); //on met la pub en avant des autres éléments


        //Textview dans le cercle
        final TextView TextViewCircle = (TextView)findViewById(R.id.textView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //---------Récupère informations------------
            ListeChoixUser = extras.getStringArrayList("ListeChoixUser");

            //gènère un nombre aléatoire entre 0 et la taille de la liste
            Result = randomisation(ListeChoixUser.size());
            Resultat = ListeChoixUser.get(Result);
            //affiche le resultat de la liste à l'index retourné aleatoirement
            TextViewCircle.setText(Resultat);

        }


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.getDefault());
                    t1.speak(ListeChoixUser.get(Result), TextToSpeech.QUEUE_FLUSH, null); // on lit le resultat randomisé
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Si on click sur le son alorso n lit le resultat
        if (id == R.id.Son) {
            t1.speak(Resultat, TextToSpeech.QUEUE_FLUSH, null); // on lit le resultat randomisé
        }

        return super.onOptionsItemSelected(item);
    }


    //genère un nombre aléatoire entre 0 et i-1
    public int randomisation(int i){
        int Min=0;
        int Max=i-1;

        Random rand = new Random();
        return rand.nextInt(Max - Min + 1) + Min;

    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


}
