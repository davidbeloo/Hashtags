package com.thepinkandroid.hashtags;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private FlowLayout mHashtagContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHashtags();
    }

    private void setHashtags()
    {
        final FlowLayout.LayoutParams hashTagLayoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        hashTagLayoutParams.setMargins(16,16,16,16);
        mHashtagContainer = (FlowLayout)findViewById(R.id.hashtagContainer);
        final ArrayList<String> hashtagList = getHashtagsFromAnySource();

        for (final String hashtag : hashtagList)
        {
            TextView textView = (TextView)getLayoutInflater().inflate(R.layout.hashtag_item, null);
            textView.setText("#" + hashtag);
            textView.setBackgroundColor(getRandomHashtagColor(this));
            textView.setLayoutParams(hashTagLayoutParams);
            textView.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    if(event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        applyTaDaAnimation(v);
                    }
                    return false;
                }
            });
            textView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // Do your action
                    Snackbar.make(v, "#" + hashtag + " Clicked" ,Snackbar.LENGTH_LONG).show();
                }
            });
            mHashtagContainer.addView(textView);
        }
    }

    private int getRandomHashtagColor(Context context)
    {
        int[] hashtagColors = context.getResources().getIntArray(R.array.hashtag_colors);
        int randomColor = hashtagColors[new Random().nextInt(hashtagColors.length)];
        return randomColor;
    }

    private ArrayList<String> getHashtagsFromAnySource()
    {
        ArrayList<String> hashtagList = new ArrayList<>();
        hashtagList.add("Cheetos");
        hashtagList.add("Beer");
        hashtagList.add("Milk");
        hashtagList.add("CocaCola");
        hashtagList.add("DairyProducts");
        hashtagList.add("Soda");
        hashtagList.add("Cake");
        hashtagList.add("Cheese");
        hashtagList.add("JohnnyWalkerBlack");
        hashtagList.add("Cigarettes");
        hashtagList.add("Cigars");
        hashtagList.add("Vodka");
        hashtagList.add("Doritos");
        hashtagList.add("Nuttela");
        return hashtagList;
    }

    private void applyTaDaAnimation(final View targetView)
    {
        YoYo.with(Techniques.Tada)
                .duration(600)
                .playOn(targetView);
    }
}
