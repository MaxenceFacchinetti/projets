package com.facchinetti.tpformulaire;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ChangeAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    private ImageView avatar5;
    private ImageView avatarPlus;
    private ImageView newAvatar;
    private Bitmap newAvatarBitmap;
    public static final int KEY_GALLERY = 1, RESULT_OK = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);
        avatar4 = findViewById(R.id.avatar4);
        avatar5 = findViewById(R.id.avatar5);
        avatarPlus = findViewById(R.id.avatarPlus);

        avatar1.setOnClickListener(this);
        avatar2.setOnClickListener(this);
        avatar3.setOnClickListener(this);
        avatar4.setOnClickListener(this);
        avatar5.setOnClickListener(this);
        avatarPlus.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == avatarPlus.getId()) {
            Toast.makeText(getApplicationContext(),"Ne fonctionne pas.",Toast.LENGTH_SHORT).show();
            //openGallery();
        }
        else if(v.getId() != R.id.titreChangeAvatar){
            newAvatar = findViewById(v.getId());

            if(getIntent().getStringExtra("ChangeAvatar").equals("Add")){
                AddContactActivity.getAvatar().setImageDrawable(newAvatar.getDrawable());
            }
            else if(getIntent().getStringExtra("ChangeAvatar").equals("Modify")){
                ModifyContactActivity.avatarContact.setImageDrawable(newAvatar.getDrawable());
            }

            super.onBackPressed();
        }


    }
    /*
    -----------------------------------------
    Suite à plusieurs heures pour ajouter une image de la galerie sur l'application
    je n'ai toujours pas réussi à en ajouter.
    -----------------------------------------

    private void openGallery(){
        Log.d("OpenGallery","OpenGallery");
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, RESULT_OK);
        Log.d("OpenGallery","OpenGallery Finish");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == KEY_GALLERY && resultCode == RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                BitmapDrawable bmpDrb = new BitmapDrawable(selectedImage);
                avatarPlus.setImageDrawable(bmpDrb);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
*/

}
