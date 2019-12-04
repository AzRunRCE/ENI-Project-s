package com.eni.tp_correction;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eni.tp_correction.bo.Article;
import com.eni.tp_correction.bo.Contact;

import java.util.ArrayList;

public class SelectionContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_contact);

        RecyclerView recyclerViewContact = findViewById(R.id.recyclerViewContact);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Contact> arrayListContact = new ArrayList<>();

        Cursor cursorFriends = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        while(cursorFriends.moveToNext()){
            String id = cursorFriends.getString(
                    cursorFriends.getColumnIndex(ContactsContract.Contacts._ID));
            String displayName = cursorFriends.getString(
                    cursorFriends.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor cursorDataContact = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone._ID +"=?",
                    new String[]{id},
                    null
            );
            String numeroTelephone = "";
            while(cursorDataContact.moveToNext()){
                numeroTelephone = cursorDataContact.getString(
                        cursorDataContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            arrayListContact.add(new Contact(numeroTelephone,displayName));


        }
        ContactAdapter adapter = new ContactAdapter(
                arrayListContact,
                (Article)getIntent().getParcelableExtra(DetailActivity.ARTICLE_EXTRA));
        recyclerViewContact.setAdapter(adapter);
    }
}
