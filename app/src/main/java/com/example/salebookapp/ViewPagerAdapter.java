package com.example.salebookapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.salebookapp.entities.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    private Map<String, Fragment> fms = new HashMap<>();


    @NonNull
    @Override
    public Fragment getItem(int position)  {
        switch (position){
            case 0:
//                if (!fms.containsKey("0"))
//                    fms.put("0", new BookFragment());
//
//                return fms.get("0");
                return new BookFragment();
            case 1:
                return new CartFragment();
            case 2:
                if (Utils.accLogin != null)
                    if(Utils.accLogin.getAccType().equals("Buyer"))
                        return new AccbuyerFragment();
                    else
                        return new AccsalerFragment();
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