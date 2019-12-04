package fr.eni.ecole.monagenda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

import fr.eni.ecole.monagenda.R;
import fr.eni.ecole.monagenda.bo.Personne;

public class PersonneAdapter extends ArrayAdapter<Personne> {

    private int resView;

    public PersonneAdapter(@NonNull Context context,
                           int resource,
                           @NonNull List<Personne> objects) {
        super(context, resource, objects);

        this.resView = resource;

    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(resView, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvNom = convertView.findViewById(R.id.tv_nom);
            viewHolder.tvPrenom = convertView.findViewById(R.id.tv_prenom);
            viewHolder.tvTelephone = convertView.findViewById(R.id.tv_telephone);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Personne p = getItem(position);
        viewHolder.tvNom.setText(p.getNom());
        viewHolder.tvPrenom.setText(p.getPrenom());
        viewHolder.tvTelephone.setText(p.getTelephone());

        return convertView;
    }

    static class ViewHolder{
        TextView tvNom;
        TextView tvPrenom;
        TextView tvTelephone;
    }
}
