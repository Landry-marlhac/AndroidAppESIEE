package com.example.androidappesiee.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidappesiee.R;
import com.example.androidappesiee.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private ImageView pub;
    private int nbr_pub;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        pub = (ImageView) root.findViewById(R.id.constraint_pub);

        // Affichage random de la pub
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

        // S'occupe de détécter le clique sur l'image et de lancer l'instance du nav internet
        pub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (nbr_pub==0){
                    Uri uri = Uri.parse("https://www.esiee.fr/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Uri uri = Uri.parse("http://demineur.hugames.fr/#level-1");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        /*final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}