package system.nsfw.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 信息发布
 *
 * @author Administrator
 * @create 8/3
 */
public class Info implements Serializable {
    private String id;
    /**
     * 信息类型
     */
    private String type;
    /**
     * 来源
     */
    private String source;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 状态
     */
    private String state;
    /**
     * 1为发布
     * 0位停用
     */
    public final static String INFO_STATE_FB = "1";
    public final static String INFO_STATE_TY = "0";
    /**
     * type的map
     */
    public final static String INFO_TYPE_TZGG = "tzgg";
    public final static String INFO_TYPE_ZCSD = "zcsd";
    public final static String INFO_TYPE_NSZD = "nszd";
    public static Map<String, String> TYPE_MAP;

    static {
        TYPE_MAP = new HashMap();
        TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
        TYPE_MAP.put(INFO_TYPE_ZCSD, "政策速递");
        TYPE_MAP.put(INFO_TYPE_NSZD, "纳税指导");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}