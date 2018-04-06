package com.example.jadstudios.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class newForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText HDBcarparkAC;
    private EditText petrolKioskAC;
    private EditText hawkerAC;
    private EditText emailaddress;
    private EditText description;
    String [] carparkName = new String[2419];
    String [] hawkerName = new String[100];
    String [] petrolKioskName = new String[185];
    String subject = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerForForms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        emailaddress = findViewById(R.id.edit_text_to);
        description = findViewById(R.id.edit_text_message);

        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int z = 0;
                int y = 0;
                int x = 0;
                if(description.getText().toString().equals("")){
                    description.setError("Cannot be empty!");
                }
                else if(emailaddress.getText().toString().equals("")){
                    emailaddress.setError("Cannot be empty!");
                }
                else if(emailaddress.getText().toString().equals("") && description.getText().toString().equals("")){
                    emailaddress.setError("Cannot be empty!");
                    description.setError("Cannot be empty!");
                }
                else {
                    sendMail();
                }
            }
        });
    }

    private void sendMail() {
        String recipientList = emailaddress.getText().toString();
        String[] recipients = recipientList.split(",");

        String message = description.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int z = 0;
        String text = adapterView.getItemAtPosition(i).toString();
        Log.d("place = ",text);
        if (text.trim().equals("Carparks")){
            Log.d("place = ","we here");
            HDBcarparkAC = findViewById(R.id.place);
            //using strings.xml
            //place = getResources().getStringArray(R.array.HDB_carparks);
            readHDBCarparkPlaces();
            readMallCarparkPlaces();
            subject = HDBcarparkAC.getText().toString();
        }
        else if (text.trim().equals("Petrol Kiosk")){
            petrolKioskAC = (AutoCompleteTextView) findViewById(R.id.place);
            readPetrolKioskPlaces();
            subject = petrolKioskAC.getText().toString();
        }
        else if (text.trim().equals("Hawker")){
            //no data yet...
            /*hawkerAC = (AutoCompleteTextView) findViewById(R.id.place);
            readHawkerPlaces();
            subject = hawkerAC.getText().toString();*/
        }
        else{
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void readHDBCarparkPlaces(){
        int i=0;

        InputStream is = getResources().openRawResource(R.raw.hdbcarparkinfo);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try
        {
            while ((line = reader.readLine())!= null){
                String[] tokens = line.split(",");
                carparkName[i]= tokens[1];
                Log.d("place = ", carparkName[i]);
                i++;

            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMallCarparkPlaces(){
        int i=2062;

        InputStream is = getResources().openRawResource(R.raw.carparkrates);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try
        {
            while ((line = reader.readLine())!= null){
                String[] tokens = line.split(",");
                carparkName[i]= tokens[0];
                Log.d("place = ", carparkName[i]);
                i++;

            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPetrolKioskPlaces(){
        int i=0;

        InputStream is = getResources().openRawResource(R.raw.pklatest);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try
        {
            while ((line = reader.readLine())!= null){
                String[] tokens = line.split(",");
                petrolKioskName[i]= tokens[0];
                Log.d("place = ", petrolKioskName[i]);
                i++;

            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
