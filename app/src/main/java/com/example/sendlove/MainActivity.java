package com.example.sendlove;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv;
    ImageView iv;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    RelativeLayout rl;
    RelativeLayout.LayoutParams mParams;
    int height1;
    int height2;
    int height3;
    int height4;
    int height5;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        rl = findViewById(R.id.rl);
        iv = findViewById(R.id.iv);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv.setOnClickListener(this);
        height1 = getWindowManager().getDefaultDisplay().getHeight() / 24 * 11;
        height2 = getWindowManager().getDefaultDisplay().getHeight() / 2;
        height3 = height2;
        height4 = getWindowManager().getDefaultDisplay().getHeight() / 24 * 13;
        height5 = getWindowManager().getDefaultDisplay().getHeight() / 24 * 14;
    }

    @Override
    public void onClick(View v) {
        mParams = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        x = (int) iv.getX();
        y = (int) iv.getY();
        getAnim(iv1, x - 100, y - 150, x, height1,1000);
        getAnim(iv2, x - 100, y - 150, x - 10, height2,1000);
        getAnim(iv3, x + 100, y - 150, x + 10, height3,1100);
        getAnim(iv4, x - 100, y - 150, x - 20, height4,1100);
        getAnim(iv5, x + 100, y - 150, x + 10, height5,1100);
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ImageView view = new ImageView(MainActivity.this);
//                        view.setLayoutParams(mParams);
//                        view.setPadding(0,0,20,20);
//                        view.setImageResource(R.drawable.o_green);
//                        rl.addView(view);
//                        getAnim(view);
//                    }
//                });
//            }
//        };
//        timer.schedule(task,500);
    }

    private void getAnim(final ImageView iv, int x1, int y1, int x2, int y2, int duration) {
        iv.setVisibility(View.VISIBLE);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0f);
        Path path = new Path();
        path.moveTo(x, y);
        path.quadTo(x1, y1, x2, y2);
        ObjectAnimator quad = ObjectAnimator.ofFloat(iv, "x", "y", path);
        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(alpha, quad);
        animator.setDuration(duration);
        animator.setTarget(iv);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //rl.removeView(iv);
                iv.setVisibility(View.GONE);
            }
        });
    }
}
