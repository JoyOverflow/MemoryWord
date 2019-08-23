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
 * 异形网格布局
 */
public class SpanLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Y_ItemEntityList itemEntityList = new Y_ItemEntityList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);


        //数据源
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            items.add("item" + i);
        }
        itemEntityList.addItems(R.layout.item_recyclerview, items)
                .addOnBind(R.layout.item_recyclerview, new Y_OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        holder.setText(R.id.textView, (String) itemData);
                    }
                });

        recyclerView = findViewById(R.id.lstView);

        //设置回收视图1行有12列（SpanCount是每行显示的列数）
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 12);
        //根据position来设置动态设置SpanSize
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 1) {
                    //原一行有12列，若spanSize=6那么该行两列即占满整行
                    return 6;
                } else if (position == 6 || position == 10) {
                    //原一行有12列，此时该行1列即占满整行
                    return 12;
                } else if (position >= 7 && position <= 9) {
                    //原一行有12列，此时该行仅3列（每列占4个span位置）
                    return 4;
                } else if (position >= 2 && position <= 5) {
                    //原一行有12列，此时该行4列占满整行
                    return 3;
                }
                //默认该行有4列（1列占3个span位置）
                return 3;
            }
        });
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
            if ((itemPosition >= 1 && itemPosition <= 6) || itemPosition == 9 || itemPosition == 10) {
                divider = new Y_DividerBuilder()
                        .setBottomSideLine(true, 0xff666666, 6, 0, 0)
                        .create();
            } else if (itemPosition == 0 || itemPosition == 7 || itemPosition == 8) {
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, 0xff666666, 6, 0, 0)
                        .setBottomSideLine(true, 0xff666666, 6, 0, 0)
                        .create();
            } else if (itemPosition > 10 && itemPosition < 22) {
                switch ((itemPosition - 10) % 4) {
                    case 1:
                    case 2:
                    case 3:
                        divider = new Y_DividerBuilder()
                                .setRightSideLine(true, 0xff666666, 6, 0, 0)
                                .setBottomSideLine(true, 0xff666666, 6, 0, 0)
                                .create();
                        break;
                    case 0:
                        divider = new Y_DividerBuilder()
                                .setBottomSideLine(true, 0xff666666, 6, 0, 0)
                                .create();
                        break;
                    default:
                        break;
                }
            }
            return divider;
        }
    }
}
