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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class existingForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText emailaddress;
    private EditText description;
    AutoCompleteTextView HDBcarparkAC;
    AutoCompleteTextView petrolKioskAC;
    AutoCompleteTextView hawkerAC;
    String [] carparkName = new String[2419];
    String [] hawkerName = new String[100];
    String [] petrolKioskName = new String[185];
    //TestingRadioButtons
    /*RadioGroup radioButtons;
    int checked;
    private static boolean carpark = false;
    private static boolean petrolKiosk = false;
    private static boolean hawker = false;*/
    //String [] place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_form);
        //TestingRadioButtons
        /*radioButtons = findViewById(R.id.radioG);
        radioButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedID){
                checked = radioButtons.indexOfChild(findViewById(checkedID));
                switch(checked){
                    case 0:
                        carpark = true;
                        Log.d("place = ","answer");
                        break;
                    case 1:
                        petrolKiosk = true;
                        break;
                    case 2:
                        hawker = true;
                        break;
                    default:
                        carpark = false;
                        petrolKiosk = false;
                        hawker = false;
                        break;
                }
            }
        });*/
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerForForms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        /*if (carpark == true){
            Log.d("place = ","we here");
            HDBcarparkAC = (AutoCompleteTextView) findViewById(R.id.place);
            //using strings.xml
            //place = getResources().getStringArray(R.array.HDB_carparks);
            readHDBCarparkPlaces();
            readMallCarparkPlaces();
            ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carparkName);
            HDBcarparkAC.setAdapter(adapterC);
        }
        else if (petrolKiosk == true){
            petrolKioskAC = (AutoCompleteTextView) findViewById(R.id.place);
            readPetrolKioskPlaces();
            ArrayAdapter<String> adapterPK = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, petrolKioskName);
            petrolKioskAC.setAdapter(adapterPK);
        }
        else{
            Log.d("place = ","bad");
            //no data yet...
            /*hawkerAC = (AutoCompleteTextView) findViewById(R.id.place);
            readHawkerPlaces();
            ArrayAdapter<String> adapterH = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hawkerName);
            petrolKioskAC.setAdapter(adapterH);
        }*/

        emailaddress = findViewById(R.id.edit_text_to);
        description = findViewById(R.id.edit_text_message);
        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        String subject = HDBcarparkAC.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int z =0;
        int y =0;
        int x = 0;
        String text = adapterView.getItemAtPosition(i).toString();
        Log.d("place = ",text);
        if (text.trim().equals("Carparks")){
            Log.d("place = ","we here");
            HDBcarparkAC = (AutoCompleteTextView) findViewById(R.id.place);
            //using strings.xml
            //place = getResources().getStringArray(R.array.HDB_carparks);
            readHDBCarparkPlaces();
            readMallCarparkPlaces();
            ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carparkName);
            HDBcarparkAC.setAdapter(adapterC);
            /*subject = HDBcarparkAC.getText().toString();
            if(subject.equals("")){
                Toast.makeText(getApplicationContext(),"Cannot be empty!",Toast.LENGTH_LONG).show();

            }*/
            /*while (carparkName[y] != null) {
                if (subject.trim().toLowerCase().equals(carparkName[y].trim().toLowerCase())) {
                    Toast.makeText(getApplicationContext(), "The petrol kiosk is in the database!", Toast.LENGTH_LONG).show();
                }
                y++;
            }*/
        }
        else if (text.trim().equals("Petrol Kiosk")){
            petrolKioskAC = (AutoCompleteTextView) findViewById(R.id.place);
            readPetrolKioskPlaces();
            ArrayAdapter<String> adapterPK = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, petrolKioskName);
            petrolKioskAC.setAdapter(adapterPK);
            //subject = petrolKioskAC.getText().toString();
        }
        else if (text.trim().equals("Hawker")){
            //no data yet...
            /*hawkerAC = (AutoCompleteTextView) findViewById(R.id.place);
            readHawkerPlaces();
            ArrayAdapter<String> adapterH = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hawkerName);
            petrolKioskAC.setAdapter(adapterH);
            subject = hawkerAC.getText().toString();*/
        }
        else {
            /*if (subject.equals("")) {
                Toast.makeText(getApplicationContext(), "Feedback on any carpark, petrol kiosk or hawker!", Toast.LENGTH_LONG).show();
            }*/
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*private void readHawkerPlaces(){
        int i=0;

        InputStream is = getResources().openRawResource(R.raw.hawker);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try
        {
            while ((line = reader.readLine())!= null){
                String[] tokens = line.split(",");
                hawkerName[i]= tokens[1];
                Log.d("place = ", hawkerName[i]);
                i++;

            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}

