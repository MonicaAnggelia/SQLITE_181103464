package com.example.sqlite_181103464;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname;
    TextView textView;
    DB_controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.editFirsname);
        lastname = (EditText) findViewById(R.id.editLastname);
        textView = (TextView) findViewById(R.id.textView);

        controller = new DB_controller(this, "", null, 1);
    }
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.btd_add:
                try {
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                }catch (SQLException e){
                    Toast.makeText(MainActivity.this,"ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.List:
                controller.list_all_student(textView);
                break;
            case R.id.update:

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Masukan Nama");

                final EditText newFirstName = new EditText(this);
                dialog.setView(newFirstName);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(firstname.getText().toString(),newFirstName.getText().toString());
                    }
                });
                dialog.show();
                break;


            }
        }
    }
