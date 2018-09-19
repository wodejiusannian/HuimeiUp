package beijing.huimei.huimeiup.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.adapter.ContentAdapter;
import beijing.huimei.huimeiup.asyn.FileReadExcelAsync;
import beijing.huimei.huimeiup.bean.BeanExcel;
import beijing.huimei.huimeiup.bean.BeanShop;
import beijing.huimei.huimeiup.ui.custom.CustomDialog;
import beijing.huimei.huimeiup.ui.custom.SyncHorizontalScrollView;
import beijing.huimei.huimeiup.utls.Util;


public class ExcelActivity extends AppCompatActivity {

    private RecyclerView content;

    private LinearLayout linearLayout;

    private SyncHorizontalScrollView headerS;

    private SyncHorizontalScrollView contentS;

    private List<BeanShop> shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        String path = getIntent().getStringExtra("xlsFilePath");
        initView();
        initData();
        FileReadExcelAsync async = new FileReadExcelAsync(this);
        async.setReadFileListener(new FileReadExcelAsync.ReadFile() {
            @Override
            public void rows(int rows) {
            }

            @Override
            public void dataes(List<BeanExcel> mdata) {
                mData.clear();
                shops.clear();
                mData.addAll(mdata);
                for (int i = 0; i < mData.size() / 10; i++) {
                    shops.add(new BeanShop());
                }
                adapter.notifyDataSetChanged();
            }
        });
        async.execute(path);
    }

    private ContentAdapter adapter;

    private int position = 0;

    private List<BeanExcel> mData;

    private void initData() {
        for (int i = 0; i < 10; i++) {
            final TextView tv = new TextView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Util.dp2px(this, 100), Util.dp2px(this, 60));
            //params.setMargins(Util.dp2px(this, 10), Util.dp2px(this, 5), Util.dp2px(this, 10), Util.dp2px(this, 5));
            tv.setLayoutParams(params);
            tv.setText("此列属性");
            tv.setBackgroundColor(Color.CYAN);
            tv.setGravity(Gravity.CENTER);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> array = new ArrayList<String>();
                    for (int j = 0; j < linearLayout.getChildCount(); j++) {
                        TextView childAt = (TextView) linearLayout.getChildAt(j);
                        array.add(childAt.getText().toString());
                    }
                    position = finalI;
                    dialog.setData(array);
                }
            });
            linearLayout.addView(tv);

        }
    }


    private CustomDialog dialog;

    private void initView() {
        if (mData == null)
            mData = new ArrayList<>();
        if (adapter == null)
            adapter = new ContentAdapter(mData, this);
        if (shops == null)
            shops = new ArrayList<>();
        this.headerS = (SyncHorizontalScrollView) this.findViewById(R.id.headers);
        this.contentS = (SyncHorizontalScrollView) this.findViewById(R.id.contents);
        this.linearLayout = (LinearLayout) this.findViewById(R.id.title);
        dialog = new CustomDialog(this);
        dialog.setOnListener(new CustomDialog.OnItemListener() {
            @Override
            public void itemListener(int p, String str) {
                try {
                    for (int i = 0; i < shops.size(); i++) {
                        BeanExcel beanExcel = mData.get(i * 10 + position);
                        BeanShop beanShop = shops.get(i);
                        switch (str) {
                            case "货号（货号一定要选择,不清楚可以咨询":
                                beanShop.id = beanExcel.content;
                                break;
                            case "出厂日期":
                                beanShop.outFactoryDate = beanExcel.content;
                                break;
                            case "商品名称":
                                beanShop.shopName = beanExcel.content;
                                break;
                            case "商品描述":
                                beanShop.shopIntroduce = beanExcel.content;
                                break;
                            case "商户结算价格":
                                beanShop.busynessPirce = beanExcel.content;
                                break;
                            case "用户购买价格":
                                beanShop.userBuyPirce = beanExcel.content;
                                break;
                            case "颜色":
                                String content = beanExcel.content;
                                beanShop.colors = Util.strToArray(content);
                                break;
                            case "型号":
                                String content1 = beanExcel.content;
                                beanShop.xinghaos = Util.strToArray(content1);
                                break;
                           /*  case 7:
                                beanShop.season = beanExcel.content;
                                break;
                            case 8:
                                beanShop.shopAttribute = beanExcel.content;
                                break;
                            case 9:
                                beanShop.shopKind = beanExcel.content;
                                break;
                            case 10:
                                beanShop.shopStyle = beanExcel.content;
                                break;
                            case 11:
                                beanShop.miaoLiao = beanExcel.content;
                                break;
                            case 12:
                                beanShop.hanliang = beanExcel.content;
                                break;
                            case 13:
                                beanShop.clothLong = beanExcel.content;
                                break;
                            case 14:
                                beanShop.xinghao = beanExcel.content;
                                break;
                            case 15:
                                beanShop.color = beanExcel.content;
                                break;
                            case 16:
                                beanShop.count = beanExcel.content;
                                break;*/
                        }
                    }
                    TextView childAt = (TextView) linearLayout.getChildAt(position);
                    childAt.setText(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ExcelActivity.this, "出错了，联系开发者", Toast.LENGTH_SHORT).show();
                }
            }
        });
        content = (RecyclerView) this.findViewById(R.id.content);
        content.setAdapter(adapter);
        content.setLayoutManager(new GridLayoutManager(this, 10));
        contentS.setScrollView(headerS);
        headerS.setScrollView(contentS);
        this.findViewById(R.id.tv_excelnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shops != null && shops.size() > 0)
                    shops.remove(0);
                Intent intent = new Intent(ExcelActivity.this, EditActivity.class);
                intent.putExtra("list", (Serializable) shops);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shops != null)
            shops = null;
        if (mData != null)
            mData = null;
    }
}
