package com.example.curdoperationassignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curdoperationassignment.PopActivity;
import com.example.curdoperationassignment.R;
import com.example.curdoperationassignment.db.entity.UserDetails;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mCtx;
    private List<UserDetails> userDetailsList;

    public UserAdapter(Context mCtx, List<UserDetails> taskList) {
        this.mCtx = mCtx;
        this.userDetailsList = taskList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserDetails userDetails = userDetailsList.get(position);
        holder.u_name.setText(userDetails.getFullName());
        holder.u_email.setText(userDetails.getEmail());
        holder.u_address.setText(userDetails.getAddress());
        holder.u_MobileNo.setText(userDetails.getMobileNo());
        holder.u_phoneNo.setText(userDetails.getPhoneNo());
        holder.u_zipCode.setText(userDetails.getZipCode());
        holder.u_country.setText(userDetails.getCounty());
        holder.u_state.setText(userDetails.getState());
        holder.u_city.setText(userDetails.getCity());
    }

    @Override
    public int getItemCount() {
        return userDetailsList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView u_name, u_address, u_city, u_email, u_state, u_country, u_zipCode, u_phoneNo, u_MobileNo;

        public UserViewHolder(View itemView) {
            super(itemView);

            u_name = itemView.findViewById(R.id.textViewName);
            u_address = itemView.findViewById(R.id.textViewAddress);
            u_city = itemView.findViewById(R.id.textViewCity);
            u_email = itemView.findViewById(R.id.textViewEmail);
            u_state = itemView.findViewById(R.id.textViewState);
            u_country = itemView.findViewById(R.id.textViewCountry);
            u_zipCode = itemView.findViewById(R.id.textViewZipCode);
            u_phoneNo = itemView.findViewById(R.id.textViewPhoneNo);
            u_MobileNo = itemView.findViewById(R.id.textViewMobileNo);

            itemView.setOnClickListener(this);

        }

        @SuppressLint("NewApi")
        @Override
        public void onClick(View view) {
            UserDetails userDetails = userDetailsList.get(getAdapterPosition());
            Button update, delete;
            Intent intent = new Intent(mCtx, PopActivity.class);
            intent.putExtra("task", userDetails);
            mCtx.startActivity(intent);
        }
    }

}

