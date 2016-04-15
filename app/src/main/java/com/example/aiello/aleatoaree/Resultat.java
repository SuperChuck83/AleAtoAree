package com.example.aiello.aleatoaree;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Resultat extends AppCompatActivity {

    private  TextToSpeech t1;
    ArrayList<String> ListeChoixUser = new ArrayList<String>();
    int Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);


        //Textview dans le cercle
        TextView TextViewCircle = (TextView)findViewById(R.id.textView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             //---------Récupère informations------------
            ListeChoixUser = extras.getStringArrayList("ListeChoixUser");

            //gènère un nombre aléatoire entre 0 et la taille de la liste
            Result = randomisation(ListeChoixUser.size());
            //affiche le resultat de la liste à l'index retourné aleatoirement
            TextViewCircle.setText(ListeChoixUser.get(Result));

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
