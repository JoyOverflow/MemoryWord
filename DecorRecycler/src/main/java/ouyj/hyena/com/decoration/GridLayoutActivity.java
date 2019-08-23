package ouyj.hyena.com.decoration;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ouyj.hyena.com.decoration.adapter.GeneralRecyclerViewHolder;
import ouyj.hyena.com.decoration.adapter.Y_ItemEntityList;
import ouyj.hyena.com.decoration.adapter.Y_MultiRecyclerAdapter;
import ouyj.hyena.com.decoration.adapter.Y_OnBind;
import ouyj.hyena.com.decoration.view.Y_Divider;
import ouyj.hyena.com.decoration.view.Y_DividerBuilder;
import ouyj.hyena.com.decoration.view.Y_DividerItemDecoration;

/**
 * 普通网格布局
 */
public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Y_ItemEntityList itemEntityList = new Y_ItemEntityList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        //数据源
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add("item" + i);
        }
        itemEntityList.addItems(R.layout.item_recyclerview, items)
                .addOnBind(R.layout.item_recyclerview, new Y_OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        holder.setText(R.id.textView, (String) itemData);
                    }
                });

        //设置Grid的列数目为3
        recyclerView = findViewById(R.id.lstView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new Y_MultiRecyclerAdapter(this, itemEntityList));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
    }

    private class DividerItemDecoration extends Y_DividerItemDecoration {
        private DividerItemDecoration(Context context) {
            super(context);
        }
        @Override
        public Y_Divider getDivider(int itemPosition) {
            Y_Divider divider = null;
            switch (itemPosition % 3) {
                case 0:
                case 1:
                    //每行的第1和第2个元素显示出右边框和底部边框
                    divider = new Y_DividerBuilder()
                            .setRightSideLine(
                                    true,
                                    0xff666666,
                                    4,
                                    0,
                                    0
                            )
                            .setBottomSideLine(
                                    true,
                                    0xff666666,
                                    4,
                                    0,
                                    0).create();
                    break;
                case 2:
                    //每行的最后元素只显示底部边框
                    divider = new Y_DividerBuilder()
                            .setBottomSideLine(
                                    true,
                                    0xff666666,
                                    4,
                                    0,
                                    0
                            ).create();
                    break;
                default:
                    break;
            }
            return divider;
        }
    }
}
