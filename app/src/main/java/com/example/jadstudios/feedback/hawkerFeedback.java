package com.example.jadstudios.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hawkerFeedback extends AppCompatActivity {
    private Button eHawkerbutton;
    private Button nHawkerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hawker_feedback);

        eHawkerbutton = (Button) findViewById(R.id.existingHawkerButton);
        eHawkerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExistingForm();
            }
        });
        nHawkerbutton = (Button) findViewById(R.id.newHawkerButton);
        nHawkerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewForm();
            }
        });
    }
    public void openExistingForm(){
        Intent openExistingFormHC = new Intent(this, existingForm.class);
        startActivity(openExistingFormHC);
    }
    public void openNewForm(){
        Intent openNewFormHC = new Intent(this, newForm.class);
        startActivity(openNewFormHC);
    }

}
