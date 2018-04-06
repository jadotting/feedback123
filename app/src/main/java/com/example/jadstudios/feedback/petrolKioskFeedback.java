package com.example.jadstudios.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class petrolKioskFeedback extends AppCompatActivity {
    private Button ePetrolKioskButton;
    private Button nPetrolKioskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_kiosk_feedback);

        ePetrolKioskButton = (Button) findViewById(R.id.existingPetrolKioskButton);
        ePetrolKioskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExistingForm();
            }
        });
        nPetrolKioskButton = (Button) findViewById(R.id.newPetrolKioskButton);
        nPetrolKioskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewForm();
            }
        });
    }
    public void openExistingForm(){
        Intent openExistingFormPK = new Intent(this, existingForm.class);
        startActivity(openExistingFormPK);
    }
    public void openNewForm(){
        Intent openNewFormPK = new Intent(this, newForm.class);
        startActivity(openNewFormPK);
    }
}
