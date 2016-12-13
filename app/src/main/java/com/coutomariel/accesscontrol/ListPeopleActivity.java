package com.coutomariel.accesscontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.repository.Pessoas;
import com.coutomariel.accesscontrol.util.TipoMsg;
import com.coutomariel.accesscontrol.util.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class ListPeopleActivity extends AppCompatActivity {


    private List<String> lista;
    private Pessoas repository;
    private ListView lstPessoas;
    private Button addPessoa;

    private List<Pessoa> listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Listagem de registros");


        repository = new Pessoas(this);
        listaPessoas = repository.listarPessoas();

        lista = carregarLista();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lstPessoas.setAdapter(adapter);
        lstPessoas.setOnItemClickListener(clickListenerPessoas);

        addPessoa = (Button) findViewById(R.id.btnAddPeople);
        addPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListPeopleActivity.this, FormPeopleActivity.class );
                startActivity(intent);
            }
        });

    }
    private OnItemClickListener clickListenerPessoas = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Pessoa pessoa = listaPessoas.get(position);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder info = new StringBuilder();
            info.append("Nome: " + pessoa.getNome());
            info.append("\nCPF: " + pessoa.getCpf());
            info.append("\nSexo: " + pessoa.getSexo().getDescricao());
            info.append("\nSetor: " + pessoa.getSetor().getDescricao());
            info.append("\nTipo: " + pessoa.getTipoPessoa().getDescricao());
            info.append("\nDt. Nasc: " + dateFormat.format(pessoa.getDataAdmissao()));

            Util.showMsgAlertOK(ListPeopleActivity.this, "Info", info.toString(), TipoMsg.INFO);
        }
    };


    private List<String> carregarLista() {
        repository = new Pessoas(this);
        lstPessoas = (ListView) findViewById(R.id.lstPeople);


        List<Pessoa> lista = repository.listarPessoas();

        List<String> valores = new ArrayList<String>();
        for (Pessoa p : lista) {
            valores.add(p.getNome());
        }
        return valores;
    }
}
