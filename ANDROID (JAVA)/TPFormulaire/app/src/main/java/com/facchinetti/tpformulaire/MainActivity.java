package com.facchinetti.tpformulaire;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Contact> listeContacts;
    public static final int REQUEST_MAIN_ACTIVITY_OK = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeContacts = new ArrayList<>();

        contactParDefaut();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void contactParDefaut(){
        ImageView avatarRouge = new ImageView(this);
        avatarRouge.setImageDrawable(getDrawable(R.drawable.logo1));

        listeContacts.add(new Contact(avatarRouge,Sexe.HOMME,"Térieur","Alex","04/04/2004","0352632800","alex.terieur@gmail.com","03754","Ceci est une adresse"));
        listeContacts.add(new Contact(avatarRouge,Sexe.HOMME,"Neymar","Jean","29/02/2000","0493107345","jean.neymar@gmail.com","19375","Ceci est une autre adresse"));
        listeContacts.add(new Contact(avatarRouge,Sexe.FEMME,"Pelle","Sarah","18/12/1980","0722983410","sarah.pelle@gmail.com","83121","Ceci est encore une adresse"));
    }

    public void clickButtonListeContacts(View v){
        //Log.d("MainActivity","Liste contacts");
        Intent intentListeContacts = new Intent(MainActivity.this,ListeContactsActivity.class);
        startActivity(intentListeContacts);
    }

    public void clickButtonAddContact(View v){
        //Log.d("MainActivity","Add contact");
        Intent intentAddContact = new Intent(MainActivity.this, AddContactActivity.class);
        startActivityForResult(intentAddContact,REQUEST_MAIN_ACTIVITY_OK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
