package fragment.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import java.util.ArrayList;

import adapter.NewTopAdapter;
import base.BaseFragment2;
import bean.NewTop;
import butterknife.Bind;
import butterknife.ButterKnife;
import network.Network;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import view.widgets.MySwipyRefreshLayout;
public class NewFunFragment extends BaseFragment2 {
    public static NewFunFragment newInstance() {
        NewFunFragment fragemnt_location = new NewFunFragment();

        return fragemnt_location;
    }
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("----new", "NewFunFragment setUpView");
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        ButterKnife.bind(this, view);
        return view;

    }
    @Override
    protected void initData() {
    }
    @Override
    protected void setDefaultFragmentTitle(String title) {
    }
}