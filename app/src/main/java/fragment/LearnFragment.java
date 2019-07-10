package fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;
import com.example.myproject.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import base.BaseFragment2;

public class LearnFragment extends BaseFragment2 {
    final int FLAG_MSG = 0x001;    //定义要发送的消息代码
    private ViewFlipper flipper;   //定义ViewFlipper
    private Message message;        //声明消息对象
    private View view;
    private Integer[] ims = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};
    private String[] name = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    private GridView gridView;
    //定义图片数组
    private int[] images = new int[]{R.drawable.b3 ,R.drawable.b2, R.drawable.b1};
    private Animation[] animation = new Animation[2];  //定义动画数组，为ViewFlipper指定切换动画
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_learn, container, false);
            gridView = (GridView) view.findViewById(R.id.grid_view);
            flipper = (ViewFlipper) view.findViewById(R.id.viewFlipper); //获取ViewFlipper
            for (int i = 0; i < images.length; i++) {      //遍历图片数组中的图片
                ImageView imageView = new ImageView(getContext());  //创建ImageView对象
                imageView.setImageResource(images[i]);  //将遍历的图片保存在ImageView中
                flipper.addView(imageView);             //加载图片
            }
            //初始化动画数组
            animation[0] = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right); //右侧平移进入动画
            animation[1] = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left); //左侧平移退出动画
            flipper.setInAnimation(animation[0]);   //为flipper设置图片进入动画效果
            flipper.setOutAnimation(animation[1]);  //为flipper设置图片退出动画效果
            message = Message.obtain();       //获得消息对象
            message.what = FLAG_MSG;  //设置消息代码
            handler.sendMessage(message); //发送消息
        load();
    }
        return view;
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
      Handler handler = new Handler() {  //创建android.os.Handler对象
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == FLAG_MSG) {  //如果接收到的是发送的标记消息
                    flipper.showPrevious();                  //显示下一张图片
                }
                message = handler.obtainMessage(FLAG_MSG);   //获取要发送的消息
                handler.sendMessageDelayed(message, 3000);  //延迟3秒发送消息
            }
        };
        protected void load() {
           List<Map<String, Object>> listems = new ArrayList<>();
            for (int j= 0; j< name.length; j++) {
               Map<String, Object> map= new HashMap<>();
                map.put("image", ims[j]);
                map.put("name", name[j]);
                listems.add(map);
              SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),listems ,
                        R.layout.grid_item, new String[]{ "name", "image"},new int[] {R.id.tv1,R.id.iv1});
                gridView.setAdapter(simpleAdapter);
            }
        }
    }
