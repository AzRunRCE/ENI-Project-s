package com.eni.tp_correction;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eni.tp_correction.bo.Article;
import com.eni.tp_correction.bo.Contact;

import java.util.ArrayList;

/**
 * Created by quentin for TP_Correction on 2019-12-03.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> arrayListContact;
    private Article articleToSend;
    public ContactAdapter(ArrayList<Contact> arrayListContact,Article articleToSend) {
        super();
        this.arrayListContact = arrayListContact;
        this.articleToSend = articleToSend;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDisplayName;
        TextView textViewNumber;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDisplayName = itemView.findViewById(R.id.textViewDisplayName);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
        }
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list,
                parent,false);
        return new ContactViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final Contact contactToShow = arrayListContact.get(position);
        holder.textViewDisplayName.setText(contactToShow.getDisplayName());
        holder.textViewNumber.setText(contactToShow.getNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(
                        contactToShow.getNumber(),
                        "",
                        "Voici un cadeau qui me ferait plaisir :" + articleToSend.getTitre(),
                        null,null
                );
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayListContact.size();
    }
}
