package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;

public class HomeActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;

    private View viewEndAnimation;
    private ImageView viewAnimation;

    private int mCountBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);

        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.baseline_menu_black_18, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.baseline_shopping_cart_black_18, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_account_circle_24, R.color.color_tab_3);

        // Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);


        // Move Tab
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });

        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fakeBillDb();
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getViewAnimation() {
        return viewAnimation;
    }

    public void setCountProductInCart(int count){
        mCountBook = count;
        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 1);
    }

    public int getCountBook() {
        return mCountBook;
    }

    public void fakeBillDb(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String bookName = "Dat Ngu";
                String publicationDate = "abc";
                double price = 12.3;
                int republish = 1;
                int quantities = 1;
                String describe ="jsaijdoaisj";
                String image ="1";
                Book book = new Book(bookName, publicationDate, price, republish,quantities,describe,image);
                AppDatabase.getDatabase(getApplicationContext()).dao().bookInsert(book);

                int fkCusID = 1;
                double total =87654;
                String dateOfExport = "29/09/2000";
                Bill bill = new Bill(fkCusID,total,dateOfExport);
                AppDatabase.getDatabase(getApplicationContext()).dao().billInsert(bill);

                int idBill = AppDatabase.getDatabase(getApplicationContext()).dao().getBillbyCus(1).get(0).getBillID();
                int fkBookID =  AppDatabase.getDatabase(getApplicationContext()).dao().getAllBook().get(0).getBookID();
                double pricesale = 12.7;
                BillDetail billDetail = new BillDetail(idBill,fkBookID,price);
                AppDatabase.getDatabase(getApplicationContext()).dao().billDetailInsert(billDetail);

            }
        });
    }

}