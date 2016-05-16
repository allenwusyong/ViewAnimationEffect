package com.example.allenw.viewanimationeffect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView_test);
    }
public static final String TAG="MainActivity";
    @Override
    protected void onStart() {
        super.onStart();
        LinkedList<Integer> ints = new LinkedList<>();
        ints.add(1001); //0
        ints.add(1002);  //1
        ints.add(1003);
        ints.add(1004);
        ints.add(1005);
        ints.add(1003); //5
        ints.add(1002); //6a
        System.out.println("First index of 1003 is : " + ints.indexOf(1003));
        System.out.println("Last index of 1003 is : " + ints.lastIndexOf(1003));
        // same position
        System.out.println("First index of 1002 is : " + ints.indexOf(1002));
        System.out.println("Last index of 1002 is : " + ints.lastIndexOf(1002));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //数据之前删除ArrayList中重复的元素
        //use "LinkedHashSet"  删除重复的元素的同时，维护原有的插入顺序
        // 清空原来的ArrayList，我们可以使用ArrayList中的clear()method
        List<Integer> primes = new ArrayList<Integer>();

        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);  //重複的
        primes.add(7);
        primes.add(11);
        primes.add(5); //重複的

        System.out.println("list of total numbers : " + primes);

        Set<Integer> primeWithoutDuplicate=new LinkedHashSet<>(primes);
       //clear the ArrayList so that we can copy all elements from LinkedHashSet
        primes.clear();

        //再加入primeWithoutDuplicate    copying elements but without any duplicates
        primes.addAll(primeWithoutDuplicate);

        System.out.println("list of total numbers : " + primes);
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
//minor change
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
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        image.setLayoutParams(layoutParams);
        //load animation
        Animation scaleAnation = AnimationUtils.loadAnimation(this, R.anim.scale_effect);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_animator){

            Intent intentAnimator=new Intent(MainActivity.this,AnimatorTestActivity.class);
            startActivity(intentAnimator);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
