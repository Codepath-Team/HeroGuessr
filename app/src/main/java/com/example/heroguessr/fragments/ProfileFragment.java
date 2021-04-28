package com.example.heroguessr.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.heroguessr.LoginActivity;
import com.example.heroguessr.R;
import com.example.heroguessr.models.User;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.parse.Parse.getApplicationContext;

public class ProfileFragment extends Fragment {

    private final String TAG = "ProfileFragment";
    private final String photoFileName = "photo.jpg";
    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private Button btnLogout;
    private Button btnChangePfp, btnCapture;
    private ImageView imProfilePicture;
    private File photoFile;
    ParseUser user = ParseUser.getCurrentUser();
    public final static int PICK_PHOTO_CODE = 1046;

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

        if (user.getParseFile("image")!=null) {
            ParseFile image = user.getParseFile("image");
            Glide.with(getContext()).load(image.getUrl()).into(imProfilePicture);
        }
        else {
            imProfilePicture.setImageResource(imageResource);
        }


        btnChangePfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_PHOTO_CODE);

            }
        });

        btnCapture = view.findViewById(R.id.btnCapture);
        btnCapture.setBackgroundColor(getResources().getColor(R.color.light_blue));
        btnCapture.setTextColor(getResources().getColor(R.color.black));
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchCamera(v);
            }
        });

    }
    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            if(Build.VERSION.SDK_INT > 27){
                ImageDecoder.Source source = ImageDecoder.createSource(getActivity().getApplicationContext().getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_CODE) {
            if (resultCode == RESULT_OK) {
                Uri photoUri = data.getData();
                Bitmap selectedImage = loadFromUri(photoUri);
                imProfilePicture.setImageBitmap(selectedImage);
                File file = new File(photoUri.getPath());
                user.put("image", new ParseFile(file));
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e!=null) {
                            Log.e(TAG, "Error while saving", e);
                            Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                        }
                        Log.i(TAG, "Image save was successful!");
                        Toast.makeText(getContext(), "Image saved successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                imProfilePicture.setImageBitmap(takenImage);

                if (resultCode == RESULT_OK) {
                    user.put("image", new ParseFile(photoFile));
                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e!=null) {
                                Log.e(TAG, "Error while saving", e);
                                Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                            }

                            Log.i(TAG, "Image save was successful!");
                            Toast.makeText(getContext(), "Image saved successfully!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "Failed to create directory.");
        }

        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    private void onLaunchCamera(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = getPhotoFileUri(photoFileName);
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider.heroguessr", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }


}