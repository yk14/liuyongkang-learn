package com.taotao.uitl;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.*;
import java.io.*;
public class Generator {

	public static void main(String[] args) throws Exception {
		  //ͨ���������� ��Ӧ�� mapper ��xml���ļ�
		 List<String> warnings = new ArrayList<String>();
		 boolean overwrite =true;
		 File configFile = new File("src/main/java/generator.xml");
		 ConfigurationParser cp = new ConfigurationParser(warnings);
		 Configuration config= cp.parseConfiguration(configFile);
		 
		 // �Ƿ����� 
		 DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		 // �����ഴ�� 
		 MyBatisGenerator mybatisGenerator = new MyBatisGenerator(config, callback, warnings);
		 
		 mybatisGenerator.generate(null);
				 
				 System.out.println("生成成功");

	}

}
