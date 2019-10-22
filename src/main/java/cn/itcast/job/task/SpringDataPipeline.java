package cn.itcast.job.task;

import cn.itcast.job.pojo.JobInfo;
import cn.itcast.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
//Component  添加这个为创建实例
@Component
public class SpringDataPipeline  implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    // 处理最后的持久化操作，或者业务操作  实现Pipeline即可   处理结果集
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情
        JobInfo jobInfo =resultItems.get("jobInfo");
        if (jobInfo !=null){
                this.jobInfoService.save(jobInfo);
        }

        //判断数据是否不为空
    }
}
