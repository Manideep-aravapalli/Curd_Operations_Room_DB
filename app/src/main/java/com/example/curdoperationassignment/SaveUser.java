package com.example.curdoperationassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.curdoperationassignment.db.entity.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class SaveUser extends AppCompatActivity {

    EditText name, email, address, city, zipCode, phoneNo, mobileNo;
    Spinner state, country;
    Button save;
    String u_state, u_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_user);

        name = findViewById(R.id.editTextFullName);
        email = findViewById(R.id.editTextEmail);
        address = findViewById(R.id.editTextAddress);
        city = findViewById(R.id.editTextCity);
        zipCode = findViewById(R.id.editTextZipCode);
        phoneNo = findViewById(R.id.editTextPhoneNo);
        mobileNo = findViewById(R.id.editTextMobileNo);
        state = findViewById(R.id.spinnerState);
        country = findViewById(R.id.spinnerCountry);
        save = findViewById(R.id.button_save);


        List<String> categories = new ArrayList<String>();
        categories.add("Select State");
        categories.add("Andhra Pradesh");
        categories.add("Arunachal Pradesh");
        categories.add("Assam");
        categories.add("Bihar");
        categories.add("Chandigarh");
        categories.add("Delhi");
        categories.add("Goa");
        categories.add("Gujarat");
        categories.add("Haryana");
        categories.add("Himachal Pradesh");
        categories.add("Jammu and Kashmir union territory");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SaveUser.this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                u_state = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Select Country");
        categories2.add("Afghanistan");
        categories2.add("Albania");
        categories2.add("Algeria");
        categories2.add("India");
        categories2.add("Andorra");
        categories2.add("Angola");
        categories2.add("Antigua and Barbuda");
        categories2.add("Argentina");
        categories2.add("Armenia");
        // Div Spinner implementing onItemSelectedListener
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(SaveUser.this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(dataAdapter2);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                u_country = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    private void saveData() {

        String u_name = name.getText().toString().trim();
        String u_email = email.getText().toString().trim();
        String u_address = address.getText().toString().trim();
        String u_city = city.getText().toString().trim();
        String u_zipCode = zipCode.getText().toString().trim();
        String u_phoneNo = phoneNo.getText().toString().trim();
        String u_mobileNo = mobileNo.getText().toString().trim();

        if (u_name.isEmpty()) {
            name.setError("Enter your FullName.");
            name.requestFocus();
            return;
        }
        if (u_email.isEmpty()) {
            email.setError("Enter your Email-id.");
            email.requestFocus();
            return;
        }
        if (u_address.isEmpty()) {
            address.setError("Enter your Address.");
            address.requestFocus();
            return;
        }
        if (u_city.isEmpty()) {
            city.setError("Enter your City.");
            city.requestFocus();
            return;
        }
        if (u_zipCode.length() < 6 || u_zipCode.isEmpty()) {
            zipCode.setError("Enter your valid ZipCode.");
            zipCode.requestFocus();
            return;
        }
        if (u_mobileNo.length() < 10 || u_mobileNo.isEmpty()) {
            mobileNo.setError("Enter valid Mobile No.");
            mobileNo.requestFocus();
            return;
        }
        if (u_phoneNo.length() < 10 || u_phoneNo.isEmpty()) {
            phoneNo.setError("Enter valid Phone No.");
            phoneNo.requestFocus();
            return;
        }
        if (state.getSelectedItem().toString().trim().equals("Select State")) {
            Toast.makeText(SaveUser.this, "Select Any State.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (country.getSelectedItem().toString().trim().equals("Select Country")) {
            Toast.makeText(SaveUser.this, "Select Any Country.", Toast.LENGTH_SHORT).show();
            return;
        }

        class saveData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                UserDetails userDetails = new UserDetails();
                userDetails.setFullName(u_name);
                userDetails.setAddress(u_address);
                userDetails.setCity(u_city);
                userDetails.setEmail(u_email);
                userDetails.setMobileNo(u_mobileNo);
                userDetails.setPhoneNo(u_phoneNo);
                userDetails.setZipCode(u_zipCode);
                userDetails.setState(u_state);
                userDetails.setCounty(u_country);

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .insert(userDetails);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        saveData st = new saveData();
        st.execute();
    }


}