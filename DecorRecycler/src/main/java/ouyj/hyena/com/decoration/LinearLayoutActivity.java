package ouyj.hyena.com.decoration;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
 * 普通线性布局
 */
public class LinearLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Y_ItemEntityList itemEntityList = new Y_ItemEntityList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        //创建数据源
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            items.add("item" + i);
        }

        //绑定时插入项文本（使用自定义布局，修改数据源）
        itemEntityList.addItems(R.layout.item_recyclerview, items)
                .addOnBind(R.layout.item_recyclerview, new Y_OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        holder.setText(R.id.textView, (String) itemData);
                    }
                });

        //查找回收视图引用
        recyclerView = findViewById(R.id.lstView);
        //为回收视图并设置布局管理器以及分割线和适配器（传送数据源）
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(new Y_MultiRecyclerAdapter(this, itemEntityList));
    }

    /**
     * 定义回收视图的分割线
     * 第5单元格设置底部（它会作为第6单元格的顶部）
     * 第6单元格设置设置左栏和右栏以及底部
     */
    class DividerItemDecoration extends Y_DividerItemDecoration {
        public DividerItemDecoration(Context context) {
            super(context);
        }
        @Override
        public Y_Divider getDivider(int itemPosition) {
            Y_Divider divider;
            switch (itemPosition) {
                case 5:
                    divider = new Y_DividerBuilder()
                            .setBottomSideLine(
                                    true,
                                    0xffFF4081,
                                    4,
                                    0,
                                    0).create();
                    break;
                case 6:
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(
                                    true,
                                    0xffFF4081,
                                    4,
                                    0,
                                    0
                            )
                            .setRightSideLine(
                                    true,
                                    0xffFF4081,
                                    4,
                                    0,
                                    0
                            )
                            .setBottomSideLine(
                                    true,
                                    0xffFF4081,
                                    4,
                                    0,
                                    0
                            ).create();
                    break;
                default:
                    divider = new Y_DividerBuilder()
                            .setBottomSideLine(
                                    true,
                                    0xff666666,
                                    4,
                                    0,
                                    0).create();
                    break;
            }
            return divider;
        }
    }
}
