package com.SoftwareDesign.Notizen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SendSMS extends AppCompatActivity {
    Button buttonSend;
    Button getPhoneBook;
    EditText textPhoneNo;
    EditText textSMS;
    String sNumber;
    String phoneNo;
    String sms;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        buttonSend = (Button)findViewById(R.id.buttonSend);
        getPhoneBook = findViewById(R.id.getPhoneBook);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);

        //입력한 값을 가져와 변수에 담는다
        phoneNo = textPhoneNo.getText().toString();
        sms = textSMS.getText().toString();

        //버튼 클릭이벤트
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //입력한 값을 가져와 변수에 담는다
                phoneNo = textPhoneNo.getText().toString();
                sms = textSMS.getText().toString();

                String [] PhoneNumberArray = phoneNo.split(",");

                try {
                    //전송
                    SmsManager smsManager = SmsManager.getDefault();
                    for (String PhoneNumber : PhoneNumberArray){
                        smsManager.sendTextMessage(PhoneNumber, null, sms, null, null);
                        Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();

                    }
                    finish();
                }
//
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    e.getMessage();
                }

            }
        });
        getPhoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_PICK);
                mIntent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(mIntent, 0);

                //입력한 값을 가져와 변수에 담는다
                phoneNo = textPhoneNo.getText().toString();
                sms = textSMS.getText().toString();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Cursor cursor = getContentResolver().query(
                    data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                    null, null, null);

            cursor.moveToFirst();
            sNumber = cursor.getString(1);

            String edittext = textPhoneNo.getText().toString();
            if (edittext.matches("")) {
                textPhoneNo.append(sNumber);
            }
            else{
                textPhoneNo.append(", " + sNumber);
            }
            count++;
            cursor.close();
        }

    }
}