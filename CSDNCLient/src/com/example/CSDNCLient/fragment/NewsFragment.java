package com.example.CSDNCLient.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.CSDNCLient.R;
import com.example.CSDNCLient.adapter.NewsAdaptor;
import com.nju.csdnclient.bean.CSDNUnit;
import com.nju.csdnclient.bean.exception.CSDNException;
import com.nju.csdnclient.service.UnitService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by never on 2014/8/30.
 */
public class NewsFragment extends Fragment {
    private Activity activity;
    private ListView mListView;
    private NewsAdaptor adaptor;
    private String text;
    private ImageView detail_loading;

    private int currentPage = 1;
    private List<CSDNUnit> unitDatas = new ArrayList<CSDNUnit>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        initData();
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadDataTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        new LoadDataTask().execute();
        adaptor = new NewsAdaptor(getActivity(), unitDatas);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
        mListView = (ListView) view.findViewById(R.id.mListView);
        mListView.setAdapter(adaptor);
        TextView item_textview = (TextView) view.findViewById(R.id.item_textview);
        detail_loading = (ImageView) view.findViewById(R.id.detail_loading);
        item_textview.setText(text);
        return view;
    }



    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                List<CSDNUnit> units = UnitService.getCSDNUnits(1, currentPage);
                unitDatas = units;
            } catch (CSDNException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            adaptor.addDatas(unitDatas);
            adaptor.notifyDataSetChanged();
        }
    }
}
