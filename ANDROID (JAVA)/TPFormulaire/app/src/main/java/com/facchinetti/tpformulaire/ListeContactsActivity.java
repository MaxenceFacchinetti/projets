package com.facchinetti.tpformulaire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class ListeContactsActivity extends AppCompatActivity {

    ContactAdapter adapter;
    ListView listViewContact;
    public static final int REQUEST_LISTE_CONTACT = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contact);

        listViewContact = (ListView)findViewById(R.id.listViewContact);
        adapter = new ContactAdapter(getApplicationContext());
        listViewContact.setAdapter(adapter);
    }

    public void annulerButtonClick(View v){
        super.onBackPressed();
    }

    public void ajouterButtonClick(View v){
        Intent intentAddContact = new Intent(ListeContactsActivity.this,AddContactActivity.class);
        startActivityForResult(intentAddContact, REQUEST_LISTE_CONTACT);
    }


    public class ContactAdapter extends BaseAdapter {

        Context context;
        public static final int REQUEST_MODIFY = 1;

        public ContactAdapter(Context context){

            this.context = context;
        }

        @Override
        public int getCount() {
            return MainActivity.listeContacts.size();
        }

        @Override
        public Contact getItem(int position) {
            return MainActivity.listeContacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position; // ???
        }

        public void modifyContact(Contact c, int pos){
            MainActivity.listeContacts.set(pos,c);
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            final ConstraintLayout layoutItem;
            final LayoutInflater layoutInflater = LayoutInflater.from(context);

            if(convertView == null){
                layoutItem = (ConstraintLayout) layoutInflater.inflate(R.layout.activity_item_liste_contact,parent,false);
            }
            else{
                layoutItem = (ConstraintLayout) convertView;
            }

            ViewHolder viewHolder = (ViewHolder) layoutItem.getTag();

            if(viewHolder == null){
                viewHolder = new ViewHolder();
                viewHolder.avatarContact = layoutItem.findViewById(R.id.avatarContactListContact);
                viewHolder.nomTextView = layoutItem.findViewById(R.id.nomTextViewItemListContact);
                viewHolder.prenomTextView = layoutItem.findViewById(R.id.prenomTextViewItemListContact);
                viewHolder.addCashflowButton = layoutItem.findViewById(R.id.addCashflowButton);
                viewHolder.removeCashflowButton = layoutItem.findViewById(R.id.removeCashflowButton);
                viewHolder.cashflowTextView = layoutItem.findViewById(R.id.cashflowTextView);
                viewHolder.constraintLayoutItemList = layoutItem.findViewById(R.id.constraintLayoutItemList);


                layoutItem.setTag(viewHolder);
            }

            viewHolder.avatarContact.setImageDrawable(getItem(position).getAvatarContact().getDrawable());
            viewHolder.nomTextView.setText(getItem(position).getNom());
            viewHolder.prenomTextView.setText(getItem(position).getPrenom());
            viewHolder.cashflowTextView.setText("Taux de cashflow : " + getItem(position).getTauxCashflow());
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.addCashflowButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getItem(position).addCashflow();
                    finalViewHolder.cashflowTextView.setText("Taux de cashflow : " + getItem(position).getTauxCashflow());
                }
            });

            viewHolder.removeCashflowButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getItem(position).removeCashflow();
                    finalViewHolder.cashflowTextView.setText("Taux de cashflow : " + getItem(position).getTauxCashflow());
                }
            });


            viewHolder.constraintLayoutItemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModifyContactActivity.setContactToModify(getItem(position));
                    Intent intentModifyContact = new Intent(ListeContactsActivity.this,ModifyContactActivity.class);
                    intentModifyContact.putExtra("Position",String.valueOf(position));
                    startActivityForResult(intentModifyContact,REQUEST_LISTE_CONTACT);
                }
            });

            viewHolder.constraintLayoutItemList.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    String msgSuppr = getItem(position).getPrenom() + " " + getItem(position).getNom() + " a été supprimé";
                    Toast.makeText(ListeContactsActivity.this,msgSuppr,Toast.LENGTH_SHORT).show();
                    MainActivity.listeContacts.remove(position);
                    ListeContactsActivity.this.recreate();
                    return false;
                }
            });



            return layoutItem;
        }

        private class ViewHolder{
            ConstraintLayout constraintLayoutItemList;
            ImageView avatarContact;
            TextView nomTextView;
            TextView prenomTextView;
            TextView cashflowTextView;
            Button addCashflowButton;
            Button removeCashflowButton;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_LISTE_CONTACT){
            if(resultCode == AddContactActivity.RESULT_CREATE_CONTACT){
                this.recreate();
            }
            if(resultCode == ModifyContactActivity.RESULT_MODIFY_CONTACT){
                int positionContactModif = Integer.parseInt(data.getStringExtra("Position"));
                MainActivity.listeContacts.set(positionContactModif,ModifyContactActivity.getContactToModify());
                for(Contact c : MainActivity.listeContacts){
                    Log.d("ListeContacttttt",c.toString());
                }
                this.recreate();
            }
        }
    }

}
