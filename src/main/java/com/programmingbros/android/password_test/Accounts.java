package com.programmingbros.android.password_test;

import java.util.Date;
import java.util.UUID;

/**
 * Created by KevinVuNguyen on 6/13/17.
 */

public class Accounts {
    private String mFullName;
    private String mPassword;
    private Date mBirthday;

    public Accounts(String name, String password, Date bday)
    {
        mFullName = name;
        mPassword = password;
        mBirthday = bday;
    }

    public String getFullName(){ return mFullName;}
    public String getPassword(){ return mPassword;}
    public Date getDate(){ return mBirthday;}
    public String getDateTime(){return mBirthday.toString();}

    public void setFullName(String newName) { mFullName = newName;}
    public void setPassword(String newPassword){ mPassword = newPassword;}
    public void setBirthday(Date newBday){ mBirthday = newBday;}

    public String toString()
    {
        return "Name: " + mFullName +
                "Birthday: " + mBirthday.toString();
    }
}
