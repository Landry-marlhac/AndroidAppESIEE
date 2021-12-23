package com.example.androidappesiee.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidappesiee.CheckitToday;
import com.example.androidappesiee.DataBaseHelper;
import com.example.androidappesiee.NewTask;
import com.example.androidappesiee.R;
import com.example.androidappesiee.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImageView pub;
    private int nbr_pub;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        Button addbouton =  root.findViewById(R.id.addbutton);
        addbouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newtaskIntent = new Intent(getActivity(), NewTask.class);
                startActivity(newtaskIntent);
            }
        });

        Button readbouton =  root.findViewById(R.id.readbutton);
        readbouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkitIntent = new Intent(getActivity(), CheckitToday.class);
                startActivity(checkitIntent);
            }
        });

        Button deleteAllButton = root.findViewById(R.id.tran);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


        mContext = getContext();

//Affichage des pub, incrémentation du nombre de clic sur la pub, toast a 5 pub cliqué DEBUT
        pref = getContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        pub = (ImageView) root.findViewById(R.id.constraint_pub);

        // Affichage une des pub de manière aléatoire
        double rand = Math.random();
        if (rand > 0.5) {
            pub.setImageResource(R.drawable.pub_1);
            nbr_pub=0;

            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.enter_from_bottom);
            pub.startAnimation(animation);
        } else {
            pub.setImageResource(R.drawable.pub_2);
            nbr_pub=1;

            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.enter_from_bottom);
            pub.startAnimation(animation);
        }
        // S'occupe d'afficher et détécter le clique sur l'image (pub) et de lancer l'instance du nav internet
        // Permet d'afficher un toast quand on a cliquer 5 fois sur la pub
        pub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                int times = pref.getInt("key_name", 0);
                editor.putInt("key_name", times+1);
                editor.apply();

                //Toast.makeText(getContext(), "Vous avez vu :", Toast.LENGTH_LONG).show();
                if (times % 5 == 0) {
                    Toast.makeText(getContext(), "Felicitation voici de l'argent !", Toast.LENGTH_LONG).show();
                }else{
                    //affichage nombre de pub visioné
                    //Toast.makeText(getContext(), "Vous avez vu :" + times + "pub", Toast.LENGTH_SHORT).show();
                }

                if (nbr_pub==0){
                    Uri uri = Uri.parse("https://www.esiee.fr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(nbr_pub==1){
                    Uri uri = Uri.parse("http://demineur.hugames.fr/#level-1");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
//Affichage des pub, incrémentation du nombre de clic sur la pub, toast a 5 pub cliqué FIN

        return root;
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Delete All tasks ?");
        builder.setMessage("Are you sure you want to delete all tasks ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper db = new DataBaseHelper(mContext);
                db.deleteAllData();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}