package com.saad.youssif.e3rfmodrsk.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.saad.youssif.e3rfmodrsk.Fragments.CommentsFragment;
import com.saad.youssif.e3rfmodrsk.Fragments.DetailsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {



    private String title[] = {"التفاصيل", "التعليقات"};
    final int PAGE_COUNT = 2;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }


    @Override
    public Fragment getItem(int i) {
        if(i==0)
        {
            Fragment fragment1=new DetailsFragment();
            return fragment1;
        }
        else
        {
            Fragment fragment2=new CommentsFragment();
            return fragment2;
        }
    }



    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return title[position];
    }
}