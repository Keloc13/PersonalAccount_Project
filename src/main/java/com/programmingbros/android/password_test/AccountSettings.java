package com.programmingbros.android.password_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by KevinVuNguyen on 6/13/17.
 */

public class AccountSettings extends AppCompatActivity{
    private TextView mName, mBirthday;
    private static final String EXTRA_ACCOUNT1 = "accountName";
    private static final String EXTRA_ACCOUNT2 = "accountBday";

    private String name, birthday;

    @Override
    protected void onCreate(Bundle onInstanceState)
    {
        super.onCreate(onInstanceState);
        setContentView(R.layout.activity_account_settings);

        name = getIntent().getStringExtra(EXTRA_ACCOUNT1);
        mName = (TextView) findViewById(R.id.id_show_name_account);
        mName.setText(name);

        birthday = getIntent().getStringExtra(EXTRA_ACCOUNT2);
        mBirthday = (TextView) findViewById(R.id.id_show_birthday_account);
        mBirthday.setText(birthday);

    }

    public static Intent newIntent(Context packageContext, String name, String password, Date Birthday)
    {
        Intent i = new Intent(packageContext, AccountSettings.class);
        i.putExtra(EXTRA_ACCOUNT1, name);
        i.putExtra(EXTRA_ACCOUNT2, Birthday);
        return i;
    }

}
