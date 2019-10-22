package cn.itcast.job.service;

import cn.itcast.job.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {
    /**
     * 保存工作信息
     */
    public void save(JobInfo jobInfo);

    /**
     *根据条件查询工作信息
     */
    public List<JobInfo> fineJobInfo(JobInfo jobInfo);
}
