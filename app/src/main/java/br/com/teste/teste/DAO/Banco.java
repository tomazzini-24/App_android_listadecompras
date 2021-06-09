package br.com.teste.teste.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 3;
    private static final String NOME = "ListaDeCompras";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS lista ( " +
                "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "     nome TEXT NOT NULL ," +
                "     quantidade INTEGER," +
                "     preco INTEGER NOT NULL," +
                "     total INTEGER NOT NULL  ) "  );
    }

   /* private static final String DATABASE = "ListaCompras";
    private static final int VERSION = 1;

    public Banco (Context context ){
        super(context, DATABASE, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = " CREATE TABLE produtos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, produto TEXT NOT NULL, quantidade INTEGER NOT NULL, preco INTEGER);";

        db.execSQL(produto);
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);

    }
}
