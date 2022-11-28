package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class RentalRegister extends AppCompatActivity {

    private EditText userNameEdt, emailEdt, passwordEdt, cpasswordEdt;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_register);
    }

    private boolean CheckAllFields() {

        EditText pNameET = findViewById(R.id.idPptName);
        EditText pEmailET = findViewById(R.id.idPptEmail);
        EditText pAddressEt = findViewById(R.id.idPptAdr);
        EditText pCountryET = findViewById(R.id.idPptCou);
        EditText pStateET = findViewById(R.id.idPptStat);
        EditText pZipcodeET = findViewById(R.id.idPptZip);
        EditText pCityET = findViewById(R.id.idPptCity);

        if (pNameET.length() == 0) {
            pNameET.setError("Property Name is required");
            return false;
        }
        if (pEmailET.length() == 0) {
            pEmailET.setError("Email is required");
            return false;
        }

        if (pAddressEt.length() == 0) {
            pAddressEt.setError("Address is required");
            return false;
        }
        if (pCountryET.length() == 0) {
            pCountryET.setError("Country is required");
            return false;
        }

        if (pStateET.length() == 0) {
            pStateET.setError("State is required");
            return false;
        }

        if (pZipcodeET.length() == 0) {
            pZipcodeET.setError("Zipcode is required");
            return false;
        }

        if (pCityET.length() == 0) {
            pCityET.setError("City is required");
            return false;
        }

        // after all validation return true.
        return true;
    }


    public void onClickRegister(View v){

        try{
            isAllFieldsChecked = CheckAllFields();

            if (!isAllFieldsChecked){
                return;
            }
            //
            EditText pNameET = findViewById(R.id.idPptName);
            EditText pEmailET = findViewById(R.id.idPptEmail);
            EditText pAddressEt = findViewById(R.id.idPptAdr);
            EditText pCountryET = findViewById(R.id.idPptCou);
            EditText pStateET = findViewById(R.id.idPptStat);
            EditText pZipcodeET = findViewById(R.id.idPptZip);
            EditText pCityET = findViewById(R.id.idPptCity);
            //

            String pName = pNameET.getText().toString();
            String pEmail = pEmailET.getText().toString();
            String pAddr = pAddressEt.getText().toString();
            String pCountry = pCountryET.getText().toString();
            String pState = pStateET.getText().toString();
            String pZipcode = pZipcodeET.getText().toString();
            String pCity = pCityET.getText().toString();

            BrcAPIInterface api = new BrcAPIInterface(RentalRegister.this);
            JSONObject requestBody = new JSONObject();
            // Request Body
            /*
              {
                  "name": "string",
                  "email": "string",
                  "address1": "string",
                  "city": "string",
                  "state": "string",
                  "country": "string",
                  "zipcode": 0
                }
             */
            requestBody.put("name", pName);
            requestBody.put("email", pEmail);
            requestBody.put("address1", pAddr);
            requestBody.put("city", pCity);
            requestBody.put("state", pState);
            requestBody.put("country", pCountry);
            requestBody.put("zipcode", Integer.parseInt(pZipcode));
            requestBody.put("phone", 0000000000);

            Log.v("R BODY", requestBody.toString());
            //
            api.brcPostAPI("/rental-posting",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Toast.makeText(RentalRegister.this, "Rental Creation Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(RentalRegister.this, "Rental Creation Failed", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);


                }

            });

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(RentalRegister.this, "Unexpected Error Occured", Toast.LENGTH_SHORT).show();
        }finally{
            finish();

        }

    }


}