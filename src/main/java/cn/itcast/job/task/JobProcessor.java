package cn.itcast.job.task;

import cn.itcast.job.pojo.JobInfo;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

//@Component
public class JobProcessor implements PageProcessor {

    public static   String url="https://search.51job.com/list/000000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=01&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=22&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=%E5%81%9A%E4%B8%80%E4%BC%91%E4%B8%80";
    @Override
    public void process(Page page) {
            // 解析页面获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        // 判断获取到的集合是否为空
        if (list.size() == 0){
            //如果为空表示招聘页 解析页面 获取招聘详情信息保存数据
            this.saveJobInfo(page);
        }else {
            //不为空 解析url放到任务中
            for (Selectable selectable:list){
                //url
              String jobInfourl = selectable.links().toString();
              //把获取到的url放到任务列表中
                page.addTargetRequest(jobInfourl);
            }
            //获取下一页的url
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
           //将下一页的url放到任务队列中
            page.addTargetRequest(bkUrl);
        }
        String html = page.getHtml().toString();


    }
    //解析获取招聘详情信息保存数据
    private void saveJobInfo(Page page) {
        //创建招聘详情对象
        JobInfo jobInfo = new JobInfo();

        //解析页面
        Html html = page.getHtml();

        //获取数据，封装到对象中
        jobInfo.setCompanyName(html.css("div.cn p.cname a","text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text() );
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());//因为就一个 所以不用写是第几个
        jobInfo.setJobName(html.css("div.cn h1","text").toString());
        jobInfo.setJobAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());
        //获取发布时间 需要用到正则
//          String time= Jsoup.parse(html.css("div.cn p.ltype").toString()).text();
//          jobInfo.setTime(time.substring(0,time.length()-2));
          //结果保存
        page.putField("jobInfo",jobInfo);
//        jobInfo.setId();
//        jobInfo.setTime();



    }

    private Site site = Site.me()
            .setCharset("gbk")
            .setTimeOut(10000)//设置超时时间 单位是ms毫秒
            .setRetrySleepTime(30000) //设置重试的间隔时间 1000为默认值
            .setRetrySleepTime(3)
            ;
    @Override
    public Site getSite() {
        return site;
    }



}
