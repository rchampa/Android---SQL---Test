package es.ric.sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private EditText et_id, et_titulo, et_descripcion, et_url, et_fecha_ini, et_fecha_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText) findViewById(R.id.et_id);
        et_titulo = (EditText) findViewById(R.id.et_titulo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_url = (EditText) findViewById(R.id.et_url);
        et_fecha_ini = (EditText) findViewById(R.id.et_fecha_ini);
        et_fecha_fin = (EditText) findViewById(R.id.et_fecha_fin);

    }


    public void addPromo(View v){

        String id_texto = et_id.getText().toString();
        int id = -1;
        try{
            id = Integer.parseInt(id_texto);
        }
        catch(Exception e){
            Toast.makeText(this, "Introduce una id valida(entero)", Toast.LENGTH_SHORT).show();
            return;
        }

        String titulo = et_titulo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String url_imagen = et_url.getText().toString();
        String fecha_ini = et_fecha_ini.getText().toString();
        String fecha_fin = et_fecha_fin.getText().toString();

        PromocionVO vo = new PromocionVO(id,titulo,descripcion,url_imagen,fecha_ini,fecha_fin);
        new PromocionDAO().insert(vo);

        Toast.makeText(this, "Nueva fila", Toast.LENGTH_SHORT).show();

        et_id.setText("");
    }

    public void goToLista(View v){
        Intent intent = new Intent(this, ListaPromosActivity.class);
        startActivity(intent);
    }

}
