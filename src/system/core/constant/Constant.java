package system.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @create 6/13
 */
public class Constant {
    public final static String PRIVILEGE_XZGL = "xzgl";
    public final static String PRIVILEGE_HQFW = "hqfw";
    public final static String PRIVILEGE_ZXXX = "zxxx";
    public final static String PRIVILEGE_NSFW = "nsfw";
    public final static String PRIVILEGE_SPACE = "space";
    public final static String USER = "user";
    public static Map<String, String> PRIVITEGE_MAP;

    static {
        PRIVITEGE_MAP = new HashMap();
        PRIVITEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
        PRIVITEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
        PRIVITEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
        PRIVITEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
        PRIVITEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
    }
}