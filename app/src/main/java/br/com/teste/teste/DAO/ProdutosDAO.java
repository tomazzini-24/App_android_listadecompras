package br.com.teste.teste.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.teste.teste.model.Produtos;

public class ProdutosDAO  {


    //Salvar

    public static void inserir(Produtos produtos, Context context){
        ContentValues values = new ContentValues();
        values.put("nome", produtos.getNome());
        values.put("quantidade", produtos.getQuantidade());
        values.put("preco", produtos.getPreco());
        values.put("total", produtos.getTotal());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("lista", null, values);
    }

    public static void editar(Produtos produtos, Context context){
        ContentValues values = new ContentValues();
        values.put("nome", produtos.getNome());
        values.put("quantidade", produtos.getQuantidade());
        values.put("preco", produtos.getPreco());
        values.put("total", produtos.getTotal());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

                db.update("lista", values, " id = " + produtos.getId() , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("lista", " id = " + id , null);
    }
    /*public  void  salvarProduto(Produtos produto ){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("quantidade", produto.getQuantidade());
        values.put("preco", produto.getPreco());
        values.put("total", produto.getTotal());

        getWritableDatabase().insert("produtos", null, values);
    }*/

    //Listar

    public static List<Produtos> getProdutos(Context context){
        List<Produtos> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, quantidade, preco, total FROM lista ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Produtos produtos = new Produtos();
                produtos.setId(cursor.getInt(0));
                produtos.setNome(cursor.getString(1));
                produtos.setQuantidade(cursor.getInt(2));
                produtos.setPreco(cursor.getDouble(3));
                produtos.setPreco(cursor.getDouble(4));
                lista.add(produtos);
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    /* public ArrayList<Produtos> getLista(){
         String [] columns = {"id", "nome", "quantidade", "preco", "total"};
         Cursor cursor = getWritableDatabase().query("produtos", columns, null, null, null, null, null, null);
         ArrayList<Produtos> produtos = new ArrayList<Produtos>();

         while (cursor.moveToNext()){
             Produtos produtos = new Produtos();
             produtos.(cursor.getInt(0));
             produtos.setNome(cursor.getString(1));
             produtos.setQuantidade(cursor.getInt(2));
             produtos.setPreco(cursor.getDouble(3));
             produtos.setPreco(cursor.getDouble(4));

             produtos.add(produtos); // inserir dentro do array
         }
         return produtos;
     }*/
   public static Produtos getProdutosById(Context context, int id){
       Banco banco = new Banco(context);
       SQLiteDatabase db = banco.getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT id, nome, quantidade, preco, total FROM lista WHERE id = " + id, null );
       if( cursor.getCount() > 0 ){
           cursor.moveToFirst();
           Produtos produtos = new Produtos();
           produtos.setId(cursor.getInt(0));
           produtos.setNome(cursor.getString(1));
           produtos.setQuantidade(cursor.getInt(2));
           produtos.setPreco(cursor.getDouble(3));
           produtos.setPreco(cursor.getDouble(4));
           return produtos;
       }else{
           return null;
       }
   }
}
