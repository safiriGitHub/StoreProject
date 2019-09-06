package com.safiri.store.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.safiri.store.utils.NetResultBean.NetCodeEnum;

public class NetResponse extends HttpServletResponseWrapper{
	
	private HttpServletResponse response;
	public NetResultBean resultBean;
	
	public NetResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
		setResContentTypeEnum(ResContentTypeEnum.JSON);
	}
	
	public NetResponse(HttpServletResponse response, ResContentTypeEnum type) {
		super(response);
		this.response = response;
		setResContentTypeEnum(type);
	}
	
	public NetResponse(HttpServletResponse response, NetResultBean bean) {
		super(response);
		this.response = response;
		setResContentTypeEnum(ResContentTypeEnum.JSON);
		this.resultBean = bean;
	}
	
	public enum ResContentTypeEnum {
		HTML("text/html;charset=UTF-8"),
		JSON("application/json;charset=UTF-8");
		private String text;
		public void setText(String text) {
			this.text = text;
		}
		public String getText() {
			return text;
		}
		ResContentTypeEnum(String text) {
			this.text = text;
		}	
	}
	public void setResContentTypeEnum(ResContentTypeEnum type) {
		if (ResContentTypeEnum.HTML == type) {
			this.response.setContentType(type.text); 
		}else if (ResContentTypeEnum.JSON == type) {
			this.response.setContentType(type.text); 
		}
	}
	
	
	//MARK: - Response的响应自定义业务json
	
	
	public void writeNetResultBean(NetResultBean result) {
		writeString(JSON.toJSONString(result));
	}
	
	public void writeNetResultBeanWithReturn(NetResultBean result) {
		writeString(JSON.toJSONString(resultBean));
		return;
	}
	
	public void writeNetResultBean() {
		writeString(JSON.toJSONString(resultBean));
	}
	
	public void writeNetResultBeanWithReturn() {
		writeString(JSON.toJSONString(resultBean));
		return;
	}
	
	//MARK: - Response的响应字符流
	public void writeString(String responsestr) {
		try {
			response.getWriter().print(responsestr);
		} catch (IOException e) {
			NetResultBean bean = NetResultBean.failureCustomizeMsg(NetCodeEnum.SYS_ERROR, e.getMessage());
			writeNetResultBean(bean);
		}
	}
	
	//MARK: - Response的响应字节流
	public void writebytes(String filePath) {
		 try {
			FileInputStream in = new FileInputStream(filePath);
			//读取输入流的字节到字节数组中，IOUtis是Apache提供的一个工具jar包
			byte[] bytes = IOUtils.toByteArray(in);
			response.getOutputStream().write(bytes);
		} catch (Exception e) {
			NetResultBean bean = NetResultBean.failureCustomizeMsg(NetCodeEnum.SYS_ERROR, e.getMessage());
			writeNetResultBean(bean);
		}
	}
	
	public void writebytes(byte[] bytes) {
		 try {
			 response.getOutputStream().write(bytes);
		} catch (Exception e) {
			NetResultBean bean = NetResultBean.failureCustomizeMsg(NetCodeEnum.SYS_ERROR, e.getMessage());
			writeNetResultBean(bean);
		}
	}
	
	
	
	//MARK: - 下载图片，通过OutputStream流
	/**
	 *  	下载图片，通过OutputStream流
	 * @param realPath 要下载的文件名
	 * 		
	 * 1.获取要下载的文件的绝对路径
     * String realPath = this.getServletContext().getRealPath("/download/demo.jpg");
	 */
	public void downloadFile(String realPath) {
        try {
			//2.获取要下载的文件名
			String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
			//3.设置content-disposition响应头控制浏览器以下载的形式打开文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//4.获取要下载的文件输入流
			InputStream in = new FileInputStream(realPath);
			int len = 0;
			//5.创建数据缓冲区
			byte[] buffer = new byte[1024];
			//6.通过response对象获取OutputStream流
			OutputStream out = response.getOutputStream();
			//7.将FileInputStream流写入到buffer缓冲区
			while ((len = in.read(buffer)) > 0) {
				//8.使用OutputStream将缓冲区的数据输出到客户端浏览器
				out.write(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			NetResultBean bean = NetResultBean.failureCustomizeMsg(NetCodeEnum.SYS_ERROR, e.getMessage());
			writeNetResultBean(bean);		
		}
	}
	
	
	//MARK: - 其他
	/**
	 * 
	 * response.setHeader("Location","/JavaWeb/Bservlet");
	 * 	设置服务器的响应状态码
	 * response.setStatus(302);
	 * 	快捷的重定向方式
	 * response.sendRedirectCustomize("/JavaWeb/Bservlet");
	 * @param location
	 * @throws IOException
	 * 
	 */
	public void sendRedirectCustomize(String location) throws IOException {
		this.response.sendRedirect(location);
	}
	
	/**
     * 	设置refresh响应头，让浏览器每隔n秒定时刷新
     *	 response.setHeader("refresh", "3");
     */
	public void refresh(String seconds) {
		response.setHeader("refresh", seconds);
	}
	
	/**
	 * 	设置refresh响应头，让浏览器n秒后跳转到 url
	 * 	response.setHeader("Refresh","5;URL=http://www.baidu.com"); 
	 */
	public void refresh(String seconds, String url) {
		response.setHeader("Refresh",seconds+";URL="+url);  
	}
	
	/**
	 * 	禁用浏览器缓存
	 */
	public void nocache() {
		//这三个响应头的设置可以禁用所有浏览器的缓存
        response.setHeader("cache-control","no-cache");
        response.setHeader("pragma","no-cache");
        response.setDateHeader("expires",-1);
	}
	
	/**
	 * 	设置页面缓存时间
	 */
	public void dataHeader(int seconds) {
		response.setDateHeader("expires",System.currentTimeMillis() + seconds * 1000);
	}
	
}
