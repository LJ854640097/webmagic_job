package cn.itcast.job.controller;


import cn.itcast.job.task.JobProcessor;
import cn.itcast.job.task.SpringDataPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@RequestMapping(value= {"/job"})
@Controller
public class JobController {


	@Autowired
	private SpringDataPipeline springDataPipeline;

	//@ResponseBody
	@RequestMapping(value= {"/action"})
	public void action() {
		Spider.create(new JobProcessor())
				.addUrl(JobProcessor.url)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
				.thread(10)
				.addPipeline(springDataPipeline)
				.runAsync();//异步启动   run（）是同步
	}
}
