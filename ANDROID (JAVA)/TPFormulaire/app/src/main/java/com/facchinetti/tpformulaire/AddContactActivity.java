package com.facchinetti.tpformulaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AddContactActivity extends AppCompatActivity {

    public final int REQUEST_CODE_ADD_CONTACT = 1;
    public static final int RESULT_CREATE_AVATAR = 1;
    public static final int RESULT_CANCEL = 2;
    public static final int RESULT_CREATE_CONTACT = 3;
    private static ImageView avatar;
    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText dateNaissanceEditText;
    private EditText numTelEditText;
    private EditText mailEditText;
    private EditText codePostalEditText;
    private EditText adresseEditText;
    private RadioButton rbtnHomme, rbtnFemme;
    private Contact newContact;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        avatar = findViewById(R.id.avatarContact);

        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        dateNaissanceEditText = findViewById(R.id.dateNaissanceEditText);
        numTelEditText = findViewById(R.id.numTelEditText);
        codePostalEditText = findViewById(R.id.codePostalEditText);
        adresseEditText = findViewById(R.id.adresseEditText);
        mailEditText = findViewById(R.id.mailEditText);
        rbtnHomme = findViewById(R.id.radioButtonHomme);
        rbtnFemme = findViewById(R.id.radioButtonFemme);

    }

    public void clickAvatar(View v){

        Intent changeAvatarIntent = new Intent(AddContactActivity.this, ChangeAvatarActivity.class);
        changeAvatarIntent.putExtra("ChangeAvatar","Add");
        startActivity(changeAvatarIntent);

    }

    public static ImageView getAvatar() {
        return avatar;
    }



    public void clickAjouter(View v){

        Sexe sexeContact;
        if(rbtnFemme.isChecked()){
            sexeContact = Sexe.FEMME;
        }
        else if(rbtnHomme.isChecked()){
            sexeContact = Sexe.HOMME;
        }
        else{
            sexeContact = Sexe.NON_RENSEIGNE;
        }

        if(nomEditText.getText().toString().equals("") ||
            prenomEditText.getText().toString().equals("") ||
            numTelEditText.getText().toString().equals("") ||
            sexeContact == Sexe.NON_RENSEIGNE
        ){
            Toast.makeText(getApplicationContext(),"Veuillez remplir les champs obligatoires.",Toast.LENGTH_SHORT).show();
        }
        else {
            String nom = nomEditText.getText().toString();
            String prenom = prenomEditText.getText().toString();
            String dateNaissance = dateNaissanceEditText.getText().toString();
            String numTel = numTelEditText.getText().toString();
            String mail = mailEditText.getText().toString();
            String codePostal = codePostalEditText.getText().toString();
            String adresse = adresseEditText.getText().toString();

            newContact = new Contact(avatar, sexeContact,nom,prenom,dateNaissance,numTel,mail,codePostal,adresse);
            Intent verifyContactActivity = new Intent(AddContactActivity.this, VerifyContactActivity.class);
            verifyContactActivity.putExtra("nom",nom);
            verifyContactActivity.putExtra("prenom",prenom);
            verifyContactActivity.putExtra("dateNaissance",dateNaissance);
            verifyContactActivity.putExtra("numTel",numTel);
            verifyContactActivity.putExtra("mail",mail);
            verifyContactActivity.putExtra("codePostal",codePostal);
            verifyContactActivity.putExtra("adresse",adresse);
            if(sexeContact == Sexe.HOMME){
                verifyContactActivity.putExtra("sexe","Homme");
            }
            else{
                verifyContactActivity.putExtra("sexe","Femme");
            }

            startActivityForResult(verifyContactActivity,REQUEST_CODE_ADD_CONTACT);

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_ADD_CONTACT){
            if(resultCode == RESULT_CREATE_AVATAR){
                MainActivity.listeContacts.add(newContact);
                Intent intentReturn = new Intent();
                setResult(RESULT_CREATE_CONTACT,intentReturn);
                super.onBackPressed();
            }
        }
    }

}
