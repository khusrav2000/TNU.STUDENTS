package tj.tnu.students.points;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tj.tnu.students.R;
import tj.tnu.students.data.model.Courses;
import tj.tnu.students.points.LessonFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MylessonRecyclerViewAdapter extends RecyclerView.Adapter<MylessonRecyclerViewAdapter.ViewHolder> {

    private final List<Courses> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MylessonRecyclerViewAdapter(List<Courses> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.courseName.setText(mValues.get(position).getCourseName());
        holder.teacherName.setText(mValues.get(position).getTeacherName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView courseName;
        public final TextView teacherName;
        public Courses mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            courseName = view.findViewById(R.id.lesson_name);
            teacherName = view.findViewById(R.id.teacher_name);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + courseName.getText() + "'";
        }
    }
}
