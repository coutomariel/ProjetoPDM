package com.coutomariel.accesscontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

    private ArrayAdapter adapter;

    private Pessoas repository;

    private List<Pessoa> listaPessoas;

    private List<String> lista;

    private ListView lstPessoas;

    private Button addPessoa;

    private int posicaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Listagem de registros");


        repository = new Pessoas(this);
//        listaPessoas = repository.listarPessoas();
        lstPessoas = (ListView) findViewById(R.id.lstPeople);

        listaPessoas = repository.listarPessoas();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        carregarLista();
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
//        lstPessoas.setAdapter(adapter);

        lstPessoas.setOnItemClickListener(clickListenerPessoas);
        lstPessoas.setOnCreateContextMenuListener(contextMenuListener);
        lstPessoas.setOnItemLongClickListener(longClickLister);

        addPessoa = (Button) findViewById(R.id.btnAddPeople);
        addPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListPeopleActivity.this, FormPeopleActivity.class );
                startActivity(intent);
            }
        });

    }

    private void carregarLista() {

        listaPessoas.clear();
        listaPessoas = repository.listarPessoas();

        List<String> valores = new ArrayList<String>();
        for (Pessoa p : listaPessoas) {
            valores.add(p.getNome());
        }

        adapter.addAll(valores);
        lstPessoas.setAdapter(adapter);
    }

    private OnItemLongClickListener longClickLister = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
            posicaoSelecionada = position;
            return false;
        }
    };

    private OnItemClickListener clickListenerPessoas = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Pessoa pessoa = repository.buscarPorId(listaPessoas.get(position).getId());

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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case 10:
                    Util.showMsgToast(this, "Editar");
                    break;
                case 20:
                    Util.showMsgConfirm(ListPeopleActivity.this, "Remover registro", "Deseja realmente remover este registro?", TipoMsg.ALERTA, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int id = listaPessoas.get(posicaoSelecionada).getId();
                            repository.removerPorId(id);
                            carregarLista();
                            adapter.notifyDataSetChanged();
                        }
                    });
                    break;
                case 30:
                    Util.showMsgToast(this, listaPessoas.get(posicaoSelecionada).getNome());
                    break;

            }
        return true;
    }

    private OnCreateContextMenuListener contextMenuListener = new OnCreateContextMenuListener() {
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(1,10,1,"Editar");
            contextMenu.add(1,20,2,"Deletar");
            contextMenu.add(1,30,3,"Navegar");
        }
    };
}
