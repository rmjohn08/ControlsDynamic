package net.rmj.android.controlsdinamic;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import net.rmj.android.controlsdinamic.network.QuakesHandler;
import net.rmj.android.controlsdinamic.network.RequestReadSimpleQuake;

import java.util.ArrayList;
import java.util.List;

public class QuakesListFragment extends Fragment {
    public static final String ARG_FRAG = "FRAGMENT";
    public static final String STR_QUAKES = "quakes";
    private String string;
    private String fragment;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quakes_list_layout, null);
        // extra processing...

        MainApplication.getInstance().showSpinner(this.getActivity());

        try {
            MainApplication.getInstance().addToRequestQueue(new RequestReadSimpleQuake().makeReadSimpleQuake(new QuakesListHandler()));
        } catch(Exception ex) {

            ex.printStackTrace();
            MainApplication.getInstance().hideSpinner();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        fragment = args.getString(ARG_FRAG);
    }

    private class QuakesListHandler extends QuakesHandler {
        @Override
        public void handleQuakes(List<String> list) {

            ListView listView = (ListView)view.findViewById(R.id.quakesList);

            //String[] quakes = new String[]{"quake 1","quake 2","quake 3","quake 4"};

            /* final ArrayList<String> list = new ArrayList<String>();
            for (int i=0; i<quakes.length; i++){
                list.add(quakes[i]);
            }
            */
            final ArrayAdapter adapter = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_list_item_1,
                    list);

            listView.setAdapter(adapter);

            // set any listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    // build the url with the event id for selected
                    String EVENT_TAG ="Event:";
                    TextView txt = (TextView)v.findViewById(android.R.id.text1);
                    String eventId = txt.getText().toString();
                    int pos = eventId.indexOf(EVENT_TAG);
                    eventId = eventId.substring(pos+EVENT_TAG.length());

                    String url = RequestReadSimpleQuake.DETAIL_URL+"/"+eventId;

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                    /*
                    TextView txtName = (TextView)v.findViewById(R.id.itemName);
                    if (txtName!=null)
                        Toast.makeText(rootView.getContext(),"Clicked " + txtName.getText(), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(rootView.getContext(),"text element "+position, Toast.LENGTH_LONG).show();

                    */
                }
            });

            MainApplication.getInstance().hideSpinner();
        }
    }


}
