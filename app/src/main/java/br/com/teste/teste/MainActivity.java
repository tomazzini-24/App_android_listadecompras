package br.com.teste.teste;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.Normalizer;
import java.util.List;

import br.com.teste.teste.DAO.ProdutosDAO;
import br.com.teste.teste.model.AdapterProdutos;
import br.com.teste.teste.model.FormularioProdutos;
import br.com.teste.teste.model.Produtos;


public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;
    //    private ArrayAdapter adapter;
    private AdapterProdutos adapter;
    private List<Produtos> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
                intent.putExtra("acao", "novo");
                startActivity( intent );
            }
        });


        lvProdutos = findViewById(R.id.lvProdutos);

        carregarProdutos();

        configurarListView();

    }

    private void configurarListView(){

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produtos produtosSelecionado = listaProdutos.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idPizza", produtosSelecionado.getId() );
                startActivity( intent );
            }
        });

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produtos produtosSelecionado = listaProdutos.get(position);
                excluirProduto( produtosSelecionado );
                return true;
            }
        });

    }



    private void  excluirProduto( Produtos produtos ){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Confirma a exclusão do pedido " + produtos.getNome() +"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutosDAO.excluir( produtos.getId(), MainActivity.this);
                carregarProdutos();
            }
        });
        alerta.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarProdutos();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarProdutos(){
        listaProdutos = ProdutosDAO.getProdutos(this);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPizzas);
        adapter = new AdapterProdutos(this, listaProdutos);
        lvProdutos.setAdapter( adapter );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}