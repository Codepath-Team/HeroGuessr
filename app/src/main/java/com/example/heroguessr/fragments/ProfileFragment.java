package com.example.heroguessr.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.heroguessr.LoginActivity;
import com.example.heroguessr.R;
import com.parse.ParseUser;

import java.io.File;

public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private Button btnChangePfp;
    private ImageView imProfilePicture;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setBackgroundColor(getResources().getColor(R.color.light_red));
        btnLogout.setTextColor(getResources().getColor(R.color.black));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                goLoginActivity();
            }
        });

        btnChangePfp = view.findViewById(R.id.btnChangePfp);
        imProfilePicture = view.findViewById(R.id.imProfilePicture);
        int imageResource = getResources().getIdentifier("@drawable/pfp", null, getContext().getPackageName());
        imProfilePicture.setImageResource(imageResource);

        btnChangePfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
           if(resultCode == Activity.RESULT_OK){
               Uri imageUri = data.getData();
               imProfilePicture.setImageURI(imageUri);
           }
        }
    }

    private void goLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }


}