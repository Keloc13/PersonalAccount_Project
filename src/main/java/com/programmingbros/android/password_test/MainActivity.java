package com.programmingbros.android.password_test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INPUT = 0;

    private EditText mEditPassword;
    private EditText mEditName;
    private Button mCreateNewAccount;
    private Button mButtonEnter;
    List<Accounts> mListAccounts;

    String name, password;

    public MainActivity()
    {
        mListAccounts = new ArrayList<Accounts>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditPassword = (EditText) findViewById(R.id.id_inputPassword);

        mEditPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                password = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEditName = (EditText) findViewById(R.id.id_inputName);
        mEditName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                name = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCreateNewAccount = (Button) findViewById(R.id.id_create_new_account);
        mCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, CreatingNewAccount.class);
                startActivityForResult(i,REQUEST_CODE_INPUT);
            }
        });

        mButtonEnter = (Button) findViewById(R.id.id_confirm_account);
        mButtonEnter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                int listSize = mListAccounts.size(), i = 0;

               while(i < listSize && (!password.equals(mListAccounts.get(i).getPassword())
                       || !name.equals(mListAccounts.get(i).getFullName())))
                {
                   i++;
                }

                if( i < listSize) {
                Intent In = AccountSettings.newIntent(MainActivity.this,mListAccounts.get(i).getFullName(),
                        mListAccounts.get(i).getPassword(),
                        mListAccounts.get(i).getDate());
                startActivity(In);
                finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE_INPUT)
        {
            if(data == null)
                return;
            mListAccounts.add(CreatingNewAccount.inputAccount(data));
        }
    }
}
