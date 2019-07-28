package br.edu.ifsc.ddm.projmydot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RegistroPontoListAdapter extends ArrayAdapter<RegistroPonto> {

    private Context mContext;
    private int mResource;


    public RegistroPontoListAdapter(Context context, int resource, ArrayList<RegistroPonto> objects) {
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
        TextView lblData = (TextView)convertView.findViewById(R.id.lblData);
        TextView lblHoraEntrada = (TextView)convertView.findViewById(R.id.lblHoraEntrada);
        TextView lblHoraSaidaAlmoco = (TextView)convertView.findViewById(R.id.lblHoraSaidaAlmoco);
        TextView lblHoraRetornoAlmoco = (TextView)convertView.findViewById(R.id.lblHoraRetornoAlmoco);
        TextView lblHoraSaida = (TextView)convertView.findViewById(R.id.lblHoraSaida);

        RegistroPonto ponto = getItem(position);

        lblData.setText(ponto.getDataAtualFormatada());
        lblHoraEntrada.setText(ponto.getHoraEntradaFormatada());
        lblHoraSaidaAlmoco.setText(ponto.horaSaidaAlmocoFormatada);
        lblHoraRetornoAlmoco.setText(ponto.horaRetornoAlmocoFormatada);
        lblHoraSaida.setText(ponto.horaSaidaFormatada);

        return convertView;
    }
}
