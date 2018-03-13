package ittepic.edu.mx.a22_shared;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener{

    private String email;
    private String celular;
    private String direccion;
    private String fecha;
    private String hora;
    private String platillos;
    private String postres;

    private String gender;
    private String hobbies;
    private String zodiac;
    private SeekBar simpleSeekBar;
    private Integer numeroseek=0;
    public static final String STORAGE_NAME = "MySharedPreferences";;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        celular = ((EditText)findViewById(R.id.txtCelular)).getText().toString();
        direccion = ((EditText)findViewById(R.id.txtDireccion)).getText().toString();
        fecha = ((EditText)findViewById(R.id.txtFecha)).getText().toString();
        hora = ((EditText)findViewById(R.id.txtHora)).getText().toString();
        platillos = ((EditText)findViewById(R.id.txtPlatillos)).getText().toString();
        postres = ((EditText)findViewById(R.id.txtPostres)).getText().toString();
        simpleSeekBar=((SeekBar)findViewById(R.id.seekbar));

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = numeroseek;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Numero de Meseros:" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();

                numeroseek=progressChangedValue;
            }
        });

        gender = "";
        hobbies = "";
        zodiac = "";
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonId);
        gender = radioButton.getText().toString();
    }
    // Add other methods

    public void onCheckboxClicked(View view) {

        CheckBox chkJogging = (CheckBox) findViewById(R.id.chkBasico);
        CheckBox chkCoding = (CheckBox) findViewById(R.id.chkLujo);

        StringBuilder sb = new StringBuilder();

        if (chkJogging.isChecked()) {
            sb.append(", " + chkJogging.getText());
        }

        if (chkCoding.isChecked()) {
            sb.append(", " + chkCoding.getText());
        }


        if (sb.length() > 0) { // No toast if the string is empty
            // Remove the first comma
            hobbies = sb.deleteCharAt(sb.indexOf(",")).toString();
        } else {
            hobbies = "";
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        zodiac = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void save(View view) {
        // Capture email input
        email = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        celular = ((EditText)findViewById(R.id.txtCelular)).getText().toString();
        direccion = ((EditText)findViewById(R.id.txtDireccion)).getText().toString();
        fecha = ((EditText)findViewById(R.id.txtFecha)).getText().toString();
        hora = ((EditText)findViewById(R.id.txtHora)).getText().toString();
        platillos = ((EditText)findViewById(R.id.txtPlatillos)).getText().toString();
        postres = ((EditText)findViewById(R.id.txtPostres)).getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("celular", celular);
        editor.putString("direccion", direccion);
        editor.putString("fecha", fecha);
        editor.putString("hora", hora);
        editor.putString("platillos", platillos);
        editor.putString("postres", postres);

        editor.putString("gender", gender);
        editor.putString("hobbies", hobbies);
        editor.putString("zodiac", zodiac);
        editor.putInt("numeseek", numeroseek);

        editor.apply();

        Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_SHORT).show();

        // To add code to save data to storage later on
    }

    public void retrieve(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        email = sharedPreferences.getString("email", "");
        celular = sharedPreferences.getString("celular", "");
        direccion = sharedPreferences.getString("direccion", "");
        fecha = sharedPreferences.getString("fecha", "");
        hora = sharedPreferences.getString("hora", "");
        platillos = sharedPreferences.getString("platillos", "");
        postres = sharedPreferences.getString("postres", "");

        gender = sharedPreferences.getString("gender", "");
        hobbies = sharedPreferences.getString("hobbies", "");
        zodiac = sharedPreferences.getString("zodiac", "");
        numeroseek = sharedPreferences.getInt("numeseek",numeroseek);

        setupUI();
    }

    protected void setupUI(){
        ((EditText)findViewById(R.id.txtNombre)).setText(email);
        ((EditText)findViewById(R.id.txtCelular)).setText(celular);
        ((EditText)findViewById(R.id.txtDireccion)).setText(direccion);
        ((EditText)findViewById(R.id.txtFecha)).setText(fecha);
        ((EditText)findViewById(R.id.txtHora)).setText(hora);
        ((EditText)findViewById(R.id.txtPlatillos)).setText(platillos);
        ((EditText)findViewById(R.id.txtPostres)).setText(postres);// Add code here

        ((SeekBar)findViewById(R.id.seekbar)).setProgress(numeroseek);

        //RadioButton radMale = (RadioButton)findViewById(R.id.radMale);
        //RadioButton radFemale = (RadioButton)findViewById(R.id.radFemale);

        CheckBox chkCoding = (CheckBox)findViewById(R.id.chkBasico);
        CheckBox chkWriting = (CheckBox)findViewById(R.id.chkLujo);


        chkCoding.setChecked(false);
        chkWriting.setChecked(false);


        if (hobbies.contains("Basica")) {
            chkCoding.setChecked(true);
        }

        if (hobbies.contains("Lujo")) {
            chkWriting.setChecked(true);
        }

    }

}