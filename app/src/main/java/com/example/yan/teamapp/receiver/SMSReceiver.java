package com.example.yan.teamapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Yan on 28/04/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context,Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String format = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, format);

        String telefone = sms.getDisplayOriginatingAddress();

        Toast.makeText(context,"Chegou uma msg bolada ", Toast.LENGTH_SHORT).show();
        }

    }

