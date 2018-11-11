import com.wenjun.pageprocess.BaiduTiebaPageProcess;
import com.wenjun.pipeline.BaiduTiebaPipeline;
import us.codecraft.webmagic.Spider;

/**
 * @Author: ruanwenjun
 * @CreateDate: 2018/11/9 18:33
 */
public class CrawlMain {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        Spider.create(new BaiduTiebaPageProcess())
                .addPipeline(new BaiduTiebaPipeline())
                .addUrl("http://tieba.baidu.com/f?ie=utf-8&kw=%E6%B9%96%E5%8C%97%E5%A4%A7%E5%AD%A6&fr=search")
                .thread(Runtime.getRuntime().availableProcessors())
                .start();

    }

}
