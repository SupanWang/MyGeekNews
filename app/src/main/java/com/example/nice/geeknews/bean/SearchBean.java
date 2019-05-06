package com.example.nice.geeknews.bean;

import java.util.List;

public class SearchBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-04-17","title":"我的优雅，是胖子给的","description":"磁器","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/U6ekBc7HU5fUFU1UEZoDoJNXmvFjFncsHEPahd0t7qRoAUSnlhZP9oSDTCIXCjdABGaqafhTvwUatruYX0ZAYQ/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1555489810&ver=1551&signature=F7Ce92YSY4Wn85OQbi8qlPy-b4P4uetxhkQROCdRwViKIB4XLN8GK0QLLc6tM6udhU*QWWieDdHKawjSCGd6KspXeWOa*IwlqBvLwqlSqxPJnEDJ9sBBOC8521z1NXZ7&new=1"},{"ctime":"2019-04-08","title":"我的幸运，是努力的结果！","description":"冯东阳","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/gvvyO8rHc8cP6iaWNCuKvVRibica6WzydMAP7waMuJNJ2071TddG0Kvq5V1j3xPC6GDZgxLBkfHHF28A3EOaH99AQ/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1554807613&ver=1536&signature=VDpPLhZ4ABfZXONtMKNHOykuXrjkxaR3j8Rcin4w*i*4p9t3ak*ISTmyQroKtV0doizLNG78rPohxMVDO5tmBPCTu*huPpnxDNB2T0cIJG030*znIqTn4DWDkUixFBsR&new=1"},{"ctime":"2019-04-08","title":"我的一些包包，你们绝对意想不到！","description":"牛奶和爱丽丝","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/AWFOpIncNo5F3XQecbdBiao6T8kcWapcCf675sDwyIL6k53xx87Fia1rxUddLtmnFkRpZWLCLwfw2mNeQzjwibfsg/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1554791408&ver=1535&signature=PIs61ub*yHy2BIzE8XDOpTjllC4Lxwf1BSov*2pDmxb0rpilHR8b3yW1*AxTT1fp4ScbcGQAJMv0WrIkZPcs7nuqyuBNVjQmCAFN0brlJZkq-UdloKe8zOZ88yCakzSy&new=1"},{"ctime":"2019-03-15","title":"我的天！湖人曾经考虑过交易詹姆斯！湖人最大的问题并非伤病？","description":"NBA篮球热点","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/8DFkoD3CxDDaYNn4icz4wmyjHE1YwG92ibevoAE6b4YYMHB3UfEGDRQjRrnbTTDnamJfSQN68z1QibywhF9ydLfCQ/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1552656618&ver=1486&signature=IPn5HEFCzJcmX3ITsIuQjYYJtJClDxgRvimNkbs9F1PiMc7BEYk-HnCWX-cRqBDT1rVAuWc0avuamw9dU0hGDeUWAj48niC0iUbFZTiG8luqLXFODXRW5F2G8faa61dJ&new=1"},{"ctime":"2019-02-12","title":"我的梦想就是不工作，有什么错？","description":"新周刊","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/r7l7L28KwuX4RwzBb8mUdH7YE0qawMbz26tc5athACHJYjAiaZcuovQmLMJpbJl7W1FLKMO7mfib4vicwicY0lpITw/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1549938613&ver=1423&signature=-ArMkz0wPKPtrt5lMmQf-rPM59YtaUUiC3beMDJy3aGRgZ-CgLCvIoKVGjJaeqlaSdZUQS0SwjbnPNnUJwyC5eNIDdiXvK5vK*RIzPXkwAPyamd52UNhoOpeS4gseCUX&new=1"},{"ctime":"2019-02-12","title":"我的路由器号称大功率穿墙王，咋才走了几步就没信号了呢？","description":"我是科学家iScientist","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/N2EssTUQcOMxdhantG3eU3m8NbySjdO0vicXwmrsClJ1Y3OTeVJZEAUJaBicujsgCEl5ruC1BcMkwfXTYibvskhxQ/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?__biz=MzI0MzA3OTI1MA==&mid=2651602475&idx=1&sn=aa3d7e1d874b3d373b036ddff5379963&chksm=f28a1f16c5fd9600a997d377cdd798e4fc7e412e7ee9240f4300eb74d3bacc5538072ee9c744&scene=21&token=2072883932&lang=zh_CN#wechat_redirect"},{"ctime":"2019-01-29","title":"我的贪心就一点点，都装在这一丢丢的小包里了！","description":"时尚芭莎","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/7Jn3GYPMVgDQ6dicwlMb3pqTPUcboc3UFmVbnXVOEXYwP1vVRrVsicqicAhmlsnbBgA3YJLSt6rpJsaJCddmYrDmw/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1548748810&ver=1395&signature=ip9FyKHdsdgu-Jwdoxq8e4SAB8yq*nVDLOpj1sYxxaDbsBKgRIQMW2ao4LltYbcD0612JFr133L4ltbMP38bl5BnBgEi1Gxcbxtu8AyPSGNhQ6XcILqEdMCe*XZf6s7j&new=1"},{"ctime":"2019-01-27","title":"我的国，您竟然这么美！","description":"摄影报","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/VK6gnoF57oBMCaqCfytC91aCjzzto4fbXxbiaduu5VDkxKhKxSZP6MDeUe0Tkr87qvUdY3BVE60nso5okh4AuWw/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1548576013&ver=1391&signature=gdPGRNjJpG5AkfbaL-Zk2kDBs0VUwlsa90GvAa5AFm6Sl4-6uxzxnVniwt9G7ME1LiwerVox4BCsxyoSGjyQ6vk-ewYdj-RU0bzOyHJ*5S-59-p9sFu5eyNX2A1vkf35&new=1"},{"ctime":"2019-01-26","title":"我的几乎所有员工都在密谋跳槽 | 一位创业CEO的口述","description":"正和岛","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/Ftnt4m7MlghCibiaceEYibE4MK5pUMHB7uI6LZXOsOiaqYkqiahgI3aoicyznjQ3eibicf7PcDguV4h2ADGFBDYZkQcWaA/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1548496813&ver=1390&signature=GBLRWWvcxqs8gDQz-0p3CbNvGzUm6SEdWgEPHHmQBaQ54waG*2S-xVf4cAE2kH3uBl22hUDE9z6ffVWI4yRly9BFv*DcLcM2jzGlhJ2HUcMx9JwitAqxZ6azfNzcWxLu&new=1"},{"ctime":"2019-01-22","title":"我的队长","description":"天下足球","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/sAmI87QVQBSJdibS7g2MZmmTbdFrQGqI8GWLuMkNTbNaiaeMGOsESILLhiaSzuwMibdToK4BUyZbhXJ66VsiattCbiaA/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1548126023&ver=1381&signature=2ium0QaXlwY5N6PGbChxv0eTofFy41VmwcTc50bHt7tqji1JQmJCZqZTceyUO9k8BttvhC9TUOLDxXkPfHZDtNaq11xAmOGTXECMRSCJqy9hEtufpWPa2saeRbb9BH*s&new=1"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2019-04-17
         * title : 我的优雅，是胖子给的
         * description : 磁器
         * picUrl : http://mmbiz.qpic.cn/mmbiz_jpg/U6ekBc7HU5fUFU1UEZoDoJNXmvFjFncsHEPahd0t7qRoAUSnlhZP9oSDTCIXCjdABGaqafhTvwUatruYX0ZAYQ/0?wx_fmt=jpeg
         * url : https://mp.weixin.qq.com/s?src=11&timestamp=1555489810&ver=1551&signature=F7Ce92YSY4Wn85OQbi8qlPy-b4P4uetxhkQROCdRwViKIB4XLN8GK0QLLc6tM6udhU*QWWieDdHKawjSCGd6KspXeWOa*IwlqBvLwqlSqxPJnEDJ9sBBOC8521z1NXZ7&new=1
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
