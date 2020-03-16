/*package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	@Test
	public void testPageHelper() throws Exception {
		//1�����mapper�������
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//2�����÷�ҳ
		PageHelper.startPage(1, 30);
		//3��ִ�в�ѯ
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//4��ȡ��ҳ����
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("total:" + total);
		int pages = pageInfo.getPages();
		System.out.println("pages:" + pages);
		int pageSize = pageInfo.getPageSize();
		System.out.println("pageSize:" + pageSize);
		
	}

}
*/