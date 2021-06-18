package com.example.regulartab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // tra ve bien tham chieu toi fragment o vi tri position
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
        }
        return null;
    }

    // so luong fragment hien co
    @Override
    public int getCount() {
        return 3;
    }

    // noi dung hien thi tren fragment o vi tri position
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tab 1";
            case 1:
                return "Tab 2";
            case 2:
                return "Tab 3";
        }
        return null;
    }
}
