package br.edu.ifsc.ads.pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FrutaListAdapter extends ArrayAdapter<Fruta> {

    private Context mContext;
    private int mResource;


    public FrutaListAdapter(Context context, int resource, ArrayList<Fruta> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        // faz como o setcontentview
        convertView = inflater.inflate(mResource, parent, false);

        // Associacao as variaveis do metodo ao layout
        TextView lblCodigo = (TextView)convertView.findViewById(R.id.lblCodigo);
        TextView lblFruta = (TextView)convertView.findViewById(R.id.lblFruta);
        TextView lblPreco = (TextView)convertView.findViewById(R.id.lblPreco);
        TextView lblPrecoVenda = (TextView)convertView.findViewById(R.id.lblPrecoVenda);
        ImageView img = (ImageView)convertView.findViewById(R.id.imageView);

        Fruta fruta = getItem(position);

        Locale local = new Locale("pt","BR");
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(local));

        lblCodigo.setText(Integer.toString(fruta.getCodigo()));
        lblFruta.setText(fruta.getNome());
        lblPreco.setText("R$ "+df.format(fruta.getPreco()));
        lblPrecoVenda.setText("R$ "+df.format(fruta.getPreco_venda()));

        img.setImageResource(fruta.getImagem());

        return convertView;
    }
}