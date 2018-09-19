package beijing.huimei.huimeiup.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.adapter.MainPicAdapter;
import beijing.huimei.huimeiup.asyn.AsyncHttpUtils;
import beijing.huimei.huimeiup.bean.BeanMainPic;
import beijing.huimei.huimeiup.bean.BeanSelect;
import beijing.huimei.huimeiup.bean.BeanShop;
import beijing.huimei.huimeiup.ui.custom.AttributeDialog;
import beijing.huimei.huimeiup.ui.custom.HozManyRecycleView;
import beijing.huimei.huimeiup.ui.custom.HozRecycleView;
import beijing.huimei.huimeiup.utls.DataUtil;
import beijing.huimei.huimeiup.utls.HttpCallBack;
import beijing.huimei.huimeiup.utls.MUtilsInternet;
import beijing.huimei.huimeiup.utls.RvDecorationUtils;
import beijing.huimei.huimeiup.utls.SharedPreferenceUtils;
import beijing.huimei.huimeiup.utls.Util;

import static android.view.View.FOCUS_UP;
import static beijing.huimei.huimeiup.R.id.hozrv_caizhi;
import static beijing.huimei.huimeiup.R.id.hozrv_kind;
import static beijing.huimei.huimeiup.R.id.hozrv_sex;
import static beijing.huimei.huimeiup.R.id.hozrv_sytle;
import static beijing.huimei.huimeiup.R.id.hozrv_tuan;
import static beijing.huimei.huimeiup.R.id.hozrv_yichang;


public class EditActivity extends AppCompatActivity {

    private List<BeanShop> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        list = (List<BeanShop>) getIntent().getSerializableExtra("list");

        initView();
        initHoz();

        loadingData(ppppppp);
    }

    BeanShop beanShop;

    private void loadingData(int b) {
        beanShop = list.get(b);
        mD.clear();
        name.setText(beanShop.shopName);
        content.setText(beanShop.shopIntroduce);
        busynessPrice.setText(beanShop.busynessPirce);
        userPrice.setText(beanShop.userBuyPirce);
        //"/mnt/shared/Other/" + fileName + "/"
        String path = "/mnt/shared/Other/" + SharedPreferenceUtils.getName(EditActivity.this) + "/" + beanShop.id + "/";
        try {
            File file = new File(path);
            for (File f : file.listFiles()) {
                String path1 = f.getPath();
                if (path1.endsWith("png") || path1.endsWith("jpg"))
                    mD.add(new BeanMainPic(path1, "属性选择"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "亲，您可能没有选择货号，图片没有办法显示", Toast.LENGTH_LONG).show();
        }
        beanShop.pics = mD;
        mainPicAdapter.notifyDataSetChanged();
        mainPicAdapter.setOnItemLisener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int tag = (int) v.getTag();
                AttributeDialog dialog = new AttributeDialog(EditActivity.this);
                dialog.setOnListener(new AttributeDialog.OnItemListener() {
                    @Override
                    public void itemListener(int p, String str) {
                        Util.swap(mD, p, tag);
                        beanShop.pics = mD;
                        mainPicAdapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });
        if (id != null)
            hozrv_xinghao.setData(DataUtil.getDataXingHao(id), beanShop.xinghaos);
        hozrv_color.setData(DataUtil.getDataColor(), beanShop.colors);
        tv.setText((ppppppp + 1) + "/" + list.size());
        addCucun();
    }

    private String id;
    private MainPicAdapter mainPicAdapter;

    private List<BeanMainPic> mD;

    private void initHoz() {
        if (mD == null)
            mD = new ArrayList<>();
        if (mainPicAdapter == null)
            mainPicAdapter = new MainPicAdapter(this, mD);
        rv.setAdapter(mainPicAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.addItemDecoration(new RvDecorationUtils(5));
        hozrv_forPeople.setData(DataUtil.getDataSex());
        hozrv_color.setData(DataUtil.getDataColor());
        hozrv_season.setData(DataUtil.getDataSeason());
        hozrv_version.setData(DataUtil.getDataVersion());
        hozrv_mianliao.setData(DataUtil.getDataMianliao());
        hozrv_chengfen.setData(DataUtil.getDataChengfen());
        hozrv_pattern.setData(DataUtil.getDataTuan());
        hozrv_material.setData(DataUtil.getDataCaizhi());
        hozrv_clothesLength.setData(DataUtil.getDataYichang());
        hozrv_forPeople.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.forPeople = beanSelect;
                hozrv_type.setData(DataUtil.getDataAttribute(beanSelect.id));
            }
        });
        hozrv_type.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.type = beanSelect;
                hozrv_style.setData(DataUtil.getDataKind(hozrv_forPeople.getIdd(), beanSelect.id));
                id = beanSelect.id;
                hozrv_xinghao.setData(DataUtil.getDataXingHao(beanSelect.id), beanShop.xinghaos);
                hozrv_color.setData(DataUtil.getDataColor(), beanShop.colors);
                addCucun();
            }
        });

        hozrv_style.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.style = beanSelect;
            }
        });

        hozrv_season.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.season = beanSelect;
            }
        });

        hozrv_version.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.shopVersion = beanSelect;
            }
        });


        hozrv_mianliao.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.miaoLiao = beanSelect;
            }
        });


        hozrv_chengfen.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.hanliang = beanSelect;
            }
        });


        hozrv_pattern.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.pattern = beanSelect;
            }
        });


        hozrv_material.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.material = beanSelect;
            }
        });


        hozrv_clothesLength.setOnListener(new HozRecycleView.OnSelect() {
            @Override
            public void onSelectListener(BeanSelect beanSelect) {
                beanShop.clothesLength = beanSelect;
            }
        });


        hozrv_xinghao.setOnListener(new HozManyRecycleView.OnSelect() {
            @Override
            public void onSelectListener(List<String> strings) {
                beanShop.xinghaos = strings;
                addCucun();
            }
        });

        hozrv_color.setOnListener(new HozManyRecycleView.OnSelect() {
            @Override
            public void onSelectListener(List<String> strings) {
                beanShop.colors = strings;
                addCucun();
            }
        });

    }


    private RecyclerView rv;

    private int ppppppp = 0;

    private HozRecycleView hozrv_forPeople, hozrv_season, hozrv_type, hozrv_style, hozrv_version, hozrv_mianliao,
            hozrv_chengfen, hozrv_pattern, hozrv_clothesLength, hozrv_material;

    private HozManyRecycleView hozrv_color, hozrv_xinghao;

    private LinearLayout cucun;

    private EditText name, content, busynessPrice, userPrice;

    private MUtilsInternet net = MUtilsInternet.getInstance();

    private ScrollView scrollView;

    private TextView time;

    private TextView tv;

    private void initView() {
        rv = (RecyclerView) this.findViewById(R.id.rv);
        cucun = (LinearLayout) this.findViewById(R.id.ll_cucun);
        hozrv_forPeople = (HozRecycleView) this.findViewById(hozrv_sex);
        hozrv_season = (HozRecycleView) this.findViewById(R.id.hozrv_season);
        hozrv_type = (HozRecycleView) this.findViewById(R.id.hozrv_attribute);
        hozrv_style = (HozRecycleView) this.findViewById(hozrv_kind);
        hozrv_version = (HozRecycleView) this.findViewById(hozrv_sytle);
        hozrv_mianliao = (HozRecycleView) this.findViewById(R.id.hozrv_mianliao);
        hozrv_chengfen = (HozRecycleView) this.findViewById(R.id.hozrv_chengfen);
        hozrv_pattern = (HozRecycleView) this.findViewById(hozrv_tuan);
        hozrv_clothesLength = (HozRecycleView) this.findViewById(hozrv_yichang);
        hozrv_material = (HozRecycleView) this.findViewById(hozrv_caizhi);
        hozrv_color = (HozManyRecycleView) this.findViewById(R.id.hozrv_color);
        hozrv_xinghao = (HozManyRecycleView) this.findViewById(R.id.hozrv_xinghao);
        name = (EditText) this.findViewById(R.id.editText_name);
        content = (EditText) this.findViewById(R.id.editText_context);
        busynessPrice = (EditText) this.findViewById(R.id.editText_busynessprice);
        userPrice = (EditText) this.findViewById(R.id.editText_userbuy);
        time = (TextView) this.findViewById(R.id.tv_time);
        scrollView = (ScrollView) this.findViewById(R.id.scrollView);
        tv = (TextView) this.findViewById(R.id.tv);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
        this.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strName = name.getText().toString();
                final String strIntroduce = content.getText().toString();
                final String strPriceMarket = busynessPrice.getText().toString();
                final String strPriceSelling = userPrice.getText().toString();
                final String strOutFactoryDate = time.getText().toString();
                final BeanSelect forPeople = beanShop.forPeople;
                final BeanSelect type = beanShop.type;
                final BeanSelect style = beanShop.style;
                final BeanSelect season = beanShop.season;
                final BeanSelect shopVersion = beanShop.shopVersion;
                final BeanSelect material = beanShop.material;
                final BeanSelect pattern = beanShop.pattern;
                final BeanSelect clothesLength = beanShop.clothesLength;
                final BeanSelect miaoLiao = beanShop.miaoLiao;
                final BeanSelect hanliang = beanShop.hanliang;
                net.post(EditActivity.this, beanShop.pics, new MUtilsInternet.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONObject obj = new JSONObject(result);
                            JSONObject body = obj.getJSONObject("body");
                            JSONArray picNames = body.getJSONArray("picNames");
                            upData(picNames, strName, strIntroduce, strPriceMarket, strPriceSelling, forPeople,
                                    type, style, season, shopVersion, material, pattern, clothesLength, strOutFactoryDate, miaoLiao, hanliang);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void showTimeDialog() {
        DatePickerDialog di = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                time.setText(year + "年" + month + "月" + dayOfMonth + "日");
            }
        }, 2018, 9, 5);
        di.show();
    }

    private void upData(JSONArray picNames, String strName, String strIntroduce, String strPriceMarket, String strPriceSelling, BeanSelect forPeople,
                        BeanSelect type, BeanSelect style, BeanSelect season, BeanSelect shopVersion, BeanSelect material, BeanSelect patten,
                        BeanSelect clothesLength, String strOutFactoryDate, BeanSelect miaoliao, BeanSelect hanliang) {
        try {
            final JSONObject obj = new JSONObject();
            obj.put("userId", SharedPreferenceUtils.getUserData(this));
            obj.put("goodsPicture", picNames.get(0));
            obj.put("goodsPicSize", picNames.get(1));
            obj.put("goodsPic1", picNames.get(2));
            obj.put("goodsPic2", picNames.get(3));
            obj.put("goodsPic3", picNames.get(4));
            obj.put("name", strName);
            obj.put("describe", strIntroduce);
            obj.put("priceMarket", strPriceMarket);
            obj.put("priceSelling", strPriceSelling);
            obj.put("forPeopleId", hozrv_forPeople.getIdd());
            obj.put("forPeopleName", hozrv_forPeople.getName());
            obj.put("typeId", hozrv_type.getIdd());
            obj.put("typeName", hozrv_type.getName());
            obj.put("styleId", hozrv_style.getIdd());
            obj.put("styleName", hozrv_style.getName());
            obj.put("seasonId", hozrv_season.getIdd());
            obj.put("seasonName", hozrv_season.getName());
            obj.put("versionId", hozrv_version.getIdd());
            obj.put("versionName", hozrv_version.getName());
            obj.put("materialId", hozrv_material.getIdd());
            obj.put("materialName", hozrv_material.getName());
            obj.put("patternId", hozrv_pattern.getIdd());
            obj.put("patternName", hozrv_pattern.getName());
            obj.put("clothesLengthId", hozrv_clothesLength.getIdd());
            obj.put("clothesLengthName", hozrv_clothesLength.getName());
            obj.put("outFactoryDate", strOutFactoryDate);
            obj.put("uploader", SharedPreferenceUtils.getUploader(this));
            JSONArray addClothesPictureVos = new JSONArray();
            for (int i = 5; i < picNames.length(); i++) {
                JSONObject o = new JSONObject();
                o.put("pictureName", picNames.get(i));
                o.put("sort", i - 4);
                addClothesPictureVos.put(o);
            }
            obj.put("clothesUpload_AddClothesPictureVos", addClothesPictureVos);
            JSONArray AddClothesFabricVos = new JSONArray();
            JSONObject oo = new JSONObject();
            oo.put("fabric", hozrv_mianliao.getIdd());
            oo.put("percentage", hozrv_chengfen.getIdd());
            oo.put("sort", "1");
            AddClothesFabricVos.put(oo);
            obj.put("clothesUpload_AddClothesFabricVos", AddClothesFabricVos);
            int childCount = cucun.getChildCount();
            JSONArray AddClothesCSSVos = new JSONArray();
            for (int i = 0; i < childCount; i++) {
                View childAt = cucun.getChildAt(i);
                TextView colorName = (TextView) childAt.findViewById(R.id.tv_color);
                TextView sizeName = (TextView) childAt.findViewById(R.id.tv_xinghao);
                EditText stock = (EditText) childAt.findViewById(R.id.et_cucun);
                JSONObject o = new JSONObject();
                o.put("colorName", colorName.getText().toString());
                o.put("sizeName", sizeName.getText().toString());
                o.put("stock", stock.getText().toString());
                o.put("sort", i + 1);
                AddClothesCSSVos.put(o);
            }
            obj.put("clothesUpload_AddClothesCSSVos", AddClothesCSSVos);
            Log.e("TAG", "onResponse: ====" + obj.toString());
            new AsyncHttpUtils(new HttpCallBack() {
                @Override
                public void onResponse(String result) {
                    Log.e("TAG", "onResponse: ====" + result);
                    try {
                        JSONObject object = new JSONObject(result);
                        String ret = object.getString("ret");
                        if ("0".equals(ret)) {
                            if (ppppppp < list.size() - 1) {
                                ppppppp++;
                                loadingData(ppppppp);
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.fullScroll(FOCUS_UP);
                                    }
                                });
                                Toast.makeText(EditActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(EditActivity.this, FinishActivity.class));
                                EditActivity.this.finish();
                            }
                        } else {
                            Toast.makeText(EditActivity.this, "请检测是否联网，如果联网了传输错误，请联系开发者", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, EditActivity.this).execute("http://192.168.1.110/admiral/clothes/upload/addClothes.htm?token=82D5FBD40259C743ADDEF14D0E22F347", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addCucun() {
        cucun.removeAllViews();
        if (hozrv_color.getName() != null) {
            List<String> colors = hozrv_color.getName();
            for (int i = 0; i < colors.size(); i++) {
                List<String> xinghao = hozrv_xinghao.getName();
                if (xinghao != null && xinghao.size() > 0) {
                    for (int j = 0; j < xinghao.size(); j++) {
                        View view = LayoutInflater.from(this).inflate(R.layout.item_cucun, null);
                        TextView tvColor = (TextView) view.findViewById(R.id.tv_color);
                        TextView tvXinghao = (TextView) view.findViewById(R.id.tv_xinghao);
                        tvColor.setText(colors.get(i));
                        tvXinghao.setText(xinghao.get(j));
                        cucun.addView(view);
                    }
                } else {
                    View view = LayoutInflater.from(this).inflate(R.layout.item_cucun, null);
                    TextView tvColor = (TextView) view.findViewById(R.id.tv_color);
                    TextView tvXinghao = (TextView) view.findViewById(R.id.tv_xinghao);
                    tvColor.setText(colors.get(i));
                    cucun.addView(view);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null)
            list = null;
        if (mD != null)
            mD = null;
    }
}
