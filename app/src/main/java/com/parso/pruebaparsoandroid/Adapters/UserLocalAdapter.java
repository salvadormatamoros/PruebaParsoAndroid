package com.parso.pruebaparsoandroid.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parso.pruebaparsoandroid.Models.User;
import com.parso.pruebaparsoandroid.Models.UserLocal;
import com.parso.pruebaparsoandroid.R;
import com.parso.pruebaparsoandroid.SQLite.ParsoSQLiteOpenHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luis.Matamoros on 22/03/2018.
 */

public class UserLocalAdapter extends RecyclerView.Adapter<UserLocalAdapter.ViewHolder>
{
    private List<UserLocal> userList;
    private Context context;

    public UserLocalAdapter(List<UserLocal> lu)
    {
        this.userList = lu;
    }

    @Override
    public UserLocalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(this.context).inflate(R.layout.raw_users, viewGroup, false);
        UserLocalAdapter.ViewHolder viewHolder = new UserLocalAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserLocalAdapter.ViewHolder viewholder, int position)
    {
        final int pos = position;
        final UserLocal lu = userList.get(pos);

        viewholder.ivPicture.setImageBitmap(lu.getPicture());
        viewholder.tvName.setText(lu.getName());
        viewholder.tvEmail.setText(lu.getEmail());
    }

    @Override
    public int getItemCount()
    {
        return this.userList.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder)
    {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivPicture;
        public TextView tvName;
        public TextView tvEmail;

        View itemView;
        ViewHolder(View itemView)
        {
            super(itemView);

            this.itemView = itemView;

            ivPicture = (ImageView) itemView.findViewById(R.id.user_picture);
            tvName = (TextView) itemView.findViewById(R.id.user_name);
            tvEmail = (TextView) itemView.findViewById(R.id.user_email);
        }
    }
}


