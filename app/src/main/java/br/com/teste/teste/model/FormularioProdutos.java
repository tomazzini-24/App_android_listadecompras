package br.com.teste.teste.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.teste.teste.DAO.ProdutosDAO;
import br.com.teste.teste.R;

public class FormularioProdutos extends AppCompatActivity {
    private EditText etProduto, etQuantidade, etPreco;
    private Button btnOk;
    private Produtos produtos;
    private String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_produtos);

        //Intent intent = getIntent();
        //editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        etProduto = findViewById( R.id.etProduto);
        etQuantidade = findViewById( R.id.etQuantidade);
        etPreco = findViewById( R.id.etPreco);
        btnOk = findViewById( R.id.btnOk );

      //  editarProduto = getIntent().getStringExtra("acao");

       /* if(editarProduto != null){
            btnOk.setText("Modificar");
        }else{
            btnOk.setText("Cadastrar");
        }*/

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
        int idProdutos = getIntent().getIntExtra("idProdutos", 0);
        if( idProdutos != 0) {
           produtos = ProdutosDAO.getProdutosById(this, idProdutos);

            etProduto.setText( produtos.getNome() );

        }
    }

    private void salvar(){
        if( etProduto.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        }else{

            if (acao.equals("novo")) {
                produtos = new Produtos();
            }

            produtos.setNome(String.valueOf(etProduto.getText().toString()));
            produtos.setQuantidade(Integer.valueOf(etQuantidade.getText().toString()));
            produtos.setPreco(Double.valueOf(etPreco.getText().toString()));
            produtos.precoTotal();

            if( acao.equals("editar")){
                ProdutosDAO.editar(produtos, this);
                etProduto.setText("");
                etQuantidade.setText("");
                etPreco.setText("");
                //finish();
            }else {
                ProdutosDAO.inserir(produtos, this);
                etProduto.setText("");
                etQuantidade.setText("");
                etPreco.setText("");
              //  etTotal.setText("");
            }
        }
    }

}

