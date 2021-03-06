package cuny.edu.com.resumebuilder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_layout);
        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
        addListenerOnButtonResumeGenerator();
        addListenerOnButtonScheduler();
        addListenerOnButtonProfile();
        addListenerOnButtonProfileUpload();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addListenerOnButtonResumeGenerator() {
        ImageView imageView = (ImageView) findViewById(R.id.image1);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
/*

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
*/
    }

    private void addListenerOnButtonScheduler() {
        ImageView imageView = (ImageView) findViewById(R.id.image3);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });
/*

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });
*/
    }
/*

    private void addListenerOnButtonProfile() {
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(getApplicationContext(), WebLocalViewActivity.class);
                startActivity(intent);

            }
        });
    }
*/


    private void addListenerOnButtonProfile() {
        ImageView imageView = (ImageView) findViewById(R.id.image4);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), WebLocalViewActivity.class);
                startActivity(intent);
            }
        });

       /* Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(getApplicationContext(), WebLocalViewActivity.class);
                startActivity(intent);

            }
        });*/
    }


    private void addListenerOnButtonProfileUpload() {
        ImageView imageView = (ImageView) findViewById(R.id.image2);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), CourseSearchActivity.class);
                startActivity(intent);
            }
        });

/*
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(getApplicationContext(), CourseSearchActivity.class);
                startActivity(intent);

            }
        });
        */
    }
}

