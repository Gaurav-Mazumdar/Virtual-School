package com.example.virtualschool.TeacherGrp.Activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualschool.R;
import com.example.virtualschool.TeacherGrp.Model.TeacherResponse;

import java.util.List;

public class teacherAdapter extends RecyclerView.Adapter<teacherAdapter.TeacherAdapterVH> {

    private List<TeacherResponse> teacherResponseList;
    private Context ctx;
    private ClickedItem clickedItem;

    public teacherAdapter(ClickedItem clickedItem){
        this.clickedItem = clickedItem;
    }

    public void setData(List<TeacherResponse> teacherResponseList){
        this.teacherResponseList = teacherResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public teacherAdapter.TeacherAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        return new teacherAdapter.TeacherAdapterVH(LayoutInflater.from(ctx).inflate(R.layout.teachers_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull teacherAdapter.TeacherAdapterVH holder, int position) {

        final TeacherResponse teacherResponse = teacherResponseList.get(position);
        String teacherName = teacherResponse.getName();
        String activeState = teacherResponse.getLive();
        String accountState = teacherResponse.getStatus();
        if(activeState.matches("1")){
            holder.rl.setVisibility(View.VISIBLE);
        }
        else {
            holder.rl.setVisibility(View.GONE);
        }

        if(!accountState.matches("1")){
            holder.imageMore.setVisibility(View.INVISIBLE);
        }
        holder.teacherName.setText(teacherName);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(teacherResponse);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedUser(TeacherResponse teacherResponse);
    }

    @Override
    public int getItemCount() {
        return teacherResponseList.size();
    }

    public class TeacherAdapterVH extends RecyclerView.ViewHolder {

        TextView teacherName;
        RelativeLayout rl;
        ImageView imageMore;

        public TeacherAdapterVH(@NonNull View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.teacherName);
            rl = itemView.findViewById(R.id.isActive);
            imageMore = itemView.findViewById(R.id.imgMore);
        }
    }
}
