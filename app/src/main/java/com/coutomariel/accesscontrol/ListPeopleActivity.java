package com.coutomariel.accesscontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.repository.Pessoas;

import java.util.ArrayList;
import java.util.List;

public class ListPeopleActivity extends AppCompatActivity {

    private Pessoas repository;
    private ListView lstPessoas;
    private Button addPessoa;

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

        addPessoa = (Button) findViewById(R.id.btnAddPeople);
        addPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListPeopleActivity.this, FormPeopleActivity.class );
                startActivity(intent);
            }
        });

    }
}
