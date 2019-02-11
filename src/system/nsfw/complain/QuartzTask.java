package system.nsfw.complain;


import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @create 8/9
 */
public class QuartzTask {

    public void runMyTask() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(System.currentTimeMillis()));
    }
}