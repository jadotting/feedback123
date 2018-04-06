package com.example.jadstudios.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button carparkButton;
    private Button hawkerButton;
    private Button petrolKioskButton;
    public boolean carpark = false;
    public boolean hawker = false;
    public boolean petrolKiosk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carparkButton = (Button) findViewById(R.id.carparkFeedbackButton);
        carparkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carpark = true;
                openCarparkFeedback();
            }
        });
        hawkerButton = (Button) findViewById(R.id.hawkerFeedbackButton);
        hawkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hawker = true;
                openHawkerFeedback();
            }
        });
        petrolKioskButton = (Button) findViewById(R.id.petrolKioskFeedbackButton);
        petrolKioskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petrolKiosk = true;
                openPetrolKioskFeedback();
            }
        });
    }
    public void openCarparkFeedback(){
        Intent openCarparkFeedback = new Intent(this, carparkFeedback.class);
        startActivity(openCarparkFeedback);
    }

    public void openHawkerFeedback(){
        Intent openHawkerFeedback = new Intent(this, hawkerFeedback.class);
        startActivity(openHawkerFeedback);
    }

    public void openPetrolKioskFeedback(){
        Intent openPetrolKioskFeedback = new Intent(this, petrolKioskFeedback.class);
        startActivity(openPetrolKioskFeedback);
    }
}
