package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import NonActivityClasses.Food;

import static com.example.afinal.MealActivity.TYPE;
import static com.example.afinal.SearchActivity.CALORIES;
import static com.example.afinal.SearchActivity.TITLE;

public class ListViewAdapter extends BaseAdapter{

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;

    //constructor
    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView mTitleTv, mDescTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);
            holder.mIconIv = view.findViewById(R.id.mainIcon);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        final String title = modellist.get(postition).getTitle();
        final String description = modellist.get(postition).getDesc();
        final String type = modellist.get(postition).getType();

        //set the results into textviews
        holder.mTitleTv.setText(title);
        //holder.mDescTv.setText(modellist.get(postition).getDesc());

        holder.mDescTv.setText("");
        //set the result in imageview
        holder.mIconIv.setImageResource(modellist.get(postition).getIcon());

        //listview item clicks
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                Log.d("", "onItemClick: "+title+"\n"+description);
                Intent nextActivity = new Intent( mContext, FoodCaloriesActivity.class);
                nextActivity.putExtra(TITLE, title);
                nextActivity.putExtra(CALORIES,description);
                nextActivity.putExtra(TYPE,type);
                mContext.startActivity(nextActivity);
                /*if (modellist.get(postition).getTitle().equals("Black Tea")){
                    //start NewActivity with title for actionbar and text for textview
                    //   Intent intent = new Intent(mContext, NewActivity.class);
                    // intent.putExtra("actionBarTitle", "Black Tea");
                    // intent.putExtra("contentTv", "This is Battery detail...");
                    Intent intent = new Intent(mContext, FoodCaloriesActivity.class);
                    mContext.startActivity(intent);

                }*/


            }
        });


        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
