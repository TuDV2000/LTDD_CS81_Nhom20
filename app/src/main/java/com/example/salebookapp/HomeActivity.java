package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;

    private View viewEndAnimation;
    private ImageView viewAnimation;

    private int mCountBook;
    public static List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);

        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        //Load book list
        setBookList();

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

    public void setBookList() {
        bookList = new ArrayList<>();

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (AppDatabase.getDatabase(getApplicationContext())
                        .dao().getAccount("admin@gmail.com").size() == 0) {
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().customerInsert(new Customer("Admin", "0123456789"
                            , "371 Nguyễn kiệm, Q.Gò Vấp, TPHCM"));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().accountInsert(new Account("admin", "admin", "Saler"));
                }

                if (AppDatabase.getDatabase(getApplicationContext()).dao().getAllBook().size() == 0) {
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("Vượt qua tất cả", "20-02-2002",
                            100000.0, 1, 10, "Sách hay !!!",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book1).toString()));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("English for Life", "20-02-2002",
                            120000.0, 1, 10, "English good for you !!!",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book2).toString()));
                }
                bookList = AppDatabase.getDatabase(getApplicationContext()).dao().getAllBook();
            }
        });
    }
}