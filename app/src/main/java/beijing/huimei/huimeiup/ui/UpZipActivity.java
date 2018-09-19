package beijing.huimei.huimeiup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.adapter.FileAdapter;
import beijing.huimei.huimeiup.asyn.FileUpzipAsync;
import beijing.huimei.huimeiup.bean.BeanFile;

public class UpZipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_zip);
        initData();

    }

    private BeanFile beanFile;

    private void initData() {
        final List<BeanFile> mData = new ArrayList<>();
        FileAdapter adapter = new FileAdapter(mData, this);
        RecyclerView content = (RecyclerView) this.findViewById(R.id.content);
        content.setLayoutManager(new LinearLayoutManager(this));
        content.setAdapter(adapter);
        final FileUpzipAsync fileUpzipAsync = new FileUpzipAsync(this);
        fileUpzipAsync.setReadFileListener(new FileUpzipAsync.ReadFile() {
            @Override
            public void end() {
                Toast.makeText(UpZipActivity.this, "解压完成", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("file", beanFile);
                setResult(101, intent);
                finish();
            }
        });
        adapter.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_itemfile:
                        int p = (int) v.getTag();
                        beanFile = mData.get(p);
                        fileUpzipAsync.execute(beanFile.filePath, "/mnt/shared/Other/");
                        break;
                    default:
                        break;
                }
            }
        });
        File file = new File("/mnt/shared/Other/");
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().endsWith("zip"))
                mData.add(new BeanFile(f.getName(), f.getPath()));
        }
    }

}
