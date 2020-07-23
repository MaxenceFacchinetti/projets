package com.facchinetti.tpformulaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyContactActivity extends AppCompatActivity {

    private static Contact contactToModify;
    public static final int RESULT_MODIFY_CONTACT = 1;

    EditText nomEditText;
    EditText prenomEditText;
    EditText dateNaissanceEditText;
    EditText numTelEditText;
    EditText mailEditText;
    EditText codePostalEditText;
    EditText adresseEditText;
    public static ImageView avatarContact;
    RadioButton rbtnHomme;
    RadioButton rbtnFemme;

    Button ajouterButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);

        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        dateNaissanceEditText = findViewById(R.id.dateNaissanceEditText);
        numTelEditText = findViewById(R.id.numTelEditText);
        mailEditText = findViewById(R.id.mailEditText);
        codePostalEditText = findViewById(R.id.codePostalEditText);
        adresseEditText = findViewById(R.id.adresseEditText);
        avatarContact = findViewById(R.id.avatarContact);
        rbtnFemme = findViewById(R.id.radioButtonFemme);
        rbtnHomme = findViewById(R.id.radioButtonHomme);
        ajouterButton = findViewById(R.id.button);

        nomEditText.setText(contactToModify.getNom());
        prenomEditText.setText(contactToModify.getPrenom());
        dateNaissanceEditText.setText(contactToModify.getDateNaissance());
        numTelEditText.setText(contactToModify.getNumTel());
        mailEditText.setText(contactToModify.getMail());
        codePostalEditText.setText(contactToModify.getCodePostal());
        adresseEditText.setText(contactToModify.getAdresse());

        if(contactToModify.getSexe() == Sexe.FEMME){
            rbtnFemme.setChecked(true);
            rbtnHomme.setChecked(false);
        }
        else{
            rbtnFemme.setChecked(false);
            rbtnHomme.setChecked(true);
        }

        avatarContact.setImageDrawable(contactToModify.getAvatarContact().getDrawable());

    }

    public static Contact getContactToModify() {
        return contactToModify;
    }

    public static void setContactToModify(Contact contactToModify) {
        ModifyContactActivity.contactToModify = contactToModify;
    }

    public void modifyButtonClick(View v){

        modifyContact();

        Intent intentReturn = new Intent();
        intentReturn.putExtra("Position",getIntent().getStringExtra("Position"));
        setResult(RESULT_MODIFY_CONTACT,intentReturn);
        super.onBackPressed();
    }

    private void modifyContact(){
        String nom, prenom, dateNaissance, numTel, mail, codePostal, adresse;
        Sexe sexe;
        ImageView avatar;

        if(rbtnHomme.isChecked()){
            sexe = Sexe.HOMME;
        }
        else{
            sexe = Sexe.FEMME;
        }

        avatar = avatarContact;
        nom = nomEditText.getText().toString();
        prenom = prenomEditText.getText().toString();
        dateNaissance = dateNaissanceEditText.getText().toString();
        numTel = numTelEditText.getText().toString();
        mail = mailEditText.getText().toString();
        codePostal = codePostalEditText.getText().toString();
        adresse = adresseEditText.getText().toString();
        double tauxCashflow = contactToModify.getTauxCashflow();
        setContactToModify(new Contact(avatar,sexe,nom,prenom,dateNaissance,numTel,mail,codePostal,adresse));
        getContactToModify().setTauxCashflow(tauxCashflow);
    }

    public void avatarClick(View v){
        Intent changeAvatarIntent = new Intent(ModifyContactActivity.this,ChangeAvatarActivity.class);
        changeAvatarIntent.putExtra("ChangeAvatar","Modify");
        startActivity(changeAvatarIntent);
    }

}
