package cc.overwall.overwall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cc.overwall.overwall.ui.Fragment0;
import cc.overwall.overwall.ui.Fragment1;
import cc.overwall.overwall.ui.Fragment2;
import cc.overwall.overwall.ui.Fragment3;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String titles[];

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles = titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return Fragment1.newInstance(position);
            case 1:
                return Fragment0.newInstance(position);
            case 2:
                return Fragment2.newInstance(position);
            case 3:
                return Fragment3.newInstance(position);

        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
