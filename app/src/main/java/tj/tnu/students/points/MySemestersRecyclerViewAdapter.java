package tj.tnu.students.points;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tj.tnu.students.R;
import tj.tnu.students.data.model.Courses;
import tj.tnu.students.data.model.Semesters;

public class MySemestersRecyclerViewAdapter extends RecyclerView.Adapter<MySemestersRecyclerViewAdapter.ViewHolder> {

    private final List<Semesters> mValues;
    private final LessonFragment.OnListFragmentInteractionListener mListener;
    Activity activity;

    public MySemestersRecyclerViewAdapter(Activity activity, List<Semesters> items, LessonFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.activity = activity;
    }

    @Override
    public MySemestersRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_semester, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MySemestersRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.semesterName.setText(holder.mItem.getSemesterName());
        System.out.println(holder.mItem.getSemesterName());

        if (holder.mItem.isActive()){
            //holder.semesterName.setTextColor(activity.getResources().getColor(R.color.green));
            holder.mView.setAlpha((float) 0.8);
            holder.mView.setBackgroundColor(activity.getResources().getColor(R.color.selected_semester));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelectSemester(holder.mItem.getSemesterId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Semesters mItem;
        public TextView semesterName;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            semesterName = view.findViewById(R.id.semester_name);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }

}
