package br.com.teste.teste.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import br.com.teste.teste.R;

public class AdapterProdutos extends BaseAdapter {

    private List<Produtos> produtoList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProdutos (Context context, List<Produtos> listaProdutos){
        this.produtoList = listaProdutos;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Object getItem(int i) {
        return produtoList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return produtoList.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.activity_main, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaProduto);
            item.tvQuantidade = convertView.findViewById(R.id.tvListaQtd);
            item.tvPreco = convertView.findViewById(R.id.tvListaPreco);
            item.tvTotal = convertView.findViewById(R.id.tvListaTotal);
            item.layout = convertView.findViewById(R.id.lvProdutos);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Produtos produtos = produtoList.get(i);
        item.tvNome.setText(  produtos.getNome() );
        item.tvQuantidade.setText( String.valueOf(produtos.getQuantidade()));
        item.tvPreco.setText(String.valueOf(produtos.getPreco()));
        item.tvTotal.setText( String.valueOf(produtos.getTotal()));

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvQuantidade, tvPreco, tvTotal;
        ListView layout;
    }


}