package cn.itcast.job.dao;

import cn.itcast.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
//其中 T 是数据库实体类(JobInfo)，ID 是数据库实体类的主键
public interface JobInfoDao  extends JpaRepository<JobInfo,Long> {
}
