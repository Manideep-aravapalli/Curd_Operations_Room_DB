package com.example.curdoperationassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.curdoperationassignment.db.entity.UserDetails;

public class PopActivity extends AppCompatActivity {

    Button update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        update = findViewById(R.id.updateUser);
        delete = findViewById(R.id.delete);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserDetails task = (UserDetails) getIntent().getSerializableExtra("task");
                Intent intent = new Intent(getApplicationContext(), UpdateUserActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserDetails task = (UserDetails) getIntent().getSerializableExtra("task");
                deleteTask(task);
            }
        });
    }

    private void deleteTask(UserDetails task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .delete(task.getId());
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), UserList.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }
}