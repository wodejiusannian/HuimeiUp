package beijing.huimei.huimeiup.utls;

import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.bean.BeanSelect;

/**
 * Created by 小五 on 2018/9/3.
 */

public class DataUtil {

    public static List<BeanSelect> getDataSeason() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "春秋", "111"));
        mData.add(new BeanSelect(false, "夏", "112"));
        mData.add(new BeanSelect(false, "秋", "113"));
        mData.add(new BeanSelect(false, "四季", "114"));
        return mData;
    }

    public static List<BeanSelect> getDataVersion() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "紧身", "701"));
        mData.add(new BeanSelect(false, "修身", "702"));
        mData.add(new BeanSelect(false, "常规", "703"));
        mData.add(new BeanSelect(false, "直筒", "704"));
        mData.add(new BeanSelect(false, "宽松", "705"));
        mData.add(new BeanSelect(false, "收腰", "706"));
        return mData;
    }

    public static List<BeanSelect> getDataMianliao() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "毛", "毛"));
        mData.add(new BeanSelect(false, "麻", "麻"));
        mData.add(new BeanSelect(false, "棉", "棉"));
        mData.add(new BeanSelect(false, "皮草", "皮草"));
        mData.add(new BeanSelect(false, "皮革", "皮革"));
        mData.add(new BeanSelect(false, "雪纺", "雪纺"));
        mData.add(new BeanSelect(false, "化学纤维", "化学纤维"));
        mData.add(new BeanSelect(false, "合成纤维", "合成纤维"));
        return mData;
    }

    public static List<BeanSelect> getDataChengfen() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "30%及以下", "30%及以下"));
        mData.add(new BeanSelect(false, "31%(含)-50%(含)", "31%(含)-50%(含)"));
        mData.add(new BeanSelect(false, "51%(含)-70%(含)", "51%(含)-70%(含)"));
        mData.add(new BeanSelect(false, "71%(含)-80%(含)", "71%(含)-80%(含)"));
        mData.add(new BeanSelect(false, "81%(含)-90%(含)", "81%(含)-90%(含)"));
        mData.add(new BeanSelect(false, "91%(含)-95%(含)", "91%(含)-95%(含)"));
        mData.add(new BeanSelect(false, "95%以上", "95%以上"));
        return mData;
    }

    public static List<BeanSelect> getDataSex() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "男", "101"));
        mData.add(new BeanSelect(false, "女", "102"));
        mData.add(new BeanSelect(false, "童装", "103"));
        return mData;
    }

    public static List<BeanSelect> getDataAttribute(String id) {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "上衣", "121"));
        mData.add(new BeanSelect(false, "裤子", "122"));
        mData.add(new BeanSelect(false, "鞋", "123"));
        mData.add(new BeanSelect(false, "箱包", "124"));
        switch (id) {
            /*男*/
            case "101":
                break;
            /*女*/
            case "102":
                mData.add(new BeanSelect(false, "裙子", "125"));
                break;
            /*童装*/
            case "103":
                mData.add(new BeanSelect(false, "裙子", "125"));
                break;
            default:
                break;
        }
        mData.add(new BeanSelect(false, "家居服", "126"));
        mData.add(new BeanSelect(false, "配饰", "128"));
        return mData;
    }

    public static List<BeanSelect> getDataKind(String sex, String id) {
        List<BeanSelect> mData = new ArrayList<>();
        if ("101".equals(sex)) {
            switch (id) {
               /* * 上衣*/
                case "121":
                    mData.add(new BeanSelect(false, "西服套装", "141"));
                    mData.add(new BeanSelect(false, "西服外套", "142"));
                    mData.add(new BeanSelect(false, "休闲西服", "143"));
                    mData.add(new BeanSelect(false, "卫衣及帽衫", "144"));
                    mData.add(new BeanSelect(false, "休闲外套", "145"));
                    mData.add(new BeanSelect(false, "正装衬衫", "146"));
                    mData.add(new BeanSelect(false, "休闲衬衫", "147"));
                    mData.add(new BeanSelect(false, "背心马甲", "151"));
                    mData.add(new BeanSelect(false, "针织毛衣", "152"));
                    mData.add(new BeanSelect(false, "大衣", "153"));
                    mData.add(new BeanSelect(false, "风衣", "154"));
                    mData.add(new BeanSelect(false, "防寒服", "155"));
                    mData.add(new BeanSelect(false, "运动衣", "160"));
                    mData.add(new BeanSelect(false, "T恤&POLO", "159"));
                    mData.add(new BeanSelect(false, "皮衣", "162"));
                    mData.add(new BeanSelect(false, "皮草", "163"));
                    mData.add(new BeanSelect(false, "民族服装", "164"));
                    mData.add(new BeanSelect(false, "制服", "165"));
                    mData.add(new BeanSelect(false, "套装", "166"));
                    mData.add(new BeanSelect(false, "其他", "161"));
                    break;
                /*
                * 裤子*/
                case "122":
                    mData.add(new BeanSelect(false, "正装裤", "171"));
                    mData.add(new BeanSelect(false, "商务休闲", "172"));
                    mData.add(new BeanSelect(false, "休闲裤", "173"));
                    mData.add(new BeanSelect(false, "牛仔裤", "174"));
                    mData.add(new BeanSelect(false, "运动裤", "175"));
                    mData.add(new BeanSelect(false, "短裤", "177"));
                    mData.add(new BeanSelect(false, "其他", "178"));
                    break;
                /*鞋子*/
                case "123":
                    mData.add(new BeanSelect(false, "正装鞋", "191"));
                    mData.add(new BeanSelect(false, "休闲鞋", "192"));
                    mData.add(new BeanSelect(false, "平底鞋", "193"));
                    mData.add(new BeanSelect(false, "靴子", "196"));
                    mData.add(new BeanSelect(false, "运动鞋", "197"));
                    mData.add(new BeanSelect(false, "凉鞋", "199"));
                    mData.add(new BeanSelect(false, "其他", "198"));
                    break;
                /*箱包*/
                case "124":
                    mData.add(new BeanSelect(false, "公文包", "211"));
                    mData.add(new BeanSelect(false, "手拿包", "220"));
                    mData.add(new BeanSelect(false, "挎包", "213"));
                    mData.add(new BeanSelect(false, "手拎包", "214"));
                    mData.add(new BeanSelect(false, "背包", "215"));
                    mData.add(new BeanSelect(false, "腰包", "216"));
                    mData.add(new BeanSelect(false, "旅行包", "217"));
                    mData.add(new BeanSelect(false, "旅行箱", "221"));
                    mData.add(new BeanSelect(false, "其他", "219"));
                    break;
                /*裙子*/
                case "125":

                    break;
                /*家居服*/
                case "126":
                    mData.add(new BeanSelect(false, "家居服", "371"));
                    mData.add(new BeanSelect(false, "内衣", "372"));
                    mData.add(new BeanSelect(false, "保暖内衣", "373"));
                    mData.add(new BeanSelect(false, "其他", "374"));
                    break;
                /*饰品*/
                case "127":
                    mData.add(new BeanSelect(false, "围巾", "413"));
                    mData.add(new BeanSelect(false, "帽子", "414"));
                    mData.add(new BeanSelect(false, "领带", "412"));
                    mData.add(new BeanSelect(false, "腰带", "415"));
                    mData.add(new BeanSelect(false, "手表", "416"));
                    mData.add(new BeanSelect(false, "眼镜", "419"));
                    mData.add(new BeanSelect(false, "其他", "418"));
                    break;
                default:
                    break;
            }
        } else if ("102".equals(sex)) {
            switch (id) {
               /* * 上衣*/
                case "121":
                    mData.add(new BeanSelect(false, "西服外套", "142"));
                    mData.add(new BeanSelect(false, "休闲西服", "143"));
                    mData.add(new BeanSelect(false, "休闲外套", "145"));
                    mData.add(new BeanSelect(false, "卫衣及帽衫", "144"));
                    mData.add(new BeanSelect(false, "正装衬衫", "146"));
                    mData.add(new BeanSelect(false, "休闲衬衫", "147"));
                    mData.add(new BeanSelect(false, "女士上衣", "148"));
                    mData.add(new BeanSelect(false, "开襟外搭", "149"));
                    mData.add(new BeanSelect(false, "内搭", "150"));
                    mData.add(new BeanSelect(false, "背心马甲", "151"));
                    mData.add(new BeanSelect(false, "针织毛衣", "152"));
                    mData.add(new BeanSelect(false, "大衣", "153"));
                    mData.add(new BeanSelect(false, "风衣", "154"));
                    mData.add(new BeanSelect(false, "防寒服", "155"));
                    mData.add(new BeanSelect(false, "午服", "156"));
                    mData.add(new BeanSelect(false, "时装", "157"));
                    mData.add(new BeanSelect(false, "运动衣", "160"));
                    mData.add(new BeanSelect(false, "T恤", "167"));
                    mData.add(new BeanSelect(false, "皮衣", "162"));
                    mData.add(new BeanSelect(false, "皮草", "163"));
                    mData.add(new BeanSelect(false, "套装", "166"));
                    mData.add(new BeanSelect(false, "打底衫", "168"));
                    mData.add(new BeanSelect(false, "民族服装", "164"));
                    mData.add(new BeanSelect(false, "其他", "161"));
                    break;
                /*
                * 裤子*/
                case "122":
                    mData.add(new BeanSelect(false, "正装裤", "171"));
                    mData.add(new BeanSelect(false, "商务休闲", "172"));
                    mData.add(new BeanSelect(false, "休闲裤", "173"));
                    mData.add(new BeanSelect(false, "牛仔裤", "174"));
                    mData.add(new BeanSelect(false, "运动裤", "175"));
                    mData.add(new BeanSelect(false, "短裤", "177"));
                    mData.add(new BeanSelect(false, "连身裤", "179"));
                    mData.add(new BeanSelect(false, "其他", "178"));
                    break;
                /*鞋子*/
                case "123":
                    mData.add(new BeanSelect(false, "正装鞋", "191"));
                    mData.add(new BeanSelect(false, "休闲鞋", "192"));
                    mData.add(new BeanSelect(false, "平底鞋", "193"));
                    mData.add(new BeanSelect(false, "中跟鞋", "194"));
                    mData.add(new BeanSelect(false, "高跟鞋", "195"));
                    mData.add(new BeanSelect(false, "靴子", "196"));
                    mData.add(new BeanSelect(false, "运动鞋", "197"));
                    mData.add(new BeanSelect(false, "低跟鞋", "200"));
                    mData.add(new BeanSelect(false, "凉鞋", "199"));
                    mData.add(new BeanSelect(false, "其他", "198"));
                    break;
                /*箱包*/
                case "124":
                    mData.add(new BeanSelect(false, "公文包", "211"));
                    mData.add(new BeanSelect(false, "手包", "212"));
                    mData.add(new BeanSelect(false, "挎包", "213"));
                    mData.add(new BeanSelect(false, "手拎包", "214"));
                    mData.add(new BeanSelect(false, "背包", "215"));
                    mData.add(new BeanSelect(false, "腰包", "216"));
                    mData.add(new BeanSelect(false, "旅行包", "217"));
                    mData.add(new BeanSelect(false, "旅行箱", "221"));
                    mData.add(new BeanSelect(false, "其他", "219"));
                    break;
                /*裙子*/
                case "125":
                    mData.add(new BeanSelect(false, "半身裙", "231"));
                    mData.add(new BeanSelect(false, "连衣裙", "232"));
                    mData.add(new BeanSelect(false, "旗袍裙", "233"));
                    mData.add(new BeanSelect(false, "大礼服", "234"));
                    mData.add(new BeanSelect(false, "小礼服", "235"));
                    mData.add(new BeanSelect(false, "午服裙", "236"));
                    mData.add(new BeanSelect(false, "时装裙", "237"));
                    mData.add(new BeanSelect(false, "套裙", "239"));
                    mData.add(new BeanSelect(false, "婚纱", "240"));
                    mData.add(new BeanSelect(false, "其他", "238"));
                    break;
                /*家居服*/
                case "126":
                    mData.add(new BeanSelect(false, "家居服", "371"));
                    mData.add(new BeanSelect(false, "内衣", "372"));
                    mData.add(new BeanSelect(false, "保暖内衣", "373"));
                    mData.add(new BeanSelect(false, "其他", "374"));
                    break;
                /*饰品*/
                case "127":

                    mData.add(new BeanSelect(false, "围巾", "413"));
                    mData.add(new BeanSelect(false, "帽子", "414"));
                    mData.add(new BeanSelect(false, "腰带", "415"));
                    mData.add(new BeanSelect(false, "手表", "416"));
                    mData.add(new BeanSelect(false, "首饰", "417"));
                    mData.add(new BeanSelect(false, "眼镜", "419"));
                    mData.add(new BeanSelect(false, "其他", "418"));
                    break;
                default:
                    break;
            }
        } else {
            switch (id) {
               /* * 上衣*/
                case "121":
                    mData.add(new BeanSelect(false, "西服外套", "142"));
                    mData.add(new BeanSelect(false, "休闲西服", "143"));
                    mData.add(new BeanSelect(false, "休闲外套", "145"));
                    mData.add(new BeanSelect(false, "卫衣及帽衫", "144"));
                    mData.add(new BeanSelect(false, "正装衬衫", "146"));
                    mData.add(new BeanSelect(false, "休闲衬衫", "147"));
                    mData.add(new BeanSelect(false, "女士上衣", "148"));
                    mData.add(new BeanSelect(false, "开襟外搭", "149"));
                    mData.add(new BeanSelect(false, "内搭", "150"));
                    mData.add(new BeanSelect(false, "背心马甲", "151"));
                    mData.add(new BeanSelect(false, "针织毛衣", "152"));
                    mData.add(new BeanSelect(false, "大衣", "153"));
                    mData.add(new BeanSelect(false, "风衣", "154"));
                    mData.add(new BeanSelect(false, "防寒服", "155"));
                    mData.add(new BeanSelect(false, "午服", "156"));
                    mData.add(new BeanSelect(false, "时装", "157"));
                    mData.add(new BeanSelect(false, "运动衣", "160"));
                    mData.add(new BeanSelect(false, "T恤", "167"));
                    mData.add(new BeanSelect(false, "皮衣", "162"));
                    mData.add(new BeanSelect(false, "皮草", "163"));
                    mData.add(new BeanSelect(false, "套装", "166"));
                    mData.add(new BeanSelect(false, "打底衫", "168"));
                    mData.add(new BeanSelect(false, "民族服装", "164"));
                    mData.add(new BeanSelect(false, "其他", "161"));
                    break;
                /*
                * 裤子*/
                case "122":
                    mData.add(new BeanSelect(false, "正装裤", "171"));
                    mData.add(new BeanSelect(false, "商务休闲", "172"));
                    mData.add(new BeanSelect(false, "休闲裤", "173"));
                    mData.add(new BeanSelect(false, "牛仔裤", "174"));
                    mData.add(new BeanSelect(false, "运动裤", "175"));
                    mData.add(new BeanSelect(false, "短裤", "177"));
                    mData.add(new BeanSelect(false, "连身裤", "179"));
                    mData.add(new BeanSelect(false, "其他", "178"));
                    break;
                /*鞋子*/
                case "123":
                    mData.add(new BeanSelect(false, "正装鞋", "191"));
                    mData.add(new BeanSelect(false, "休闲鞋", "192"));
                    mData.add(new BeanSelect(false, "平底鞋", "193"));
                    mData.add(new BeanSelect(false, "中跟鞋", "194"));
                    mData.add(new BeanSelect(false, "高跟鞋", "195"));
                    mData.add(new BeanSelect(false, "靴子", "196"));
                    mData.add(new BeanSelect(false, "运动鞋", "197"));
                    mData.add(new BeanSelect(false, "低跟鞋", "200"));
                    mData.add(new BeanSelect(false, "凉鞋", "199"));
                    mData.add(new BeanSelect(false, "其他", "198"));
                    break;
                /*箱包*/
                case "124":
                    mData.add(new BeanSelect(false, "公文包", "211"));
                    mData.add(new BeanSelect(false, "手包", "212"));
                    mData.add(new BeanSelect(false, "挎包", "213"));
                    mData.add(new BeanSelect(false, "手拎包", "214"));
                    mData.add(new BeanSelect(false, "背包", "215"));
                    mData.add(new BeanSelect(false, "腰包", "216"));
                    mData.add(new BeanSelect(false, "旅行包", "217"));
                    mData.add(new BeanSelect(false, "旅行箱", "221"));
                    mData.add(new BeanSelect(false, "其他", "219"));
                    break;
                /*裙子*/
                case "125":
                    mData.add(new BeanSelect(false, "半身裙", "231"));
                    mData.add(new BeanSelect(false, "连衣裙", "232"));
                    mData.add(new BeanSelect(false, "旗袍裙", "233"));
                    mData.add(new BeanSelect(false, "大礼服", "234"));
                    mData.add(new BeanSelect(false, "小礼服", "235"));
                    mData.add(new BeanSelect(false, "午服裙", "236"));
                    mData.add(new BeanSelect(false, "时装裙", "237"));
                    mData.add(new BeanSelect(false, "套裙", "239"));
                    mData.add(new BeanSelect(false, "婚纱", "240"));
                    mData.add(new BeanSelect(false, "其他", "238"));
                    break;
                /*家居服*/
                case "126":
                    mData.add(new BeanSelect(false, "家居服", "371"));
                    mData.add(new BeanSelect(false, "内衣", "372"));
                    mData.add(new BeanSelect(false, "保暖内衣", "373"));
                    mData.add(new BeanSelect(false, "其他", "374"));
                    break;
                /*饰品*/
                case "127":
                    mData.add(new BeanSelect(false, "围巾", "413"));
                    mData.add(new BeanSelect(false, "帽子", "414"));
                    mData.add(new BeanSelect(false, "腰带", "415"));
                    mData.add(new BeanSelect(false, "手表", "416"));
                    mData.add(new BeanSelect(false, "首饰", "417"));
                    mData.add(new BeanSelect(false, "眼镜", "419"));
                    mData.add(new BeanSelect(false, "其他", "418"));
                    break;
                default:
                    break;
            }
        }
        return mData;
    }

    public static List<BeanSelect> getDataTuan() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "条纹", "601"));
        mData.add(new BeanSelect(false, "格子", "602"));
        mData.add(new BeanSelect(false, "纯色", "603"));
        mData.add(new BeanSelect(false, "花色", "604"));
        mData.add(new BeanSelect(false, "手绘", "605"));
        mData.add(new BeanSelect(false, "圆点", "606"));
        mData.add(new BeanSelect(false, "字母", "607"));
        mData.add(new BeanSelect(false, "千鸟格", "608"));
        mData.add(new BeanSelect(false, "人物", "609"));
        mData.add(new BeanSelect(false, "碎花", "610"));
        mData.add(new BeanSelect(false, "风景", "611"));
        mData.add(new BeanSelect(false, "抽象图案", "612"));
        mData.add(new BeanSelect(false, "建筑", "613"));
        mData.add(new BeanSelect(false, "斑马纹", "614"));
        mData.add(new BeanSelect(false, "大花", "615"));
        mData.add(new BeanSelect(false, "动物纹", "616"));
        mData.add(new BeanSelect(false, "卡通动漫", "617"));
        mData.add(new BeanSelect(false, "动物图案", "618"));
        mData.add(new BeanSelect(false, "豹纹", "619"));
        mData.add(new BeanSelect(false, "其他", "620"));
        return mData;
    }

    public static List<BeanSelect> getDataCaizhi() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "羊绒", "501"));
        mData.add(new BeanSelect(false, "羊毛", "502"));
        mData.add(new BeanSelect(false, "兔毛", "503"));
        mData.add(new BeanSelect(false, "棉", "504"));
        mData.add(new BeanSelect(false, "麻", "505"));
        mData.add(new BeanSelect(false, "羊皮", "506"));
        mData.add(new BeanSelect(false, "氯纶", "507"));
        mData.add(new BeanSelect(false, "锦纶", "508"));
        mData.add(new BeanSelect(false, "涤纶", "509"));
        mData.add(new BeanSelect(false, "醋纤", "510"));
        mData.add(new BeanSelect(false, "PU", "511"));
        mData.add(new BeanSelect(false, "莫代尔", "512"));
        mData.add(new BeanSelect(false, "LYCRA莱卡", "513"));
        mData.add(new BeanSelect(false, "蚕丝", "514"));
        mData.add(new BeanSelect(false, "维纶", "515"));
        mData.add(new BeanSelect(false, "猪皮", "516"));
        mData.add(new BeanSelect(false, "丙纶", "517"));
        mData.add(new BeanSelect(false, "貂皮", "518"));
        mData.add(new BeanSelect(false, "牛皮", "519"));
        mData.add(new BeanSelect(false, "粘胶", "520"));
        mData.add(new BeanSelect(false, "腈纶", "521"));
        mData.add(new BeanSelect(false, "鹿皮", "522"));
        mData.add(new BeanSelect(false, "羊驼毛", "523"));
        mData.add(new BeanSelect(false, "貂毛", "524"));
        mData.add(new BeanSelect(false, "狐狸毛", "525"));
        mData.add(new BeanSelect(false, "头层牛皮", "526"));
        mData.add(new BeanSelect(false, "其他", "527"));
        return mData;
    }

    public static List<BeanSelect> getDataYichang() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "短", "801"));
        mData.add(new BeanSelect(false, "常规", "802"));
        mData.add(new BeanSelect(false, "长", "803"));
        mData.add(new BeanSelect(false, "中长", "804"));
        mData.add(new BeanSelect(false, "连体", "805"));
        mData.add(new BeanSelect(false, "不对称衣长", "806"));
        return mData;
    }


    public static List<BeanSelect> getDataColor() {
        List<BeanSelect> mData = new ArrayList<>();
        mData.add(new BeanSelect(false, "黑", "黑"));
        mData.add(new BeanSelect(false, "白", "白"));
        mData.add(new BeanSelect(false, "灰", "灰"));
        mData.add(new BeanSelect(false, "棕", "棕"));
        mData.add(new BeanSelect(false, "红", "红"));
        mData.add(new BeanSelect(false, "黄", "黄"));
        mData.add(new BeanSelect(false, "绿", "绿"));
        mData.add(new BeanSelect(false, "蓝", "蓝"));
        mData.add(new BeanSelect(false, "紫", "紫"));
        mData.add(new BeanSelect(false, "橙", "橙"));
        mData.add(new BeanSelect(false, "粉", "粉"));
        mData.add(new BeanSelect(false, "金属", "金属"));
        mData.add(new BeanSelect(false, "其他", "其他"));
        return mData;
    }


    public static List<BeanSelect> getDataXingHao(String id) {
        List<BeanSelect> mData = new ArrayList<>();
        if ("128".equals(id) || "124".equals(id)) {
            return mData;
        } else if ("123".equals(id)) {
            mData.add(new BeanSelect(false, "35", "35"));
            mData.add(new BeanSelect(false, "36", "36"));
            mData.add(new BeanSelect(false, "37", "37"));
            mData.add(new BeanSelect(false, "38", "38"));
            mData.add(new BeanSelect(false, "39", "39"));
            mData.add(new BeanSelect(false, "40", "40"));
            mData.add(new BeanSelect(false, "41", "41"));
            mData.add(new BeanSelect(false, "42", "42"));
            mData.add(new BeanSelect(false, "43", "43"));
            mData.add(new BeanSelect(false, "44", "44"));
            mData.add(new BeanSelect(false, "45", "45"));
            mData.add(new BeanSelect(false, "46", "46"));
            mData.add(new BeanSelect(false, "47", "47"));
            mData.add(new BeanSelect(false, "48", "48"));
            mData.add(new BeanSelect(false, "49", "49"));
            mData.add(new BeanSelect(false, "50", "50"));
        } else {
            mData.add(new BeanSelect(false, "s", "s"));
            mData.add(new BeanSelect(false, "m", "m"));
            mData.add(new BeanSelect(false, "l", "l"));
            mData.add(new BeanSelect(false, "xl", "xl"));
            mData.add(new BeanSelect(false, "2xl", "2xl"));
            mData.add(new BeanSelect(false, "3xl", "3xl"));
            mData.add(new BeanSelect(false, "4xl", "4xl"));
            mData.add(new BeanSelect(false, "5xl", "5xl"));
            mData.add(new BeanSelect(false, "6xl", "6xl"));
            mData.add(new BeanSelect(false, "7xl", "7xl"));
        }
        return mData;
    }
}
