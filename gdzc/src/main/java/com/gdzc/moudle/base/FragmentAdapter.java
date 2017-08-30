package com.gdzc.moudle.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 4;
    private int mFlag;

    public FragmentAdapter(FragmentManager fm, int flag) {
        super(fm);
        this.mFlag = flag;
    }

    @Override
    public Fragment getItem(int position) {
        switch (mFlag) {
            case 0:
            case 1:
            case 2:
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "全部";
            case 1:
                return "青年";
            case 2:
                return "成人教育";
            case 3:
                return "管理";
            default:
                return "全部";
        }
    }
}