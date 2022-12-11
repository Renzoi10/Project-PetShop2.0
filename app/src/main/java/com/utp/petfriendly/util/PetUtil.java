package com.utp.petfriendly.util;

import android.app.Activity;
import android.app.ProgressDialog;

import com.utp.petfriendly.R;

public class PetUtil {

    public static ProgressDialog getProgressDialog(Activity activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.screenDialog);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
