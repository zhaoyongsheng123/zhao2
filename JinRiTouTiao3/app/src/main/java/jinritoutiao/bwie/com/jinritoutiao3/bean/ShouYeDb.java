package jinritoutiao.bwie.com.jinritoutiao3.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zhao on 2017/3/23.
 */
@Table(name = "ShouYeDb")
public class ShouYeDb {
    @Column(name="id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "source")
    private String Source;
    @Column(name = "like_count")
    private String like_count;
    @Column(name = "topicDesc")
    private String topicDesc;
    @Column(name = "topicImg")
    private String topicImg;
    @Column(name = "url")
    private String url;

}
