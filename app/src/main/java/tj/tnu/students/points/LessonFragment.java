package tj.tnu.students.points;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tj.tnu.students.Data;
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
    Dialog semesters;
    RecyclerView semestersRecyclerView;


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


        view.findViewById(R.id.button_select_semester).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semesters.show();
            }
        });
        // Set the adapter


        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        semesters = new Dialog(getContext());
        semesters.setContentView(R.layout.fragment_semesters_list);
        semesters.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.select_semester_background));
        semestersRecyclerView = semesters.findViewById(R.id.semester_list);
        semestersRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        semesters.findViewById(R.id.select_semester_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semesters.dismiss();
            }
        });

        setSemestersAdapter();
        setCourseAdapter();

        return view;
    }

    public void setSemestersAdapter() {
        System.out.println(Data.getSemesters().toString());
        semestersRecyclerView.setAdapter(new MySemestersRecyclerViewAdapter(getActivity(), Data.getSemesters(), mListener));
    }

    public void setCourseAdapter() {
        recyclerView.setAdapter(new MylessonRecyclerViewAdapter(Data.getCourses(), mListener));
    }

    public void dismissSelectSemester(){
        semesters.dismiss();
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
        void onSelectSemester(int semesterId, int position);
    }

}
