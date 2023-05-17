package com.example.emprendetec;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class register_Image extends AppCompatActivity {

    ImageView perfil, portada, sig;
    private static final int IMAGEN_CODIGO_PERFIL = 1;
    private static final int IMAGEN_CODIGO_PORTADA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_image);

        perfil = findViewById(R.id.btn_setImage_perfil);
        portada = findViewById(R.id.btn_setImage_portada);
        sig = findViewById(R.id.btn_reg_img_sig);

        sig.setOnClickListener(view -> {
            animation(sig);
        });
    }

    public void seleccionarImagenPerfil(View view){
        animation(perfil);
        Intent img = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(img, IMAGEN_CODIGO_PERFIL);
    }

    public void seleccionarImagenPortada(View view){
        animation(portada);
        Intent img = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(img, IMAGEN_CODIGO_PORTADA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null){
            Uri imgS = data.getData();

            switch (requestCode) {
                case IMAGEN_CODIGO_PERFIL:
                    Glide.with(this)
                            .load(imgS)
                            .into(perfil);
                    break;

                case IMAGEN_CODIGO_PORTADA:
                    Glide.with(this)
                            .load(imgS)
                            .into(portada);
                    break;
            }
        }
    }

    private void animation(final ImageView imv) {
        Animation anim = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setDuration(100);
        anim.setFillAfter(true);

        AnimationSet anims = new AnimationSet(true);
        anims.addAnimation(anim);

        anims.setInterpolator(new DecelerateInterpolator());
        anims.setRepeatCount(1);
        anims.setRepeatMode(Animation.REVERSE);

        anims.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imv.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imv.startAnimation(anims);
    }
}