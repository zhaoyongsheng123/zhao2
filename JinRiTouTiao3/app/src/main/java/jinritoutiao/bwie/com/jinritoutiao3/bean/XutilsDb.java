package jinritoutiao.bwie.com.jinritoutiao3.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zhao on 2017/3/23.
 */
@Table(name = "shoucang1502L")
public class XutilsDb {
    @Column(name="id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "ptime")
    private String ptime;
    @Column(name = "topicName")
    private String topicName;
    @Column(name = "topicDesc")
    private String topicDesc;
    @Column(name = "topicImg")
    private String topicImg;
    @Column(name = "mp4_url")
    private String mp4_url;

    @Override
    public String toString() {
        return     topicName  +"";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public XutilsDb() {
    }

    public XutilsDb(String title, String ptime, String topicName, String topicDesc, String topicImg, String mp4_url) {
        this.title = title;
        this.ptime = ptime;
        this.topicName = topicName;
        this.topicDesc = topicDesc;
        this.topicImg = topicImg;
        this.mp4_url = mp4_url;
    }
}
