package com.parso.pruebaparsoandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parso.pruebaparsoandroid.Models.User;
import com.parso.pruebaparsoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luis.Matamoros on 22/03/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> lu)
    {
        this.userList = lu;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(this.context).inflate(R.layout.raw_users, viewGroup, false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewholder, int position)
    {
        final int pos = position;
        final User lu = userList.get(pos);

        Picasso.with(viewholder.itemView.getContext())
                .load(lu.getPicture().getLarge())
                .fit()
                .into(viewholder.ivPicture);
        String title = lu.getName().getTitle().substring(0,1).toUpperCase() + "" + lu.getName().getTitle().substring(1) + ". ";
        String fisrName = lu.getName().getFirst().substring(0, 1).toUpperCase() + lu.getName().getFirst().substring(1);
        String lastName = lu.getName().getLast().substring(0, 1).toUpperCase() + lu.getName().getLast().substring(1);

        viewholder.tvName.setText(title + fisrName + " " + lastName);
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


