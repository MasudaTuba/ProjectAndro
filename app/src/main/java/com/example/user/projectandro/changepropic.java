package com.example.user.projectandro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

public class changepropic extends AppCompatActivity {

    ImageView propic;
    Button edit;
    private StorageReference mStorageRef;
    private String imageDownloadUrl;
    private Uri uri ;
    private static final int IMAGE_READ_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepropic);

        mStorageRef = FirebaseStorage.getInstance().getReference("images/"); ///////////////////////////

        propic= findViewById(R.id.change1);
        Intent intent=getIntent();
        int resID = intent.getExtras().getInt("resID");
        propic.setImageResource(resID);

        edit=(Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                openGallery();

                }
                });



    }



    void openGallery(){
        //Toast.makeText(changepropic.this,"into gallery",Toast.LENGTH_SHORT).show();
        Intent pickImage = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        pickImage.addCategory(Intent.CATEGORY_OPENABLE);
        pickImage.setType("image/*");
        startActivityForResult(pickImage,IMAGE_READ_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        /*if (requestCode == IMAGE_READ_REQUEST_CODE && resultCode == Activity.RESULT_OK
                && resultData!=null && resultData.toString()!=null)
        {

                try{
                    final Uri imageUri = resultData.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                    propic.setImageBitmap(bitmap);

                }
                catch (Exception e){
                    e.printStackTrace();

                }
        }*/
        if(requestCode==IMAGE_READ_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            if(resultData !=null){
                uri=resultData.getData();
                Toast.makeText(changepropic.this, (CharSequence) uri,Toast.LENGTH_SHORT).show();
                propic.setImageURI(null);
                propic.setImageURI(uri);
            }
        }
    }
}
