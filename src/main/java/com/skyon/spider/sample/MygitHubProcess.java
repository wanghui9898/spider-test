package com.skyon.spider.sample;

import com.skyon.spider.pipeline.ConsolePipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 第一个github爬虫项目
 * @author 辉
 *
 */
public class MygitHubProcess implements PageProcessor{
	
	private Site site = Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko)"
			+ "Chrome/31.0.1650.57 Safari/537.36").setSleepTime(100);
	
	
	public void process(Page page) {
		page.putField("title", page.getHtml().xpath("ul[@class='sojob-list']/li/div/div/h3/@title").all());//获取职位
		page.putField("salary", page.getHtml().xpath("ul[@class='sojob-list']/li/div/div[1]/p[1]/span[1]/text(1)").all());//获取薪水
		page.putField("jobAddress", page.getHtml().xpath("ul[@class='sojob-list']/li/div/div[1]/p[1]/span[2]/text(1)").all());//工作地点
		page.putField("publishTime", page.getHtml().xpath("ul[@class='sojob-list']/li/div/div[1]/p[2]/time/text(1)").all());//发布时间
		page.putField("company", page.getHtml().xpath("ul[@class='sojob-list']/li/div/div[2]/p[1]/a/@title").all());//发布公司
	}

	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
		Spider.create(new MygitHubProcess())
		.addUrl("https://www.liepin.com/sojob/?dqs=020&curPage=0")
		.addPipeline(new ConsolePipeline())
		.run();
	}

}
