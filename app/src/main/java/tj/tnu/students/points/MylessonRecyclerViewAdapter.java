package tj.tnu.students.points;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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


        holder.pointsProgress.setProgress(Integer.valueOf((int) holder.mItem.getTotalPoints()));

        holder.totalPoints.setText(String.valueOf(holder.mItem.getTotalPoints()));




        holder.mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    holder.courseLayout.setAlpha((float) 0.4);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    holder.courseLayout.setAlpha((float) 1);
                    mListener.onListFragmentInteraction(holder.mItem);
                } else {
                    holder.courseLayout.setAlpha((float) 1);
                }



                return true;
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
        private ProgressBar pointsProgress;
        private TextView totalPoints;
        private RelativeLayout courseLayout;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            courseName = view.findViewById(R.id.lesson_name);
            teacherName = view.findViewById(R.id.teacher_name);
            pointsProgress = view.findViewById(R.id.progress_points);
            totalPoints = view.findViewById(R.id.total_points);
            courseLayout = view.findViewById(R.id.main_layout_course);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + courseName.getText() + "'";
        }
    }

}
