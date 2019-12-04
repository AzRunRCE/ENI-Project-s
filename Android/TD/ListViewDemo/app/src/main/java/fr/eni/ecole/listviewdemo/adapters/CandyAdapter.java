package fr.eni.ecole.listviewdemo.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.eni.ecole.listviewdemo.R;
import fr.eni.ecole.listviewdemo.bo.Candy;

public class CandyAdapter extends ArrayAdapter<Candy> {

    private List<Candy> candys;
    private int viewRes;
    private Resources res;

    public CandyAdapter(@NonNull Context context,
                        int resource,
                        @NonNull List<Candy> objects) {
        super(context, resource, objects);

        this.candys = objects;
        this.viewRes = resource;
        this.res = context.getResources();

    }

    //Permet de renseigner chaque élément de la listview
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext()
                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(viewRes, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvCouleur = convertView.findViewById(R.id.tv_couleur);
            viewHolder.tvNom = convertView.findViewById(R.id.tv_nom);
            viewHolder.tvPrix = convertView.findViewById(R.id.tv_prix);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Candy candy = getItem(position);

        viewHolder.tvPrix.setText(String.valueOf(candy.getPrix()));
        viewHolder.tvNom.setText(candy.getNom());
        viewHolder.tvCouleur.setText(candy.getCouleur());

        return convertView;
    }

    static class ViewHolder{
        TextView tvNom;
        TextView tvCouleur;
        TextView tvPrix;
    }
}
