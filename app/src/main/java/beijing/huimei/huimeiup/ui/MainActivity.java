package beijing.huimei.huimeiup.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.adapter.MainAdapter;
import beijing.huimei.huimeiup.bean.BeanFile;
import beijing.huimei.huimeiup.utls.SharedPreferenceUtils;

public class MainActivity extends AppCompatActivity {
    private Button btnUpZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        this.findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                SharedPreferenceUtils.writeUserData(MainActivity.this, null);
            }
        });
        final Button btn_read = (Button) this.findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataFile.clear();
                //
                //"/sdcard/huimeiup/"
                File file = new File("/mnt/shared/Other/");
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.isDirectory())
                        mDataFile.add(f.getName());
                }
                adapterFile.notifyDataSetChanged();
                btn_read.setText("请看下面，有一些文件夹，请先选择你刚才解压的文件夹，然后选择要读取的excel文件,如果没有数据请确定否解压，或者联系开发者");
            }
        });
        btnUpZip = (Button) this.findViewById(R.id.btn_upzip);
        btnUpZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                boolean c = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (b && c) {
                    upZIP();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                }
            }
        });

        isLogin();
    }

    private void isLogin() {
        String userData = SharedPreferenceUtils.getUserData(this);
        if (userData.isEmpty() || userData.length() == 0) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private MainAdapter adapterFile;

    private MainAdapter adapterExcel;

    private List<String> mDataFile;

    private List<String> mDataExcel;

    private void initView() {
        if (mDataFile == null)
            mDataFile = new ArrayList<>();
        if (mDataExcel == null)
            mDataExcel = new ArrayList<>();
        if (adapterFile == null)
            adapterFile = new MainAdapter(mDataFile, this);
        if (adapterExcel == null)
            adapterExcel = new MainAdapter(mDataExcel, this);
        RecyclerView rv_file = (RecyclerView) this.findViewById(R.id.rv_file);
        RecyclerView rv_excel = (RecyclerView) this.findViewById(R.id.rv_excel);
        rv_excel.setLayoutManager(new LinearLayoutManager(this));
        rv_file.setLayoutManager(new LinearLayoutManager(this));
        rv_file.setAdapter(adapterFile);
        rv_excel.setAdapter(adapterExcel);
        adapterFile.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataExcel.clear();
                int p = (int) v.getTag();
                String fileName = mDataFile.get(p);
                SharedPreferenceUtils.writeName(MainActivity.this, fileName);
                //"/sdcard/huimeiup/" + fileName + "/"
                File file = new File("/mnt/shared/Other/" + fileName + "/");
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.getName().endsWith("xls"))
                        mDataExcel.add(f.getPath());
                }
                adapterExcel.notifyDataSetChanged();
            }
        });
        adapterExcel.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = (int) v.getTag();
                String xlsFilePath = mDataExcel.get(p);
                Intent in = new Intent(MainActivity.this, ExcelActivity.class);
                in.putExtra("xlsFilePath", xlsFilePath);
                startActivity(in);
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    upZIP();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "亲，不给读写权限没有办法获取数据的，请在权限设置中给应用权限，如果不会请联系开发者", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static final int EXCELCODE = 100;

    private void upZIP() {
        Intent intent = new Intent(this, UpZipActivity.class);
        startActivityForResult(intent, EXCELCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (EXCELCODE == requestCode && 101 == resultCode) {
            BeanFile file = (BeanFile) data.getSerializableExtra("file");
            btnUpZip.setText("已解压" + file.fileName + "请点击读取按钮如果解压错了，请重新点击本按钮");
        }
    }
}
