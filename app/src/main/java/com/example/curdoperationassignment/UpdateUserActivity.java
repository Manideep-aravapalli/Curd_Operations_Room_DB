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

public class UpdateUserActivity extends AppCompatActivity {

    EditText name, email, address, city, zipCode, phoneNo, mobileNo;
    Spinner state, country;
    Button save;
    int id;
    String u_state, u_country;
    List<String> categories = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        final UserDetails task = (UserDetails) getIntent().getSerializableExtra("task");

        name = findViewById(R.id.updateEditTextFullName);
        email = findViewById(R.id.updateEditTextEmail);
        address = findViewById(R.id.updateEditTextAddress);
        city = findViewById(R.id.updateEditTextCity);
        zipCode = findViewById(R.id.updateEditTextZipCode);
        phoneNo = findViewById(R.id.updateEditTextPhoneNo);
        mobileNo = findViewById(R.id.updateEditTextMobileNo);
        state = findViewById(R.id.updateSpinnerState);
        country = findViewById(R.id.updateSpinnerCountry);
        save = findViewById(R.id.button_update);


        LoadData(task);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask(task);
            }
        });
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(UpdateUserActivity.this, android.R.layout.simple_spinner_item, categories);
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
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(UpdateUserActivity.this, android.R.layout.simple_spinner_item, categories2);
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
    }

    private void updateTask(UserDetails task) {
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
            Toast.makeText(UpdateUserActivity.this, "Select Any State.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (country.getSelectedItem().toString().trim().equals("Select Country")) {
            Toast.makeText(UpdateUserActivity.this, "Select Any Country.", Toast.LENGTH_SHORT).show();
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
                        .update(u_name, u_address, u_email, u_city, u_state, u_country, u_zipCode, u_phoneNo, u_mobileNo, id);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Intent intent = new Intent(getApplicationContext(), UserList.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_LONG).show();
            }
        }
        saveData st = new saveData();
        st.execute();
    }

    private void LoadData(UserDetails task) {
        name.setText(task.getFullName());
        email.setText(task.getEmail());
        address.setText(task.getAddress());
        phoneNo.setText(task.getPhoneNo());
        mobileNo.setText(task.getMobileNo());
        zipCode.setText(task.getZipCode());
        city.setText(task.getCity());
        id = task.getId();
    }
}
