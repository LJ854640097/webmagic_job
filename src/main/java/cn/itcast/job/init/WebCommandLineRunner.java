package cn.itcast.job.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebCommandLineRunner implements CommandLineRunner {



	@Override  
	public void run(String... args) throws Exception {  
		//app初始化操作
		
		System.out.println("初始化");

		
	}


//	@Autowired
//	private SpringDataPipeline springDataPipeline;
//
//	//initialDelay  当任务启动时候 等多久执行方法
//	//fixedDelay    m每隔10秒 执行方法
//	@Scheduled(initialDelay = 1000,fixedDelay = 100*1000)
//	public  void process(){
//		Spider.create(new JobProcessor())
//				.addUrl(JobProcessor.url)
//				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
//				.thread(10)
//				.addPipeline(springDataPipeline)
//				.run();
//	}
}
