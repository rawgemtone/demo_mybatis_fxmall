package com.qianfeng.fxmall.goods.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BaseServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws  IOException
	{
		req.setCharacterEncoding("utf-8");
		String m =req.getParameter("m");
		try
		{
			Method method = getClass().getMethod(m, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, req,resp);
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		doGet(req, resp);
	}
	
}
