/*
package work.pegase.android.demo.tp4;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import work.pegase.android.demo.R;
import work.pegase.android.demo.tp4.model.Contact;


*/
/**
 * Created by Silien on 25/03/16.
 *//*

public class ContactEditorFrag extends DialogFragment implements View.OnClickListener {


    private EditText pseudo;
    private EditText name;
    private Contact contact;
    private Button save;
    private Button cancel;
    private OnContactSave listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.contact_editor , container,false);
         */
/* Binding *//*

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // Je ne veux pas de barre de titre
        pseudo = (EditText) view.findViewById(R.id.pseudo);
        name = (EditText) view.findViewById(R.id.name);
        if (contact != null){
            pseudo.setText(contact.pseudo);
            name.setText(contact.name);
        }
        save = (Button) view.findViewById(R.id.save);
        cancel = (Button) view.findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (getArguments() != null && getArguments().getString(Contact.class.getName()) != null){
            Gson gson = new Gson();
            contact = gson.fromJson(getArguments().getString(Contact.class.getName()),Contact.class);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                if (contact == null){
                    contact = new Contact();
                    contact.position = -1; // Position -1 means that is a creation
                }

                contact.name = name.getText().toString();
                contact.pseudo = pseudo.getText().toString();
                listener.onContactSave(contact);
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }



    }



    public void setOnContactSave(OnContactSave onContactSave) {
        listener = onContactSave;
    }
}
*/
