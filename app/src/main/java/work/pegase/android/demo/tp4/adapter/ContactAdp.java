/*
package work.pegase.android.demo.tp4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import work.pegase.android.demo.R;
import work.pegase.android.demo.tp4.model.Contact;


*/
/**
 * Created by Silien on 16/03/16.
 *//*

public class ContactAdp extends ArrayAdapter<Contact> {

    class Holder {
        TextView badge ;
        TextView name  ;
        TextView pseudo ;
    }

    private Context mCtx;

    public ContactAdp(Context context, int resource) {
        super(context, resource);
        mCtx = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            */
/* Création *//*

            convertView = LayoutInflater.from(mCtx)
                    .inflate(R.layout.contact_item,parent,false);
            Holder holder = new Holder();
            holder.badge  = (TextView) convertView.findViewById(R.id.badge);
            holder.name  = (TextView) convertView.findViewById(R.id.name);
            holder.pseudo  = (TextView) convertView.findViewById(R.id.pseudo);
            convertView.setTag(holder);
        }

        */
/* Récupérer le Holder existant *//*

        Holder holder = (Holder) convertView.getTag();

        */
/* Data *//*

        Contact c = getItem(position);

        */
/* Initialisation *//*

        holder.badge.setText("");
        holder.name.setText("");
        holder.pseudo.setText("");

        if (c.name != null){
            holder.badge.setText(c.name.charAt(0) + "");
            holder.name.setText(c.name);
        }
        if (c.pseudo != null) {
            holder.pseudo.setText(c.pseudo);
        }

        return convertView;
    }
}
*/
