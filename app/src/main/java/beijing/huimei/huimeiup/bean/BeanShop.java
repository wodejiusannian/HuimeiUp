package beijing.huimei.huimeiup.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小五 on 2018/8/29.
 */

public class BeanShop implements Serializable{
    public List<BeanMainPic> pics;
    public String id;
    public String shopName;
    public String shopIntroduce;
    public String busynessPirce;
    public String userBuyPirce;
    public BeanSelect forPeople;
    public BeanSelect type;
    public BeanSelect style;
    public BeanSelect season;
    public BeanSelect shopVersion;
    public BeanSelect material;
    public BeanSelect miaoLiao;
    public BeanSelect pattern;
    public BeanSelect clothesLength;
    public String outFactoryDate;
    public BeanSelect hanliang;
    public String count;
    public String color;
    public String xinghao;
    public List<String> colors;
    public List<String> xinghaos;
}
