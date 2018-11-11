package com.wenjun.pageprocess;

import com.wenjun.entity.CSUEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;


public class BaiduTiebaPageProcess implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(BaiduTiebaPageProcess.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {

        List<String> titles = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[2]/div[1]/div[1]/a/text()").all();
        List<String> comments = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[1]/span/text()").all();
        List<String> authors = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[2]/div[1]/div[2]/span[1]/span[1]/a/text()").all();

        logger.info("this page have {} title", titles.size());
        List<CSUEntity> list = new ArrayList<CSUEntity>(titles.size());
        for (int i = 0; i < titles.size(); i++) {
            try {
                CSUEntity csuEntity = new CSUEntity();
                csuEntity.setTitle(titles.get(i));
                csuEntity.setCommentNum(Integer.parseInt(comments.get(i)));
                csuEntity.setAuthor(authors.get(i));
                list.add(csuEntity);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        page.putField("list", list);
        String nextUrl = page.getHtml().xpath("//div[@class='thread_list_bottom clearfix']/div[1]/a[@class='next pagination-item']/@href").toString().substring(18);
        logger.info("next url is {}", nextUrl);
        page.addTargetRequest(nextUrl);
    }

    public Site getSite() {
        return site;
    }
}
