package com.saad.youssif.e3rfmodrsk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.Comment;
import com.saad.youssif.e3rfmodrsk.Model.Teacher;
import com.saad.youssif.e3rfmodrsk.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.TeachersHolder> implements Filterable {
    private final List<Teacher> teacherList;
    private final List<Teacher> teacherListFull;

    Context ctx;

    public TeachersAdapter(List<Teacher>teacherList,Context context)
    {
        this.teacherList=teacherList;
        this.ctx=context;
        teacherListFull=new ArrayList<>(teacherList);

    }

    @NonNull
    @Override
    public TeachersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_profile_layout,viewGroup,false);
        TeachersAdapter.TeachersHolder viewHolder = new TeachersAdapter.TeachersHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TeachersHolder teachersHolder, int i) {
        Teacher teacher=teacherList.get(i);
        Picasso.with(ctx).load(teacher.getTchPhoto()).into(teachersHolder.tch_profileImg);
        teachersHolder.tch_name.setText(teacher.getName());
        teachersHolder.tch_specification.setText(teacher.getTchSpecification());
        teachersHolder.tch_rate.setText(teacher.getTchRate());

        teachersHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = teachersHolder.getAdapterPosition(); //get position which is swipe
                Toast.makeText(ctx,"teacher id is : "+teacherList.get(position).getId(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }




    public static class TeachersHolder extends RecyclerView.ViewHolder
    {
        TextView tch_name,tch_rate,tch_specification;
        CircleImageView tch_profileImg;

        public TeachersHolder(View item) {
            super(item);
            tch_name=item.findViewById(R.id.tch_name);
            tch_specification=item.findViewById(R.id.tch_specification);
            tch_rate=item.findViewById(R.id.tch_rate);
            tch_profileImg=item.findViewById(R.id.tch_img);
        }

    }

    @Override
    public Filter getFilter() {
        return TeacherFilter;
    }
    private Filter TeacherFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Teacher> filteredList=new ArrayList<>();

            if(charSequence==null || charSequence.length()==0)
            {
                filteredList.addAll(teacherListFull);
            }
            else
            {
                String filterString=charSequence.toString().trim();
                for(Teacher item : teacherListFull)
                {
                    if(item.getName().contains(filterString))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            teacherList.clear();
            teacherList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

}
