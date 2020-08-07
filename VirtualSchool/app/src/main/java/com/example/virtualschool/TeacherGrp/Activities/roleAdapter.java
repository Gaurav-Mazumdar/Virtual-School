package com.example.virtualschool.TeacherGrp.Activities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualschool.R;
import com.example.virtualschool.TeacherGrp.Model.RoleResponse;
import com.example.virtualschool.TeacherGrp.Model.TeacherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class roleAdapter extends RecyclerView.Adapter<roleAdapter.RoleAdapterVH> {

    private List<RoleResponse> roleResponseList;
    private Context ctx;
    private ClickedPeriodItem clickedPeriodItem;

    public roleAdapter(ClickedPeriodItem clickedPeriodItem) {
        this.clickedPeriodItem = clickedPeriodItem;
    }

    public void setRole(List<RoleResponse> roleResponseList){
        this.roleResponseList = roleResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoleAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        return new roleAdapter.RoleAdapterVH(LayoutInflater.from(ctx).inflate(R.layout.role_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoleAdapterVH holder, int position) {
        RoleResponse roleResponse = roleResponseList.get(position);
        String tClass = roleResponse.getAssignedClass();
        String tSub = roleResponse.getAssignedSub();
        String tPeriod = roleResponse.getPeriodNum();
        String periodDay = roleResponse.getDayVal();
        holder.TClass.setText(tClass);
        holder.TSub.setText(tSub);
        holder.TPeriod.setText(tPeriod);
        holder.TPeriodDay.setText(periodDay);

        holder.imgMoreR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedPeriodItem.clickedPeriod(roleResponse);
            }
        });

        if(getDate().matches(periodDay)){
            holder.TPeriodDay.setTextColor(Color.parseColor("#D81B60"));
            holder.TSub.setTextColor(Color.parseColor("#D81B60"));
            holder.TPeriod.setTextColor(Color.parseColor("#D81B60"));
            holder.TClass.setTextColor(Color.parseColor("#D81B60"));
        }
    }

    public interface ClickedPeriodItem{
        public void clickedPeriod(RoleResponse roleResponse);
    }

    @Override
    public int getItemCount() {
        return roleResponseList.size();
    }

    public String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("EEEE");
        Date date = new Date();
        return df.format(date);
    }

    public class RoleAdapterVH extends RecyclerView.ViewHolder {

        TextView TClass, TSub, TPeriod, TPeriodDay;
        ImageView imgMoreR;
        public RoleAdapterVH(@NonNull View itemView) {
            super(itemView);

            TClass = itemView.findViewById(R.id.teacherClass);
            TSub = itemView.findViewById(R.id.teacherSub);
            TPeriod = itemView.findViewById(R.id.teacherPeriod);
            TPeriodDay = itemView.findViewById(R.id.weekDay);
            imgMoreR = itemView.findViewById(R.id.imgMoreR);
        }
    }
}
