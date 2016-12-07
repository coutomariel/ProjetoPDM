package com.coutomariel.accesscontrol;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.coutomariel.accesscontrol.fragment.DatePickerFragment;
import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.model.Setor;
import com.coutomariel.accesscontrol.model.Sexo;
import com.coutomariel.accesscontrol.model.TipoPessoa;
import com.coutomariel.accesscontrol.repository.Pessoas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormPeopleActivity extends AppCompatActivity {

    private Spinner spinnerTpPessoa, spinnerSetores;
    private RadioGroup rbgSexo;
    private EditText edtNome, edtCpf, edtAdmissao;

    private Pessoas pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_people);

        getSupportActionBar().setTitle("Cadastro de Pessoa");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pessoas = new Pessoas(this);

        spinnerTpPessoa = (Spinner) findViewById(R.id.spnTipoPessoa);
        spinnerSetores = (Spinner) findViewById(R.id.spnSetor);

        rbgSexo = (RadioGroup) findViewById(R.id.rbgSexo);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCpf = (EditText) findViewById(R.id.edtCpf);
        edtAdmissao = (EditText) findViewById(R.id.edtAdmissao);

        this.carregaTiposPessoa();
        this.carregaSetores();
    }


    public void setData(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        Calendar cal = Calendar.getInstance();

        Bundle bundle = new Bundle();
        bundle.putInt("dia", cal.get(Calendar.DAY_OF_MONTH));
        bundle.putInt("mes", cal.get(Calendar.MONTH));
        bundle.putInt("ano", cal.get(Calendar.YEAR));

        datePickerFragment.setArguments(bundle);
        datePickerFragment.setDateListener(dateListener);
        datePickerFragment.show(getFragmentManager(), "Data Adimissão.");
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edtAdmissao.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };

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

    public void enviarPessoa(View view){
        Toast.makeText(this, "Pessoa:"+montarPessoa(), Toast.LENGTH_SHORT).show();
        Pessoa pessoa = montarPessoa();
        pessoas.salvarPessoa(pessoa);
        Intent i = new Intent(this, ListPeopleActivity.class);
        startActivity(i);
        finish();
    }

    private Pessoa montarPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edtNome.getText().toString());
        pessoa.setCpf(edtCpf.getText().toString());
        switch (rbgSexo.getCheckedRadioButtonId()) {
            case R.id.rbtMasc:
                pessoa.setSexo(Sexo.MASCULINO);
                break;
            case R.id.rbtFem:
                pessoa.setSexo(Sexo.FEMININO);
                break;
        }

        Setor setor = Setor.getSetor(spinnerSetores.getSelectedItemPosition());
        pessoa.setSetor(setor);

        TipoPessoa tipoPessoa = TipoPessoa.getTipoPessoa(spinnerTpPessoa.getSelectedItemPosition());
        pessoa.setTipoPessoa(tipoPessoa);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date admissao = dateFormat.parse(edtAdmissao.getText().toString());
            pessoa.setDataAdmissao(admissao);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  pessoa;
    }
}
