package com.facchinetti.tpformulaire;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyContactActivity extends AppCompatActivity {

    String sexe;
    String nom;
    String prenom;
    String dateNaissance;
    String numTel;
    String mail;
    String codePostal;
    String adresse;

    ImageView avatar;
    TextView nomTextView;
    TextView prenomTextView;
    TextView dateNaissanceTextView;
    TextView numTelTextView;
    TextView mailTextView;
    TextView codePostalTextView;
    TextView adresseValueTextView;
    TextView sexeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_contact);
        nom = getIntent().getStringExtra("nom");
        prenom = getIntent().getStringExtra("prenom");
        dateNaissance = getIntent().getStringExtra("dateNaissance");
        numTel = getIntent().getStringExtra("numTel");
        mail = getIntent().getStringExtra("mail");
        codePostal = getIntent().getStringExtra("codePostal");
        adresse = getIntent().getStringExtra("adresse");

        avatar = findViewById(R.id.avatarContact);
        nomTextView = findViewById(R.id.nomTextViewVerify);
        prenomTextView = findViewById(R.id.prenomTextViewVerify);
        numTelTextView = findViewById(R.id.numTelTextViewVerify);
        dateNaissanceTextView = findViewById(R.id.dateNaissanceTextViewVerify);
        codePostalTextView = findViewById(R.id.codePostalTextViewVerify);
        adresseValueTextView = findViewById(R.id.adresseValueTextViewVerify);
        sexeTextView = findViewById(R.id.sexeTextViewVerify);
        mailTextView = findViewById(R.id.mailTextViewVerify);

        avatar.setImageDrawable(AddContactActivity.getAvatar().getDrawable());
        nomTextView.setText("Nom : " + nom);
        prenomTextView.setText("Prenom : " + prenom);
        sexeTextView.setText(sexe);
        dateNaissanceTextView.setText("Date de naissance : " + dateNaissance);
        numTelTextView.setText("Tel : " + numTel);
        mailTextView.setText("Mail : " + mail);
        codePostalTextView.setText("Code postal : " + codePostal);
        adresseValueTextView.setText(adresse);

    }

    public void clickAjouterButton(View v){
        setResult(AddContactActivity.RESULT_CREATE_AVATAR);
        super.onBackPressed();
    }

    public void clickAnnulerButton(View v){
        setResult(AddContactActivity.RESULT_CANCEL);
        super.onBackPressed();
    }

}
