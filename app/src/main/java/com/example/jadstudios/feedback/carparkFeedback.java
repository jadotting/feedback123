package com.example.jadstudios.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class carparkFeedback extends AppCompatActivity {
    private Button eCarparkbutton;
    private Button nCarparkbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpark_feedback);

        eCarparkbutton = (Button) findViewById(R.id.existingCarparkButton);
        eCarparkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExistingForm();
            }
        });
        nCarparkbutton = (Button) findViewById(R.id.newCarparkButton);
        nCarparkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewForm();
            }
        });
    }
    public void openExistingForm(){
        Intent openExistingFormCP = new Intent(this, existingForm.class);
        startActivity(openExistingFormCP);
    }
    public void openNewForm(){
        Intent openNewFormCP = new Intent(this, newForm.class);
        startActivity(openNewFormCP);
    }
}
