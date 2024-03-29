package cn.itcast.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

public class Proxytest implements PageProcessor {
    @Scheduled(fixedDelay = 1000)
    public  void Process(){
        //创建一个下载器Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器信息
    httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("124.156.117.78",80)));
        Spider.create(new Proxytest())
                .addUrl("http://ip.chinaz.com/getip.aspx")
                .setDownloader(httpClientDownloader)//设置下载器
                .run();
    }

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
    }
    private Site site =Site.me();

    @Override
    public Site getSite() {
        return site;
    }
}
