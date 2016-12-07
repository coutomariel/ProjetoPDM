package com.coutomariel.accesscontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.repository.Pessoas;

import java.util.ArrayList;
import java.util.List;

public class ListPeopleActivity extends AppCompatActivity {

    private Pessoas repository;
    private ListView lstPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        repository = new Pessoas(this);
        lstPessoas = (ListView) findViewById(R.id.lstPeople);

        List<Pessoa> lista = repository.listarPessoas();

        List<String> valores = new ArrayList<String>();
        for (Pessoa p : lista) {
            valores.add(p.getNome());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        lstPessoas.setAdapter(adapter);


    }
}
