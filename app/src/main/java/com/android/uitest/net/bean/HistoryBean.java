package com.android.uitest.net.bean;

import java.util.List;

/**
 * Created by 张海洋 on 2018-07-06.
 */
public class HistoryBean extends BaseBeanNew {

    public List<ItemData> result;

    public class ItemData {
        /**
         * {
         "_id":"18141001",
         "title":"反法联盟各参加国在奥地利首都维也纳召开会议",
         "pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201110/2/1F81726127.jpg",
         "year":1814,
         "month":10,
         "day":1,
         "des":"在204年前的今天，1814年10月1日 (农历八月十八)，反法联盟各参加国在奥地利首都维也纳召开会议。",
         "lunar":"甲戌年八月十八"
         }
         */

        public String _id;
        public String title;
        public String pic;
        public String year;
        public String month;
        public String day;
        public String des;
        public String lunar;
    }
}
