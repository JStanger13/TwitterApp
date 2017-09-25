package com.nyc.justinstanger.twitterapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Image;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by justinstanger on 9/21/17.
 */

public class HomePage extends Activity {

    public static final String BASE_URL = "http://www.purgomalum.com/service/containsprofanity";

    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Twitter.initialize(this);
        //what does this do?




        String username = getIntent().getStringExtra("username");
        TextView mUserName = (TextView) findViewById(R.id.username);
        mUserName.setText(username);



        //compose tweet
        final EditText writeTweet = (EditText) findViewById(R.id.write_tweet);

        Button sendTweet = (Button) findViewById(R.id.send_tweet);



//send tweet->
        sendTweet.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //Still need to send writeTweet to Bad Word Filter API before next step.
                String tweetText = writeTweet.getText().toString();

                if(TextUtils.isEmpty(tweetText)) {
                Toast.makeText(HomePage.this, "Your Tweet is Empty!", Toast.LENGTH_SHORT).show();
            }else{
                final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                        .getActiveSession();
                final Intent intent = new ComposerActivity.Builder(HomePage.this)
                        .session(session)
                        .text(writeTweet.getText().toString())
                        .hashtags("")
                        .createIntent();
                startActivity(intent);
                //Find way to send this to twitter without editing so user can't place banned
                // words in the final tweet.
            }
            }
        }));
    }
}










