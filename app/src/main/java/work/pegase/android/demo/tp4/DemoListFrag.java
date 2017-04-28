//package work.pegase.android.demo.tp4;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.ContextMenu;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//import cz.msebera.android.httpclient.Header;
//
//import work.pegase.android.demo.DemoApplication;
//import work.pegase.android.demo.R;
//import work.pegase.android.demo.tp4.adapter.ContactAdp;
//import work.pegase.android.demo.tp4.model.Contact;
//import work.pegase.android.demo.tp4.model.Result;
//
//
///**
// * Created by Silien on 25/03/16.
// */
//public class DemoListFrag extends Fragment implements View.OnClickListener, OnContactSave, AdapterView.OnItemClickListener {
//
//    private ContactAdp adp ;
//    private ListView list;
//    private Button resetFromServer;
//    private Button add;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        adp = new ContactAdp(DemoApplication.getContext(),0);
//        Contact c  = new Contact();
//        c.name = "toto";
//        c.pseudo = "fff";
//        adp.add(c);
//
//    }
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.main_list_view, container, false);
//         /* Binding */
//        list = (ListView) view.findViewById(R.id.list);
//        add = (Button) view.findViewById(R.id.add);
//        resetFromServer = (Button) view.findViewById(R.id.resetFromServer);
//        list.setAdapter(adp);
//        list.setOnItemClickListener(this);
//        list.setOnCreateContextMenuListener(this);
//        add.setOnClickListener(this);
//        resetFromServer.setOnClickListener(this);
//
//        return view;
//    }
//
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        menu.setHeaderTitle(adp.getItem(info.position).name);
//        getActivity().getMenuInflater().inflate(R.menu.menu_list_context, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        Log.d("DemoListAct", "Item : " + adp.getItem(info.position).pseudo);
//        switch (item.getItemId()){
//            case R.id.deleteContact:
//                adp.remove(adp.getItem(info.position));
//                break;
//            case R.id.modifyContact:
//                Gson gson = new Gson();
//                ContactEditorFrag frag = new ContactEditorFrag();
//                frag.setArguments(new Bundle());
//                Contact contact = adp.getItem(info.position);
//                contact.position = info.position;
//                frag.getArguments().putString(Contact.class.getName(),gson.toJson(contact));
//                frag.setOnContactSave(this);
//                frag.show(getFragmentManager(),"DIALOGO");
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }
//
//    public  void updateContactFromServer(){
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams parameters = new RequestParams();
//
//        parameters.add("security","public");
//        parameters.add("module","tuto");
//        parameters.add("action","get_contact");
//        parameters.add("method", "get_contact");
//        parameters.add("mode", "rest");
//
//        client.post("http://web.pegase.work/index.php", parameters, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                /* Refresh the list view with new Data */
//                try {
//                    Log.e("HTTPRequester", "Success !");
//                    String decoded = new String(responseBody, "UTF-8");
//                    Gson gson = new Gson();
//                    Result result = gson.fromJson(decoded, Result.class);
//                    Log.e("HTTPRequester", "DATA --> " + result.data.size());
//                    if (result.success) {
//                        adp.clear();
//                        adp.addAll(result.data);
//                    }
//                } catch (UnsupportedEncodingException e) {
//                    Log.e("HTTPRequester", "error !", e);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                /* Refresh the list view with new Data */
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.add:
//                ContactEditorFrag frag = new ContactEditorFrag();
//                frag.setOnContactSave(this);
//                frag.show(getFragmentManager(),"ContactEditorFrag");
//
//                break;
//            case R.id.resetFromServer:
//                updateContactFromServer();
//                break;
//        }
//    }
//
//    @Override
//    public void onContactSave(Contact contact) {
//        if (contact.position == -1){
//            adp.add(contact);
//        }  else {
//            /* Modification */
//            adp.getItem(contact.position).name = contact.name;
//            adp.getItem(contact.position).pseudo = contact.pseudo;
//            adp.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Contact c = adp.getItem(position);
//        Toast.makeText(getActivity(),"Contact : " + c.name,Toast.LENGTH_SHORT).show();
//    }
//}
