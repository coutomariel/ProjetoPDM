package com.coutomariel.accesscontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.coutomariel.accesscontrol.model.Setor;
import com.coutomariel.accesscontrol.model.TipoPessoa;

import java.util.ArrayList;

public class FormPeopleActivity extends AppCompatActivity {
    Spinner spinnerTpPessoa;
    Spinner spinnerSetores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_people);

        spinnerTpPessoa = (Spinner) findViewById(R.id.spnTipoPessoa);
        this.carregaTiposPessoa();


        spinnerSetores = (Spinner) findViewById(R.id.spnSetor);
        this.carregaSetores();


    }

    private void carregaTiposPessoa(){
        ArrayList<String> tiposPessoa = new ArrayList<>();
        for(TipoPessoa pessoa : TipoPessoa.values() ){
            tiposPessoa.add(pessoa.getDescricao());
        }
        ArrayAdapter adapterTpPessoas = new ArrayAdapter(FormPeopleActivity.this, android.R.layout.simple_spinner_item, tiposPessoa);
        adapterTpPessoas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTpPessoa.setAdapter(adapterTpPessoas);
    }

    private void carregaSetores(){
        ArrayList<String> setores = new ArrayList<>();
        for(Setor setor : Setor.values()){
            setores.add(setor.getDescricao());
        }
        ArrayAdapter adapterSetores = new ArrayAdapter(FormPeopleActivity.this, android.R.layout.simple_spinner_item, setores);
        adapterSetores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSetores.setAdapter(adapterSetores);
    }
}
