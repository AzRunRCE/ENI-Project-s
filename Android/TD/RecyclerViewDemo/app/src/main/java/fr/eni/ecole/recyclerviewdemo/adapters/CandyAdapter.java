package fr.eni.ecole.recyclerviewdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.ecole.recyclerviewdemo.R;
import fr.eni.ecole.recyclerviewdemo.bo.Candy;

//Implementation de androidx.recyclerview:recyclerview:1.1.0
public class CandyAdapter
        extends RecyclerView.Adapter<CandyAdapter.ViewHolder> {

    private List<Candy> data;
    private int resView;
    private CustomItemClickListener listener;

    public CandyAdapter(Context context,
                        int resource,
                        List<Candy> candies,
                        CustomItemClickListener listener){
        this.data = candies;
        this.resView = resource;
        this.listener = listener;
    }



    @NonNull
    @Override
    public CandyAdapter.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext())
                .inflate(resView, parent, false);

        final ViewHolder viewHolder = new ViewHolder(mView);

        //Click sur un item de la liste
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(v, data.get(viewHolder.getAdapterPosition()));
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder
            (@NonNull CandyAdapter.ViewHolder holder, int position) {
           Candy candy = data.get(position);
           holder.tvNom.setText(candy.getNom());
           holder.tvCouleur.setText(candy.getCouleur());
           holder.tvPrix.setText(String.valueOf(candy.getPrix()));
    }

    @Override
    public int getItemCount() {
        //renvoyer le nombre d'élément à manipuler
        return data.size();
    }

    /**
     * Class Interne qui contiendra les éléments de
     * la view d'un item
     */
    public static class ViewHolder
        extends RecyclerView.ViewHolder{
        public TextView tvNom;
        public TextView tvCouleur;
        public TextView tvPrix;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tv_nom);
            tvCouleur = itemView.findViewById(R.id.tv_couleur);
            tvPrix = itemView.findViewById(R.id.tv_prix);
        }
    }

    //Mon interface de gestion du click
    public interface CustomItemClickListener{
        public void onItemClick(View v, Candy candy);
    }
}
