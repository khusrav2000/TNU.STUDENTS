package tj.tnu.students.points;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import tj.tnu.students.R;
import tj.tnu.students.data.model.Courses;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LessonFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;


    public LessonFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public LessonFragment newInstance(int columnCount) {
        LessonFragment fragment = new LessonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_list, container, false);

        // Set the adapter

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        setAdapter();


        return view;
    }

    private void setAdapter() {
        List<Courses> coursesList = new ArrayList<>();
        Courses courses;
        courses = new Courses("Таҳлили математика", "Юсупов");
        coursesList.add(courses);
        courses = new Courses("Диншиноси", "Амирбекова И.");
        coursesList.add(courses);
        courses = new Courses("Ҳуқуқ", "Сафарзода Н");
        coursesList.add(courses);
        courses = new Courses("Географияи Тоҷикистон бо асосҳои демографии он.", "Юнусов Талабшо.");
        coursesList.add(courses);


        recyclerView.setAdapter(new MylessonRecyclerViewAdapter(coursesList, mListener));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Courses item);
    }
}
