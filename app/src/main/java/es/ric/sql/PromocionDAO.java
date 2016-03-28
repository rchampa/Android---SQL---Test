package es.ric.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;



public class PromocionDAO {

	protected static final String TABLE = "promociones";
	protected static final String ID = "id";
	protected static final String TITULO = "nombre";
	protected static final String DESCRIPCION = "titulo";
	protected static final String URL = "url_imagen";
	protected static final String ENLACE = "url_enlace";
	protected static final String TERCERA = "tercera_actualizacion";
	protected static final String FECHA_INI = "fecha_inicio";
	protected static final String FECHA_FIN = "fecha_fin";
	
	
	public int getNumberOfCentros() {
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteStatement s = db.compileStatement( "select count(*) from "+TABLE );

		long count = s.simpleQueryForLong();
		return (int)count;
	}
	
	public List<PromocionVO> getAllPromos() {

		List<PromocionVO> list = new ArrayList<PromocionVO>();
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null,
				null, null);

		if (cursor.moveToFirst()) {

			PromocionVO promocion;
			do {
				int id = cursor.getInt(cursor.getColumnIndex(ID));
				String nombre = cursor.getString(cursor.getColumnIndex(TITULO));
				String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
				String url = cursor.getString(cursor.getColumnIndex(URL));
				String fecha_ini = cursor.getString(cursor.getColumnIndex(FECHA_INI));
				String fecha_fin = cursor.getString(cursor.getColumnIndex(FECHA_FIN));

				
				promocion = new PromocionVO(id, nombre, descripcion, url, fecha_ini, fecha_fin);
				
				list.add(promocion);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		return list;
	}

	public PromocionVO get(int id) {
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, ID + "=?", new String[]{"" + id}, null, null, null);
		PromocionVO promocion = null;
		if (cursor.moveToFirst()) {

			String nombre = cursor.getString(cursor.getColumnIndex(TITULO));
			String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
			String url = cursor.getString(cursor.getColumnIndex(URL));
			String fecha_ini = cursor.getString(cursor.getColumnIndex(FECHA_INI));
			String fecha_fin = cursor.getString(cursor.getColumnIndex(FECHA_FIN));

			
			promocion = new PromocionVO(id, nombre, descripcion, url, fecha_ini,fecha_fin);

		}

		cursor.close();
		db.close();
		return promocion;
	}

	public long insert(PromocionVO promocion) {
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, promocion.getId());
		values.put(TITULO, promocion.getTitulo());
		values.put(DESCRIPCION, promocion.getDescripcion());
		values.put(URL, promocion.getUrl_imagen());
		values.put(FECHA_INI, promocion.getFecha_ini());
		values.put(FECHA_FIN, promocion.getFecha_fin());

		long num = db.insertOrThrow(TABLE, null, values);
		return num;
	}

	private long insert(SQLiteDatabase db, PromocionVO promocion) {

		ContentValues values = new ContentValues();
		values.put(ID, promocion.getId());
		values.put(TITULO, promocion.getTitulo());
		values.put(DESCRIPCION, promocion.getDescripcion());
		values.put(URL, promocion.getUrl_imagen());
		values.put(FECHA_INI, promocion.getFecha_ini());
		values.put(FECHA_FIN, promocion.getFecha_fin());

		long num = db.insertOrThrow(TABLE, null, values);
		return num;
	}
	
	public void insertAll(ArrayList<PromocionVO> lista_promocion){

		SQLiteDatabase db = null;
		try {
			db = new DatabaseHelper().getWritableDatabase();
		    db.beginTransaction();
		    db.delete(TABLE, null, null);
		    for (PromocionVO centro : lista_promocion) {
				insert(db, centro);
			}
		    
		    db.setTransactionSuccessful();
		} catch(SQLException e) {
		} catch(Exception e) {
		} finally {
			if(db!=null){
				db.endTransaction();
				db.close();
			}
		}
		
		
	}
	
	 public void deleteAll() {
		 SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		 db.delete(TABLE, null, null);
		 db.close();
	 }
	 

}
