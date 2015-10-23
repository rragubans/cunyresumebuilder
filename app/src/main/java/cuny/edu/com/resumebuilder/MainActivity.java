package cuny.edu.com.resumebuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import cuny.edu.com.resumebuilder.pdf.ResumeGenerator;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Personal Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Education Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Experience Details"));
     //   tabLayout.addTab(tabLayout.newTab().setText("Resume"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                addListenerOnButton();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.buttonGenerate);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.i("Onclick", "Generate Resume");
                    try {
                        ResumeGenerator generator = new ResumeGenerator();
                        ResumeInformation resumeInfo = new ResumeInformation();

                        resumeInfo.setAddress1("87-32 168st");
                        resumeInfo.setCity("Jamaica");
                        resumeInfo.setState("NY");
                        resumeInfo.setName("Ram Ragubans");
                        generator.generateResume(resumeInfo, view.getContext());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            });
        }
    }

    private void viewPdf(File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


    public void sendFeedback(View view) {

        System.out.println("sendFeedback");

    }
}

