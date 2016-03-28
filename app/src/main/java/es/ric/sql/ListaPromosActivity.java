package es.ric.sql;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ricardo on 28/03/16.
 */
public class ListaPromosActivity extends ListActivity {
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        content = (TextView)findViewById(R.id.output);

        //listView = (ListView) findViewById(R.id.list);
        List<PromocionVO> lista_promos = new PromocionDAO().getAllPromos();

        ArrayAdapter<PromocionVO> adapter = new ArrayAdapter<PromocionVO>(this,
                android.R.layout.simple_list_item_1, lista_promos);


        // Assign adapter to List
        setListAdapter(adapter);
    }


//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//
//        super.onListItemClick(l, v, position, id);
//
//        // ListView Clicked item index
//        int itemPosition     = position;
//
//        // ListView Clicked item value
//        String  itemValue    = (String) l.getItemAtPosition(position);
//
//        content.setText("Click : \n  Position :"+itemPosition+"  \n  ListItem : " +itemValue);
//
//    }
}

