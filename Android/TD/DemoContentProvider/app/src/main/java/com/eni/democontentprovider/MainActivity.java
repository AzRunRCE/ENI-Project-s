package com.eni.democontentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_CONTACT = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.READ_CONTACTS
                },
                REQUEST_CODE_CONTACT
        );
    }

    public void bindSpinner(){
        ArrayList<Friend> arrayListFriend = new ArrayList<>();
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
            ContactsContract.Contacts.
            arrayListFriend.add(new Friend(Integer.parseInt(id),displayName));
        }

        ArrayAdapter ad = new ArrayAdapter<Friend>(this, R.layout.spinner_layout,R.id.textViewDisplayName,
                arrayListFriend);
        Spinner spinner = findViewById(R.id.spinnerContact);
        spinner.setAdapter(ad);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_CODE_CONTACT:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    bindSpinner();
                }
                return;
        }
    }
}
