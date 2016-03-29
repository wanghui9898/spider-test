package com.skyon.spider.pipeline;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.skyon.spider.db.util.DBUtil;
import com.skyon.spider.model.JobInfo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class ConsolePipeline implements Pipeline{

	public void process(ResultItems resultItems, Task task) {
		/*System.out.println("get page: " + resultItems.getRequest().getUrl());*/
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
		Map<String, Object> fields = resultItems.getAll();
		List<JobInfo> jobs = new ArrayList<JobInfo>();//装招聘信息
		for(Map.Entry<String, Object> entry : fields.entrySet()) {
			List<String> value = (List<String>)entry.getValue();
			if("title".equals(entry.getKey())){
				for(String title:value){
					JobInfo job = new JobInfo();
					job.setTitle(title);
					jobs.add(job);
				}
			}
			if("salary".equals(entry.getKey())){
				if(null != jobs && jobs.size() > 0){
					for(int i=0;i<value.size();i++){
						jobs.get(i).setSalary(value.get(i));
					}
				}
			}
			if("jobAddress".equals(entry.getKey())){
				if(null != jobs && jobs.size() > 0){
					for(int i=0;i<value.size();i++){
						jobs.get(i).setCompany_address(value.get(i));
					}
				}
			}
			if("publishTime".equals(entry.getKey())){
				if(null != jobs && jobs.size() > 0){
					for(int i=0;i<value.size();i++){
						jobs.get(i).setPublishTime(value.get(i));
					}
				}
			}
			if("company".equals(entry.getKey())){
				if(null != jobs && jobs.size() > 0){
					for(int i=0;i<value.size();i++){
						jobs.get(i).setCompany(value.get(i));
					}
				}
			}
		}
		//获取数据库连接
		try{
			Connection conn = DBUtil.getDBConnection();
			conn.setAutoCommit(false); 
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,     
                    ResultSet.CONCUR_READ_ONLY);
			for(int x = 0; x < jobs.size(); x++){ 
				String title = jobs.get(x).getTitle();
				title = "'"+title+"'";
				String salary = jobs.get(x).getSalary();
				salary = "'"+salary+"'";
				String company = jobs.get(x).getCompany();
				company = "'"+company+"'";
				String publish_time = jobs.get(x).getPublishTime();
				publish_time = "'"+publish_time+"'";
				String company_address = jobs.get(x).getCompany_address();
				company_address = "'"+company_address+"'";
				String sql = "INSERT INTO JobInfo(title,salary,company,"
						+ "publish_time,company_address,create_time,version)"
				   		+ "VALUES("+title+","
						           +salary+","
						           +company+","
						           +publish_time+","
						           +company_address+","
						           +"now(),"
						           + "'1')";
//				System.out.println(sql);
				stmt.execute(sql);     
			}     
			conn.commit();  
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
