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
		//config => web.xml�� ���� (ȯ�漳�� ���� => ServletConfig)
		String path=config.getInitParameter("contextConfigLocation");
	//	System.out.println(path);
		
		try {
			//xml�� �����͸� ���� ���� => �Ľ�
			//1.xml�ļ��� ����
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//2.�ļ���
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml���� ���� ������ ����
			Document doc = db.parse(new File(path));
			
			//xml => root Ȯ��
			Element root = doc.getDocumentElement();  //Element = tag
			System.out.println(root.getTagName()); //beans ==> table
			
			NodeList list=root.getElementsByTagName("bean"); 
			//bean���� ����ִ°�
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
			//����ڰ� ��û = > Model ã��
			String cmd = request.getRequestURI();
			cmd = cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
			Model model=(Model)map.get(cmd);
			
		//	System.out.println("model:"+model);
			String jsp =model.handlerRequest(request);
			if(jsp.startsWith("redirect")){
				//ȭ���̵�
				response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
				
			} else {
				//ȭ�� ���
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
	
	
	
	
	
	
}
