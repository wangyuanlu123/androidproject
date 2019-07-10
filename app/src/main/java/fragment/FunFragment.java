package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.myproject.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BaseFragment2;
import ui.CardActivity;
import ui.CollecttionActivity;
import ui.MessageActivity;
import ui.SettingActivity;

public class FunFragment extends BaseFragment2 {
    private View view;
    private Integer[] ims = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};
    private String[] name = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    private ListView listView;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.fragment_fun, container, false);
            listView = (ListView) view.findViewById(R.id.list_view);
            load();
        }

            return view;
}
    @Override
    protected void initData() {
        Log.i("-----fragment","fun initData()");

    }
    @Override
    protected void setDefaultFragmentTitle(String title) {
        setHasOptionsMenu(true);
        getActivity().getActionBar().setTitle("娱乐");

    }
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("-----fragment","me destory()");
    }
        protected void load() {
           List<Map<String, Object>> listems = new ArrayList<>();
            for (int j= 0; j< name.length; j++) {
                Map<String, Object> map= new HashMap<>();
                map.put("image", ims[j]);
                map.put("name", name[j]);
                listems.add(map);
               SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),listems ,
                        R.layout.list_item, new String[]{ "name", "image"},new int[] {R.id.tv,R.id.iv});
                listView.setAdapter(simpleAdapter);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position,
                                        long id) {
                    System.out.println(position);
                    switch (position) {
                        case 0:
                           Intent intent = new Intent(getActivity(), SettingActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent01 = new Intent(getActivity(), CardActivity.class);
                            startActivity(intent01);
                            break;
                        case 2:
                            Intent intent02 = new Intent(getActivity(), MessageActivity.class);
                            startActivity(intent02);
                            break;
                        case 3:
                            Intent intent03 = new Intent(getActivity(), CollecttionActivity.class);
                            startActivity(intent03);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

