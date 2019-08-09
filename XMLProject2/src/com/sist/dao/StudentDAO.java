package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;

import java.io.*;

public class StudentDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			//xml을 읽어 온다 =>src까지 인식 가능 
			Reader reader = Resources.getResourceAsReader("config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<StudentVO> studentAllData(){
		
		return ssf.openSession().selectList("studentAllData");
	}
	
	
	
	
	
	
	
	
	
	
}
