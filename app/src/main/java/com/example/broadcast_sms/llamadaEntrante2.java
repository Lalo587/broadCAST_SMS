package com.example.broadcast_sms;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class llamadaEntrante2 extends BroadcastReceiver {
        private static final String TAG = "PhoneStatReceiver";
        private static boolean incomingFlag = false;
        private static String incoming_number = null;
    private static String mensajeGuardado = null;

        @Override
        public void onReceive (Context context, Intent intent){
            //OBTENEMOS EL MENSAJE ENVIADO DE LA CAJA DE TEXTO PARA GUARDARLO EN UNA VARIABLE
            String mensaje=intent.getStringExtra("Mensaje");
            if(mensaje!=null){
                mensajeGuardado=mensaje;
            }else{
                Toast.makeText(context, "desde el receive",Toast.LENGTH_LONG).show();
                //
                if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
                    incomingFlag = false;
                    String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                } else {
                    // TELEPHONY MANAGER OBTENIENDO EL SERVICIO DE TELEFONIA.
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

                    //SWITCH QUE PREGUNTA POR EL ESTADO DE MANAGER SI SE ENCUENTRA EN RINGING(SONANDO) O IDLE.
                    switch (tm.getCallState()) {
                        // SI EL ESTADO DEL MANAGER ES UN RINGING ES PORQUE HAY UNA LLAMADA ENTRANTE O SONANDO, ENTONCES CAPTURAMOS EL NUMERO TELEFONICO.
                        case TelephonyManager.CALL_STATE_RINGING:
                            incomingFlag = true;
                            incoming_number = intent.getStringExtra("incoming_number"); //OBTENEMOS EL NUMERO DESDE EL INTENT.
                            Toast.makeText(context, "RINGING :" + incoming_number,Toast.LENGTH_LONG).show();
                            SmsManager smgr = SmsManager.getDefault(); //CREANDO UN OBJETO DE SMS MANAGER.
                            smgr.sendTextMessage(incoming_number,null,mensajeGuardado,null,null); // ENVIAMOS EL SMS CON EL MENSAJE GUARDADO Y EL NUMERO TELEFONICO.
                            Toast.makeText(context,"Enviando mensaje a :"+incoming_number+" "+mensajeGuardado,Toast.LENGTH_LONG).show();
                            break;
                            // SI CONTESTAS LA LLAMADA.
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            if (incomingFlag) {
                                Log.i(TAG, "incoming ACCEPT :" + incoming_number);
                            }
                            break;
                            //AL TERMINAR LA LLAMADA
                        case TelephonyManager.CALL_STATE_IDLE:
                            if (incomingFlag) {
                                Toast.makeText(context, "incoming IDLE",Toast.LENGTH_LONG).show();
                            }
                            break;
                    }
                }

            }

            }





    }

