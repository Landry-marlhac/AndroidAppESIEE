package com.example.androidappesiee;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewMail extends AppCompatActivity {

    private ImageView pub;
    private int nbr_pub;
    private Button sendEmail;
    private Button retour_home;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    //Class permettant d'afficher la page de remplissage du mail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mail);

        pref = getSharedPreferences("MyPref", 0);
        editor = pref.edit();

        //Lors de l'appuis sur le bouton send email, on récupère le text de l'objet et du message,
        //pour le préremplir dans le mail. On ajoute nos mail pro pour le contact
        //On utilise le intent de partage par mail de base d'android

        sendEmail = (Button) findViewById(R.id.btn_mail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                EditText objet = findViewById(R.id.objet);
                EditText contenu = findViewById(R.id.contenu);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"camille.picard@edu.esiee.fr","landry.marlhac@edu.esiee.fr","guillaume.martin@edu.esiee.fr"});
                i.putExtra(Intent.EXTRA_SUBJECT, objet.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT, contenu.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Envoyez votre message avec ..."));
                } catch (ActivityNotFoundException ex) {

                }
            }
        });

        //Bouton sur le menu home
        retour_home = (Button) findViewById(R.id.btn_retour_home);
        retour_home.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

            Intent intent_retour = new Intent(NewMail.this, MainActivity.class);
            startActivity(intent_retour);

        }
    });


    }


}


