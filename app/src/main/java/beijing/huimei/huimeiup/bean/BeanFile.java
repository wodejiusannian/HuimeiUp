package beijing.huimei.huimeiup.bean;

import java.io.Serializable;

/**
 * Created by 小五 on 2018/8/27.
 */

public class BeanFile implements Serializable{
    public String fileName;
    public String filePath;

    public BeanFile(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
