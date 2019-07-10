package fragment.news;

import android.content.Context;
import android.content.Intent;
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
import ui.news.NewDetialActivity;
import view.widgets.MySwipyRefreshLayout;

public class NewTopFragment extends BaseFragment2{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefresh)
    MySwipyRefreshLayout swiperefresh;
    protected Subscription subscription;

    private ArrayList<NewTop.New> list;
    private Context context = getActivity();
    private NewTopAdapter adapter;
    public static NewTopFragment newInstance() {

        NewTopFragment fragemnt_location = new NewTopFragment();
        return fragemnt_location;

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("----new","NewTopFragment initViews");
        View view = inflater.inflate(R.layout.fragment_new_top, container, false);
        ButterKnife.bind(this, view);
        swiperefresh.post(new Runnable() {
            @Override
            public void run() {
                swiperefresh.setRefreshing(true);
            }
        });
        swiperefresh.setOnRefreshListener(new MySwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                swiperefresh.setRefreshing(false);
                return;
            }

            @Override
            public void onLoad(int index) {
                swiperefresh.setRefreshing(false);
                return;
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        Log.i("----new","NewTopFragment initViews");
        list = new ArrayList<NewTop.New>();
        adapter = new NewTopAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        loadData();
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewTopAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, NewTop.New data) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewDetialActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        subscription = Network.getNewFunApi().getNewTop("1", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    Observer<NewTop> observer = new Observer<NewTop>() {
        @Override
        public void onCompleted() {
            //swiperefresh.setRefreshing(false);
            Log.i("--rxjava--", "onCompleted");
            swiperefresh.setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.i("--rxjava--e", e.toString());
            swiperefresh.setRefreshing(false);
        }

        @Override
        public void onNext(NewTop d) {
            Log.i("--rxjava--onNext", d.getData().size() + "");
            list.addAll(d.getData());
            adapter.notifyDataSetChanged();
            swiperefresh.setRefreshing(false);
        }
    };

    @Override
    protected void setDefaultFragmentTitle(String title) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
