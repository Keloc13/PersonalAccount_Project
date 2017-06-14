package com.programmingbros.android.password_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by KevinVuNguyen on 6/13/17.
 */

public class CreatingNewAccount extends AppCompatActivity{

    private static final String EXTRA_ANSWER_INPUT_NAME = "inputtingName";
    private static final String EXTRA_ANSWER_INPUT_PASSWORD = "inputtingPassword";
    private static final String EXTRA_ANSWER_INPUT_BIRTHDAY = "inputtingDate";

    private EditText mUserName;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mBirthday;
    private Button mButtonCreate;
    private Accounts mInputAccount;

    String name,
            password,
            confirmPassword;
    Date Birthday;

    public CreatingNewAccount()
    {
        name = "";
        password = "";
        confirmPassword = "";
        Birthday = null;
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mUserName = (EditText) findViewById(R.id.id_inputName_create);
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence != null)
                name = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPassword = (EditText) findViewById(R.id.id_inputPassword_create);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null)
                password = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPasswordConfirm = (EditText) findViewById(R.id.id_inputPassword_create_confirm);
        mPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null)
                confirmPassword = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mBirthday = (EditText) findViewById(R.id.id_input_birthday_confirm);
        mBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null)
                Birthday = new Date();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mButtonCreate = (Button) findViewById(R.id.id_button_create_confirm);
        mButtonCreate.setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View view)
            {
                if( !name.equals("") && password.equals(confirmPassword)) {
                    setAnswerShownResult(name, password, Birthday);
                    Toast.makeText(CreatingNewAccount.this, "Creating new account", Toast.LENGTH_SHORT).show();

                    Intent i = AccountSettings.newIntent(CreatingNewAccount.this,name, password, Birthday);
                    startActivity(i);

                    finish();
                }
                else{
                    Toast.makeText(CreatingNewAccount.this,"Passwords aren't the same", Toast.LENGTH_SHORT).show();
                }

             }
    });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private void setAnswerShownResult(String inputName, String inputPassword, Date date)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_INPUT_NAME, inputName);
        data.putExtra(EXTRA_ANSWER_INPUT_PASSWORD, inputPassword);
        data.putExtra(EXTRA_ANSWER_INPUT_BIRTHDAY, date);
        setResult(RESULT_OK, data);
    }

    public static Accounts inputAccount(Intent result)
    {
        return new Accounts(result.getStringExtra(EXTRA_ANSWER_INPUT_NAME), result.getStringExtra(EXTRA_ANSWER_INPUT_PASSWORD)
        , (Date) result.getParcelableExtra(EXTRA_ANSWER_INPUT_BIRTHDAY));
    }
}
