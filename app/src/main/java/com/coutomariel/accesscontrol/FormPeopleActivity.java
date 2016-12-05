package com.coutomariel.accesscontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FormPeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_people);

        Spinner spinnerTpPessoa = (Spinner) findViewById(R.id.spnTipoPessoa);

        ArrayList<String> tiposPessoa = new ArrayList<>();
        tiposPessoa.add("Funcionário");
        tiposPessoa.add("Fornecedor");
        tiposPessoa.add("Cliente");
        tiposPessoa.add("Visitante");
        ArrayAdapter adapterTpPessoas = new ArrayAdapter(FormPeopleActivity.this, android.R.layout.simple_spinner_item, tiposPessoa);
        adapterTpPessoas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTpPessoa.setAdapter(adapterTpPessoas);

        Spinner spinnerSetores = (Spinner) findViewById(R.id.spnSetor);
        ArrayList<String> setores = new ArrayList<>();
        setores.add("Administrativo");
        setores.add("Armazém");
        setores.add("Entrega");
        setores.add("Puxada");
        setores.add("Vendas");
        ArrayAdapter adapterSetores = new ArrayAdapter(FormPeopleActivity.this, android.R.layout.simple_spinner_item, setores);
        adapterSetores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSetores.setAdapter(adapterSetores);

    }
}
