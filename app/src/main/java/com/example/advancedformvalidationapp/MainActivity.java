package com.example.advancedformvalidationapp;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.email);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        EditText password = (EditText) findViewById(R.id.password);
        EditText passwordConfirmation = (EditText) findViewById(R.id.passwordConfirmation);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstNameS = firstName.getText().toString();
                String lastNameS = lastName.getText().toString();
                String emailS = email.getText().toString();
                String phoneNumberS = phoneNumber.getText().toString();
                String passwordS = password.getText().toString();
                String passwordConfirmationS = passwordConfirmation.getText().toString();
                int taskCount = 0;

                // walidacja imieni oraz nazwiska
                if(!(firstNameS.isEmpty()) && !(lastNameS.isEmpty())){
                    Log.d("OK", firstNameS + " " + lastNameS);
                    taskCount++;
                } else {
                    if(firstNameS.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Pole Imie jest puste", Toast.LENGTH_SHORT).show();
                    }
                    if(lastNameS.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Pole Nazwisko jest puste", Toast.LENGTH_SHORT).show();
                    }
                }

                // walidacja e-mail
                if(Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
                    Log.d("OK", emailS);
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Bledny email", Toast.LENGTH_SHORT).show();
                }

                // walidacja numeru telefonu
                int num = 0;
                for(int i=0; i<phoneNumberS.length(); i++){
                    num++;
                }
                if(num>=9){
                    Log.d("OK", String.valueOf(num));
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Numer telefonu nie moze byc mniejszy niz 9", Toast.LENGTH_SHORT).show();
                }

                // walidacja hasla
                int pasNum = 0;
                for(int i=0; i<passwordS.length(); i++){
                    pasNum++;
                }
                if(pasNum>=6){
                    Log.d("OK", String.valueOf(pasNum));
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Haslo nie moze byc mniejsze niz 9", Toast.LENGTH_SHORT).show();
                }

                // walidacja hasla
                if(passwordS.equals(passwordConfirmationS)){
                    Log.d("OK", passwordS+" "+passwordConfirmationS);
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Hasla powinny sie zgadzac", Toast.LENGTH_SHORT).show();
                }

                if(taskCount==5){
                    Toast.makeText(getApplicationContext(), "Wyslij", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}