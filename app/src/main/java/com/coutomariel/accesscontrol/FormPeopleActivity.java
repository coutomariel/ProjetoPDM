package com.coutomariel.accesscontrol;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.coutomariel.accesscontrol.com.coutomariel.accesscontrol.fragment.DatePickerFragment;
import com.coutomariel.accesscontrol.model.Setor;
import com.coutomariel.accesscontrol.model.TipoPessoa;
import com.coutomariel.accesscontrol.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class FormPeopleActivity extends AppCompatActivity {

    private Spinner spinnerTpPessoa, spinnerSetores;

    private EditText edtAdmissao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_people);

        spinnerTpPessoa = (Spinner) findViewById(R.id.spnTipoPessoa);
        this.carregaTiposPessoa();


        spinnerSetores = (Spinner) findViewById(R.id.spnSetor);
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
        datePickerFragment.show(getFragmentManager(), "Data Adissão.");
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
}
