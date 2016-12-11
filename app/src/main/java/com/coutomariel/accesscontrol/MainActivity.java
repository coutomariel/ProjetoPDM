package com.coutomariel.accesscontrol;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coutomariel.accesscontrol.util.TipoMsg;
import com.coutomariel.accesscontrol.util.Util;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Bem vindo Mariel");

        //Utilização de toast personalizados
        //Util.showMsgToast(this, "Teste do toast personalizado!");

        //Utilização de alerts personalizados
        //Util.showMsgAlertOK(this, "Confirmação", "Deseja realmente deletar este registro", TipoMsg.ALERTA);
    }
}
