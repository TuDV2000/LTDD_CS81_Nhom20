package com.example.salebookapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BookFragment();
            case 1:
                return new CartFragment();
            case 2:
                if (Utils.accLogin != null)
//                    if(Utils.accLogin.getAccType().equals("Buyer"))
//                        return new
                return new AccountFragment();
            default:
                return new BookFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}