package com.coutomariel.accesscontrol;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.coutomariel.accesscontrol.fragment.DatePickerFragment;
import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.model.Setor;
import com.coutomariel.accesscontrol.model.Sexo;
import com.coutomariel.accesscontrol.model.TipoPessoa;
import com.coutomariel.accesscontrol.repository.Pessoas;
import com.coutomariel.accesscontrol.util.Util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.view.View.SCALE_X;

public class FormPeopleActivity extends AppCompatActivity {

    private static final int CODIGO_CAMERA = 567;

    private String caminhoFoto;

    private Spinner spinnerTpPessoa, spinnerSetores;
    private RadioGroup rbgSexo;
    private EditText edtNome, edtCpf, edtAdmissao;
    private ImageView campoFoto;

    private Pessoas pessoas;

    private Button btnSalvar;

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

        campoFoto = (ImageView) findViewById(R.id.imgFoto);

        Button btnFoto = (Button) findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/"+ System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

        this.carregaTiposPessoa();
        this.carregaSetores();

        btnSalvar = (Button) findViewById(R.id.btnEnviar);
        btnSalvar.setOnClickListener(clickSalvar);
    }

    private View.OnClickListener clickSalvar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Pessoa pessoa = montarPessoa();
            Util.showMsgToast(FormPeopleActivity.this, "Enviando");
            pessoas.salvarPessoa(pessoa);
            Intent i = new Intent(FormPeopleActivity.this, ListPeopleActivity.class);
            startActivity(i);
            finish();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == CODIGO_CAMERA) {
                ImageView foto = (ImageView) findViewById(R.id.imgFoto);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                foto.setImageBitmap(bitmapReduzido);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);
                foto.setTag(caminhoFoto);
            }
        }

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
        Util.showMsgToast(this, "Enviando");
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

        pessoa.setCaminhoFoto((String) campoFoto.getTag());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
