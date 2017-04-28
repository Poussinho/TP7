//package work.pegase.android.demo.tp4;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import work.pegase.android.demo.R;
//
//
//
//public class DemoListAct extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.demo_list_act);
//
//        /* Lancement du Fragment par programmation
//         *  --> Pour gérer l'orientation, il est nécessaire de vérifier si un Fragment
//         *  a déjà été instancier via findFragmentByTag()
//         */
//        DemoListFrag frag = (DemoListFrag) getSupportFragmentManager().findFragmentByTag("DemoListFrag");
//        if (frag == null) {
//            Log.e("Frag","Find existing frag !");
//            frag = new DemoListFrag();
//        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.frag,frag,"DemoListFrag").commit();
//    }
//
//}
