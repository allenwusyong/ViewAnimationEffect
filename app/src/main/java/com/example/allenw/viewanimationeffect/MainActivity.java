package com.example.allenw.viewanimationeffect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView_test);
    }

    public void showAnimationEffect(View view){

        switch (view.getId()){

          case   R.id.button: // alpha
              doAlpha(imageView);
            break;


            case   R.id.button2: //scale
                doScale(imageView);
                break;
            case   R.id.button3: //rotate
                doRotate(imageView);
                break;
            case   R.id.button4: //translate

                doTranslate(imageView);
                break;
        }

    }

    private void doAlpha(ImageView imge) {
        imge.setImageResource(R.drawable.combanana);
        imge.clearAnimation();
        //load animation
        Animation alphaAniamton= AnimationUtils.loadAnimation(this,R.anim.alpha_effect);
        imge.startAnimation(alphaAniamton);
    }


    private  void doScale(ImageView image){
      image.setImageResource(R.drawable.eyeball_b);
        image.clearAnimation();
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                image.setLayoutParams(layoutParams);
        //load animation
        Animation scaleAnation= AnimationUtils.loadAnimation(this,R.anim.scale_effect);
        image.startAnimation(scaleAnation);

    }


    private  void doRotate(ImageView image){
        image.setImageResource(R.drawable.images_circle);
        image.clearAnimation();
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        image.setLayoutParams(layoutParams);
        //load animation
        Animation scaleAnation= AnimationUtils.loadAnimation(this,R.anim.rotate_effect);
        image.startAnimation(scaleAnation);

    }

    public static final String Tag="test";
    //move image from top-left corner to downright
    private  void doTranslate(ImageView image){
        image.setImageResource(R.drawable.arrow_down);
        //clear animation
        image.clearAnimation();
        //place image top left corner
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        image.setLayoutParams(layoutParams);
RelativeLayout layout= (RelativeLayout) findViewById(R.id.topContainer);


        int top=layout.getTop();
        int left=layout.getLeft();
        int right=layout.getRight();
        int bottom=layout.getBottom();

        TranslateAnimation translateAnimation=new TranslateAnimation(left,right,top,bottom);

        translateAnimation.setDuration(4000);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        image.startAnimation(translateAnimation);
    }


    Animation.AnimationListener animationListener=new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {
        Log.d(Tag,"start");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d(Tag, "end");
        //set image invisible  and intent another Activity
        imageView.setVisibility(View.INVISIBLE);
        Intent intent=new Intent(MainActivity.this,AnotherActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d(Tag,"repeat");
    }
};

}
