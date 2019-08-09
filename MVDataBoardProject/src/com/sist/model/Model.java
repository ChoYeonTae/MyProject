package com.sist.model;
import java.util.*;
import java.io.*;
import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.*;
//�ڹ� , �±���  => �и� (MV)
public class Model {
	
	
	public void databoard_list(HttpServletRequest request){
		//list.jsp ó��
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage = Integer.parseInt(page);
		//������ �б�
		DataBoardDAO dao = new DataBoardDAO();
		List<DataBoardVO> list = dao.databoardListData(curpage);
		
		int totalpage = dao.databoardTotalPage();
		
		//jsp�� ����� ����
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		
	}
	
	public void databoard_insert_ok(HttpServletRequest request,HttpServletResponse response){
		
		try {	
			request.setCharacterEncoding("UTF-8");
			String path="c:\\upload";
			String enctype = "UTF-8";
			int size = 100*1024*1024;
			MultipartRequest mr = new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
			//new DefaultFileRenamePolicy()  ==> ���� ���ϸ� => ex) a.jpg �� �ΰ��� �ϳ��� a1.jpg�� �ٲ�
			String name=mr.getParameter("name");
			String subject=mr.getParameter("subject");
			String content=mr.getParameter("content");
			String pwd=mr.getParameter("pwd");
			
			DataBoardVO vo = new DataBoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			String filename = mr.getOriginalFileName("upload");
			
			if(filename == null){
				vo.setFilename("");
				vo.setFilesize(0);
			} else {
				File file = new File(path+"\\"+filename);
				vo.setFilename(filename);
				vo.setFilesize((int)file.length());
			}
			
			DataBoardDAO dao = new DataBoardDAO();
			dao.databoardInsert(vo);
			
		} catch (Exception e) {}
	}
	
	public void databoard_detail(HttpServletRequest request){
		
		String no = request.getParameter("no");
		String curpage=request.getParameter("page");
		//DAO ����
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoardDetailData(Integer.parseInt(no));
		//���� ������ =>> JS ����
		request.setAttribute("vo", vo);
		request.setAttribute("curpage", curpage);
		
		//���
		List<DataBoardReplyVO> list = dao.databoardReplyData(Integer.parseInt(no));
		request.setAttribute("list", list);
		request.setAttribute("len", list.size());
	}
	
	//�����ϱ�
	public void databoard_update_data(HttpServletRequest request){
		
		String no = request.getParameter("no");
		String page=request.getParameter("page");
		//DAO ����
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoardUpdateData(Integer.parseInt(no));
		//request�� ���� ����ش� ==> JSP����  request�� �ִ� ������ ���
		request.setAttribute("vo", vo);
		request.setAttribute("curpage", page);
		/*
		 * �� ==> ����� ���� �������� (�б�,���� ) ==> getter setter
		 * �� => ��ûó��
		 * �� ==> ȭ�� ���
		 */
		
		
	}
	
	
	public void databoard_update_ok(HttpServletRequest request){
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {		}
		//������ �ޱ�
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		
		DataBoardVO vo = new DataBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		//DAO�� ���� => ó�� => ����� => update_ok.jsp ����
		DataBoardDAO dao = new DataBoardDAO();
		boolean bCheck =dao.databoardUpdate(vo);
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page);
		request.setAttribute("no", no);
	}
	
	public void login(HttpServletRequest request){
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//DAO ����
		DataBoardDAO dao = new DataBoardDAO();
		String result = dao.isLogin(id, pwd);
		
		if(!(result.equals("NOID")||result.equals("NOPWD"))){
			//session�� ����
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", result);
			session.setAttribute("pwd", pwd);
		}
		request.setAttribute("result", result);
		
	}
	
	public void reply_insert(HttpServletRequest request){
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String msg = request.getParameter("msg");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		//request =>session cookie
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		DataBoardReplyVO vo =new DataBoardReplyVO();
		vo.setId(id);
		vo.setName(name);
		vo.setBno(Integer.parseInt(bno));
		vo.setMsg(msg);
		
		//DAO�� ���� ==> ����Ŭ ����
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyInsert(vo);
		//�̵� => �ʿ��� ������ ���� ==> bno,page
		request.setAttribute("bno", bno);
		request.setAttribute("page", page);
		
	}
	
	public void reply_delete(HttpServletRequest request){
		
		String no = request.getParameter("no");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		//DAO => ��� ����
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyDelete(Integer.parseInt(no));
		//jsp = >�ʿ��� ������ ����
		request.setAttribute("no", bno);
		request.setAttribute("page", page);
		
		
	}
	
	public void reply_update(HttpServletRequest request){
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String no = request.getParameter("no");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		String msg = request.getParameter("msg");
		
		//DAO => ��� ����
		DataBoardDAO dao = new DataBoardDAO();
		dao.replyUPdate(Integer.parseInt(no), msg);
		
		//jsp = >�ʿ��� ������ ����
		request.setAttribute("no", bno);
		request.setAttribute("page", page);
		
		
	}
	
	//�����ϱ�
	public void databoard_delete(HttpServletRequest request){
		
		//delete.jsp => no page => delete.jsp����
		String no = request.getParameter("no");
		String page= request.getParameter("page");
		
		request.setAttribute("no", no);
		request.setAttribute("page", page);
		
	}
	
	public void databoard_delete_ok(HttpServletRequest request){
		String no = request.getParameter("no");
		String page= request.getParameter("page");
		String pwd = request.getParameter("pwd");
		
		//DB����
		DataBoardDAO dao = new DataBoardDAO();
		DataBoardVO vo = dao.databoardFileInfo(Integer.parseInt(no));
		boolean bCheck = dao.databoard_delete(Integer.parseInt(no), pwd);
		if (bCheck==true){
			
			if(vo.getFilesize()>0){
				
				try {
					File file = new File("c:\\upload\\"+vo.getFilename());
					file.delete();
				} catch (Exception e) {}	
			}
		}
		//JSP ����
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page); //list.jsp
	}
	
	
	
	
	
	
	
	
	
	
}
