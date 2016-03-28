package es.ric.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "prueba_sql.db";
	private static final int DATABASE_VERSION = 3;

	public DatabaseHelper() {
		super(MyApp.getContext(),DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		MyLogs.show("onCreate");
		db.execSQL("create table " + PromocionDAO.TABLE + " (" 
				+ PromocionDAO.ID	+ " integer primary key," 
				+ PromocionDAO.TITULO + " text,"
				+ PromocionDAO.DESCRIPCION + " text,"
				+ PromocionDAO.URL + " text,"
				+ PromocionDAO.ENLACE + " text,"
				+ PromocionDAO.TERCERA + " text,"
				+ PromocionDAO.FECHA_INI + " text,"
				+ PromocionDAO.FECHA_FIN + " text)");
	}

    /**
     * Sólo se ejecuta cuando se incrementa la variable DATABASE_VERSION
     * PRECAUCIONES
     * Tener en cuenta que actualizar la version de la APP no tiene nada que ver con sqlite
     * No volver a crear la misma tabla
     * Preservar las actualizaciones anteriores, teniendo en cuenta desde que version se actualiza.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MyLogs.show("onUpgrade");

        // Nunca más se puede borrar esta línea por tema de retrocompatibilidad
        if (newVersion > oldVersion) {

            //NO USAR BREAKS, de esta manera se ejecutan todas las acutalizaciones necesarias
            //y en el orden adecuado
            switch(oldVersion){
                case 1:
                    MyLogs.show("viene de version 1");
                    try {
                        db.execSQL("ALTER TABLE " + PromocionDAO.TABLE + " ADD COLUMN " + PromocionDAO.ENLACE + " text");
                        MyLogs.show("Nueva columna");
                    }
                    catch(Exception e){
                        MyLogs.show("La columna ya existia");
                    }

                case 2:
                    MyLogs.show("viene de version 2");
                    try {
                        db.execSQL("ALTER TABLE " + PromocionDAO.TABLE + " ADD COLUMN " + PromocionDAO.TERCERA + " text");
                        MyLogs.show("Nueva columna");
                    }
                    catch(Exception e){
                        MyLogs.show("La columna ya existia");
                    }
            }

        }
		
	}
	

	public void deleteAllData(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(PromocionDAO.TABLE, null, null);//los promos son los mismos para todos los usuarios
		db.close();
	}
	

}
