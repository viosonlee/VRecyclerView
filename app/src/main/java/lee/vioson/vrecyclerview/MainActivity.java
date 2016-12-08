package lee.vioson.vrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import lee.vioson.vrecyclerviewlibrary.MFooter;
import lee.vioson.vrecyclerviewlibrary.VRecyclerView;

public class MainActivity extends AppCompatActivity implements VRecyclerView.VRecyclerViewLoader {
    private static final int COMPLETE = 0;
    private VRecyclerView list;
    private List<String> items = new ArrayList<>();
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        addItems();
    }

    private void addItems() {
        if (page == 1)
            items.clear();
        for (int i = 15 * (page - 1); i < 15 * page; i++) {
            items.add("item" + i);
        }
    }

    private void initView() {
        list = (VRecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ListAdapter(new MFooter(this), items));
        list.addVRecyclerViewLoader(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETE) {
                addItems();
                list.getAdapter().notifyDataSetChanged();
                list.loadComplete();
                if (page == 5) {
                    list.loadAllDataOk();
                    page = 1;
                }
            }
        }
    };

    @Override
    public void loadMore() {
        page++;
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                handler.sendEmptyMessage(COMPLETE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
