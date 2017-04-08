package jinritoutiao.bwie.com.jinritoutiao3.bean;

import java.util.List;

/**
 * Created by zhao on 2017/3/20.
 */
public class YuangGuangBean {

    private List<视频Bean> 视频;

    public List<视频Bean> get视频() {
        return 视频;
    }

    public void set视频(List<视频Bean> 视频) {
        this.视频 = 视频;
    }

    public static class 视频Bean {
        /**
         * cover : http://vimg3.ws.126.net/image/snapshot/2017/3/8/H/VCF29D98H.jpg
         * description : 小智遇到了新的劲敌真司
         * length : 151
         * m3u8_url : http://flv2.bn.netease.com/videolib3/1703/20/DasvZ8507/SD/movie_index.m3u8
         * mp4_url : http://flv2.bn.netease.com/videolib3/1703/20/DasvZ8507/SD/DasvZ8507-mobile.mp4
         * playCount : 229
         * playersize : 0
         * program : base
         * prompt : 成功为您推荐3条新视频
         * ptime : 2017-03-20 16:30:07.0
         * replyBoard : video_bbs
         * replyCount : 0
         * replyid : CF283MT6008535RB
         * sectiontitle : http://vimg2.ws.126.net/image/snapshot/2016/9/7/E/VBUVE3N7E.jpg
         * title : 《宠物小精灵》小智遇到了新的劲敌真司
         * topicDesc : 中国传统收藏文化交流，为广大的中国收藏爱好者，提供文化知识交流。
         * topicImg : http://vimg2.ws.126.net/image/snapshot/2016/9/7/E/VBUVE3N7E.jpg
         * topicName : 桃子
         * topicSid : VBUVE3N7A
         * vid : VCF283MT6
         * videoTopic : {"alias":"桃子新闻资讯","ename":"T1433150103695","tid":"T1433150103695","tname":"桃子","topic_icons":"http://dingyue.nosdn.127.net/dFsmT3rtuCyd9qBHWZOnGd=Kd2sdK9okQ0xvkUc3mVoHy1477454880453.jpg"}
         * videosource : 新媒体
         */

        private String cover;
        private String description;
        private int length;
        private String m3u8_url;
        private String mp4_url;
        private int playCount;
        private int playersize;
        private String program;
        private String prompt;
        private String ptime;
        private String replyBoard;
        private int replyCount;
        private String replyid;
        private String sectiontitle;
        private String title;
        private String topicDesc;
        private String topicImg;
        private String topicName;
        private String topicSid;
        private String vid;
        private VideoTopicBean videoTopic;
        private String videosource;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public VideoTopicBean getVideoTopic() {
            return videoTopic;
        }

        public void setVideoTopic(VideoTopicBean videoTopic) {
            this.videoTopic = videoTopic;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public static class VideoTopicBean {
            /**
             * alias : 桃子新闻资讯
             * ename : T1433150103695
             * tid : T1433150103695
             * tname : 桃子
             * topic_icons : http://dingyue.nosdn.127.net/dFsmT3rtuCyd9qBHWZOnGd=Kd2sdK9okQ0xvkUc3mVoHy1477454880453.jpg
             */

            private String alias;
            private String ename;
            private String tid;
            private String tname;
            private String topic_icons;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public String getTopic_icons() {
                return topic_icons;
            }

            public void setTopic_icons(String topic_icons) {
                this.topic_icons = topic_icons;
            }
        }
    }
}
