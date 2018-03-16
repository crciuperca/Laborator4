package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText address;
    private EditText position;
    private EditText company;
    private EditText website;
    private EditText messID;
    private Button   showButton;
    private Button   saveButton;

    private ViewButtonListener viewButtonListener = new ViewButtonListener();
    private class ViewButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            showButton.setText(showButton.getText().equals("Show Additional Fields") ? "Hide Additional Fields" : "Show Additional Fields");
            position.setVisibility(!showButton.getText().equals("Show Additional Fields") ? LinearLayout.VISIBLE : LinearLayout.GONE);
            company.setVisibility(!showButton.getText().equals("Show Additional Fields") ? LinearLayout.VISIBLE : LinearLayout.GONE);
            website.setVisibility(!showButton.getText().equals("Show Additional Fields") ? LinearLayout.VISIBLE : LinearLayout.GONE);
            messID.setVisibility(!showButton.getText().equals("Show Additional Fields") ? LinearLayout.VISIBLE : LinearLayout.GONE);
            //saveButton.setVisibility(!showButton.getText().equals("Show Additional Fields") ? LinearLayout.VISIBLE : LinearLayout.GONE);
            email.setEnabled(!showButton.getText().equals("Show Additional Fields") ? true : false);
        }
    }

    private SaveButtonListener saveButtonListener = new SaveButtonListener();
    private class SaveButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            if (name != null) {
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
            }
            if (phone != null) {
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText());
            }
            if (email != null) {
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText());
            }
            if (address != null) {
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText());
            }
            if (position != null) {
                intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, position.getText());
            }
            if (company != null) {
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText());
            }
            ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            if (website != null) {
                ContentValues websiteRow = new ContentValues();
                websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                contactData.add(websiteRow);
            }
            if (messID != null) {
                ContentValues imRow = new ContentValues();
                imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                imRow.put(ContactsContract.CommonDataKinds.Im.DATA, messID.getText().toString());
                contactData.add(imRow);
            }
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        name = (EditText)findViewById(R.id.editText1);
        phone = (EditText)findViewById(R.id.editText2);
        email = (EditText)findViewById(R.id.editText3);
        address = (EditText)findViewById(R.id.editText4);
        position = (EditText)findViewById(R.id.editText5);
        position.setVisibility(LinearLayout.GONE);
        company = (EditText)findViewById(R.id.editText6);
        company.setVisibility(LinearLayout.GONE);
        website = (EditText)findViewById(R.id.editText7);
        website.setVisibility(LinearLayout.GONE);
        messID = (EditText)findViewById(R.id.editText8);
        messID.setVisibility(LinearLayout.GONE);
        showButton = (Button)findViewById(R.id.button1);
        showButton.setOnClickListener(viewButtonListener);
        saveButton = (Button)findViewById(R.id.button2);
        saveButton.setOnClickListener(saveButtonListener);
    }
}
