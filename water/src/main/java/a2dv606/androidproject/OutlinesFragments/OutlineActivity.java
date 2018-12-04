package a2dv606.androidproject.OutlinesFragments;

import android.os.Bundle;
import android.widget.TextView;

import a2dv606.androidproject.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class OutlineActivity extends AppCompatActivity {
    private   TextView pageMun;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_outline);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pageMun = (TextView) findViewById(R.id.pageNum);
        // Instantiate a ViewPager and a PagerAdapter.
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                pageMun.setText(((position + 1) + "/" + pagerAdapter.getCount()) );

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        androidx.fragment.app.Fragment[] fragments = new androidx.fragment.app.Fragment[6];

        public MyPagerAdapter(androidx.fragment.app.FragmentManager fm) {
            super(fm);

            fragments[0] = adviceFragment.create(R.string.wake_up_review, R.drawable.wakeup);
            fragments[1] = adviceFragment.create( R.string.sleep_review, R.drawable.sleep);
            fragments[2] = adviceFragment.create( R.string.shower_review, R.drawable.shower);
            fragments[3] = adviceFragment.create( R.string.meal_review, R.drawable.eat);
            fragments[4] = adviceFragment.create( R.string.lose_weight, R.drawable.lose_weight);
            fragments[5] = adviceFragment.create( R.string.lack_water, R.drawable.pheart);





        }


        @Override
        public Fragment getItem(int position) {

            return fragments[position];

        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

