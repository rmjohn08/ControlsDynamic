package net.rmj.android.controlsdinamic;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class OperatingSystemFragment extends Fragment {
    public static final String ARG_OS= "OS";
    public static final String ARG_FRAG = "FRAGMENT";
    public static final String STR_QUAKES = "quakes";
    public static final String STR_TEXT = "text";

    private String string;
    private String fragment;

    private LinearLayout myLynearLayout;
    private TextView valueTV;
    private Button valueB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;

        int pos=0;
        Bundle bun = getArguments();
        if (bun!=null){
            pos = this.getArguments().getInt(OperatingSystemFragment.ARG_OS);

        }

        switch (pos)
        {
            case 1:
                view = inflater.inflate(R.layout.fragment_relative_layout, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_frame_layout, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_linear_layout, null);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_operating_system, null);


        }

        return view;

        /* TextView textView = (TextView) view.findViewById(R.id.textView1);
        //sets text of current selection on textView
        textView.setText(string);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        myLynearLayout = (LinearLayout)view.findViewById(R.id.fragmentContainer);
        myLynearLayout.setOrientation(LinearLayout.VERTICAL);

        //myLynearLayout.setOrientation(LinearLayout.VERTICAL);

        valueTV = new TextView(view.getContext());
        valueTV.setText("The developer world is yours");
        //valueTV.setId(6);

        valueTV.setLayoutParams(params);

        valueB = new Button(view.getContext());
        valueB.setText("the world is yours");
        valueB.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        valueB.setPadding(2,4,2,4);
        valueB.setHeight(150);
        //valueB.setId(7);

        ToggleButton toggle = new ToggleButton(view.getContext());
        toggle.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        toggle.setPadding(0,0,0,10);

        myLynearLayout.addView(valueTV);
        myLynearLayout.addView(valueB);
        myLynearLayout.addView(toggle);

        return view;
        */
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /* @Override
    public void setArguments(Bundle args) {
        string = args.getString(ARG_OS);
        fragment = args.getString(ARG_FRAG);
    }
    */

}
