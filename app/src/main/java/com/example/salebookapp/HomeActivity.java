package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
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

        Utils.context = this;

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
                        .dao().getAccount("admin").size() == 0) {
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().customerInsert(new Customer("Admin", "0123456789"
                            , "371 Nguyễn kiệm, Q.Gò Vấp, TPHCM"));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().accountInsert(new Account("admin", "admin", "Saler"));
                }

                if (AppDatabase.getDatabase(getApplicationContext()).dao().getAllBook().size() == 0) {
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("Vượt qua tất cả", "20-02-2002",
                            100000.0, 1, 10, "Xem sách này để được khai sáng. Giúp ta vượt qua môn này !^O^!",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book1).toString()));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("English for Life", "20-02-2002",
                            120000.0, 1, 10, "English good for you !!!",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book2).toString()));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("Tâm Lý Học Thành Công", "20-02-2002",
                            135000.0, 1, 10, "Nhà tâm lý học nổi tiếng Carol S. Dweck sau nhiều thập niên nghiên cứu về thành công đã khám phá ra một ý tưởng thực sự mang tính đột phá – sức mạnh tư duy của chúng ta. Cuốn sách sẽ cho bạn thấy không chỉ khả năng và tài trí mới mang lại thành công cho chúng ta, mà phần lớn do cách tiếp cận mục tiêu bằng lối tư duy nào. Việc tán dương trí thông minh và khả năng của con bạn không hề nuôi dưỡng lòng tự trọng và dẫn đến thành tựu, mà thậm chí còn phương hại đến thành công sau này. Với tư duy đứng đắn, chúng ta có thể tạo động lực cho con cái và giúp chúng phát triển trong trường học, cũng như đạt được mục tiêu của bản thân trong cuộc sống và sự nghiệp. Dweck đã giúp tất cả các bậc cha mẹ, giáo viên, CEO và vận động viên thấy một ý tưởng đơn giản về não bộ có thể tạo ra tình yêu học tập và sự kiên trì – cơ sở cho những thành tựu vĩ đại ở mọi lĩnh vực.",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book3).toString()));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("Phi Lý Trí", "20-02-2002",
                            115000.0, 1, 10, "Trong hàng loạt các thực nghiệm nhằm làm sáng tỏ vấn đề, Dan Ariely, đã phản bác lại quan điểm chung cho rằng về cơ bản con người luôn hành động dựa trên lý trí. Bằng sự kết hợp giữa nghiên cứu chuyên sâu với những trải nghiệm thực tế, Ariely đã cho chúng ta một câu trả lời bất ngờ: chúng ta đôi khi phi lý trí hơn chúng ta tưởng. Thậm chí là thường xuyên phi lý trí và phi lý trí một cách có hệ thống. Ariely khám phá ra rằng chúng ta không chỉ phạm các lỗi đơn giản hàng ngày, mà còn lặp lại chúng. Chúng ta không hiểu được tác động của xúc cảm đối với những gì mình muốn và thường đánh giá quá cao những gì mình có. Nhưng những hành vi sai lầm này không hề ngẫu nhiên hay vô nghĩa. Chúng có thể được dự đoán.",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book4).toString()));
                    AppDatabase.getDatabase(getApplicationContext())
                            .dao().bookInsert(new Book("Tư Duy Nhanh Và Chậm", "20-02-2002",
                            150000.0, 1, 10, "Chúng ta thường tự cho rằng con người là sinh vật có lý trí mạnh mẽ, khi quyết định hay đánh giá vấn đề luôn kĩ lưỡng. Nhưng sự thật là, dù bạn có cẩn trọng tới mức nào, thì trong cuộc sống hàng ngày hay trong vấn đề liên quan đến kinh tế, bạn vẫn có những quyết định dựa trên cảm tính chủ quan của mình. Thinking fast and slow, cuốn sách nổi tiếng tổng hợp tất cả nghiên cứu được tiến hành qua nhiều thập kỷ của nhà tâm lý học từng đạt giải Nobel Kinh tế Daniel Kahneman sẽ cho bạn thấy những sư hợp lý và phi lý trong tư duy của chính bạn. Cuốn sách được đánh giá là “kiệt tác” trong việc thay đổi hành vi của con người, Thinking fast and slow đã dành được vô số giải thưởng danh giá, lọt vào Top 11 cuốn sách kinh doanh hấp dẫn nhất năm 2011. Alpha Books đã mua bản quyền và sẽ xuất bản cuốn sách trong Quý 1 năm nay. Thinking fast and slow dù là cuốn sách có tính hàn lâm cao nhưng vô cùng bổ ích với tất cả mọi người và đặc biệt rất dễ hiểu và vui nhộn.",
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book5).toString()));
                }
                bookList = AppDatabase.getDatabase(getApplicationContext()).dao().getAllBook();
            }

        });
    }


}