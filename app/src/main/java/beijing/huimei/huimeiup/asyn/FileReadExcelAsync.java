package beijing.huimei.huimeiup.asyn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.bean.BeanExcel;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by 小五 on 2018/8/23.
 */

public class FileReadExcelAsync extends AsyncTask<String, Integer, List<BeanExcel>> {

    private Context context;

    public FileReadExcelAsync(Context context) {
        this.context = context;
    }

    private ProgressDialog show;

    private ReadFile readFile;

    public void setReadFileListener(ReadFile readFile) {
        this.readFile = readFile;
    }

    public interface ReadFile {

        void rows(int rows);

        void dataes(List<BeanExcel> mdata);

    }

    /*
    * 开始加载
    * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        show = ProgressDialog.show(context, "进度", "读取中....", false);
    }

    /*
    * 加载进度
    * */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /*
    * 加载中
    * */
    @Override
    protected List<BeanExcel> doInBackground(String... params) {
        List<BeanExcel> d = new ArrayList<>();
        try {
            File file = new File(params[0]);
            FileInputStream is = new FileInputStream(file);
            Workbook workbook = Workbook.getWorkbook(is);
            //获取第一张excel数据表。
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();//获取该表中有多少行数据。
            int columns = sheet.getColumns();
            readFile.rows(rows);
            Range[] rangeCell = sheet.getMergedCells();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j < columns) {
                        String str = null;
                        str = (sheet.getCell(j, i)).getContents();
                        for (Range r : rangeCell) {
                            if (i > r.getTopLeft().getRow()
                                    && i <= r.getBottomRight().getRow()
                                    && j >= r.getTopLeft().getColumn()
                                    && j <= r.getBottomRight().getColumn()) {
                                str = sheet.getCell(r.getTopLeft().getColumn(),
                                        r.getTopLeft().getRow()).getContents();
                            }
                        }
                        d.add(new BeanExcel(j,str ));
                    } else {
                        d.add(new BeanExcel(j, ""));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /*
    * 加载完成
    * */
    @Override
    protected void onPostExecute(List<BeanExcel> beanShops) {
        super.onPostExecute(beanShops);
        if (show != null)
            show.dismiss();
        readFile.dataes(beanShops);
    }
}
