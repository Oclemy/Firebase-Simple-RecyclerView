package com.tutorials.hp.firebasesimplerecyclerview.m_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tutorials.hp.firebasesimplerecyclerview.R;


/**
 * Created by Oclemy on 6/21/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyViewHolder extends RecyclerView.ViewHolder  {

    TextView nameTxt;

    public MyViewHolder(View itemView) {
        super(itemView);

        nameTxt=(TextView) itemView.findViewById(R.id.nameTxt);
    }
}
