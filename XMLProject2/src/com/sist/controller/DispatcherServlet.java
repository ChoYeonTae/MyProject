package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.sist.model.Model;

//DOM , SAX
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map map = new HashMap();
	public void init(ServletConfig config) throws ServletException {
		//config => web.xml을 제어 (환경설정 파일 => ServletConfig)
		String path=config.getInitParameter("contextConfigLocation");
	//	System.out.println(path);
		
		try {
			//xml의 데이터를 읽이 시작 => 파싱
			//1.xml파서기 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//2.파서기
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml에서 읽은 데이터 저장
			Document doc = db.parse(new File(path));
			
			//xml => root 확인
			Element root = doc.getDocumentElement();  //Element = tag
			System.out.println(root.getTagName()); //beans ==> table
			
			NodeList list=root.getElementsByTagName("bean"); 
			//bean들을 모아주는것
			for(int i =0;i<list.getLength();i++){
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls= bean.getAttribute("class");
				
			//	System.out.println("id:"+id+",class:"+cls);
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				
				map.put(id, obj);
			}
			
		} catch (Exception e) {

		}
		
		
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자가 요청 = > Model 찾기
			String cmd = request.getRequestURI();
			cmd = cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
			Model model=(Model)map.get(cmd);
			
		//	System.out.println("model:"+model);
			String jsp =model.handlerRequest(request);
			if(jsp.startsWith("redirect")){
				//화면이동
				response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
				
			} else {
				//화면 출력
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
	
	
	
	
	
	
}
