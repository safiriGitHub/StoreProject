package com.safiri.store.utils;

import java.io.Serializable;
import java.util.Objects;


	
public class NetResultBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// 返回编码
    private String code;
    // 返回信息
    private String msg;
    // 签名
    private String sign = "";
    // 返回数据封装
    private Object  data;
	
    public NetResultBean() {};
	
    public NetResultBean(String code, String msg) {
    	this.code = code;
    	this.msg = msg;
    };
    
    public NetResultBean(String code, String msg, Object data) {
    	this.code = code;
    	this.msg = msg;
    	this.data = data;
    };
    
    public static NetResultBean builder(String code, String msg) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(code);
    	bean.setMsg(msg);
    	return bean;
    }
    
    public static NetResultBean builder(NetCodeEnum codeEnum) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(codeEnum.code);
    	bean.setMsg(codeEnum.msg);
    	return bean;
    }
    
    public static NetResultBean builder(NetCodeEnum codeEnum, Object data) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(codeEnum.code);
    	bean.setMsg(codeEnum.msg);
    	bean.setData(data);
    	return bean;
    }
    
    public static NetResultBean success() {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(NetCodeEnum.OK.code);
    	return bean;
    }
    
    public static NetResultBean success(Object data) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(NetCodeEnum.OK.code);
    	bean.setMsg(NetCodeEnum.OK.msg);
    	bean.setData(data);
    	return bean;
    }
    
    public static NetResultBean failureCustomizeMsg(NetCodeEnum codeEnum, String msg) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(codeEnum.code);
    	bean.setMsg(msg);
    	return bean;
    }
    public static NetResultBean failureParamNullMsg(String msg) {
    	NetResultBean bean = new NetResultBean();
    	bean.setCode(NetCodeEnum.PARAM_NULL.code);
    	bean.setMsg(msg);
    	return bean;
    }
    
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	
	public enum NetCodeEnum {
		
		OK("200", "成功"),
		VALUE_NULL("300", "值为空"),
	    PARAM_NULL("301", "参数为空，处理异常"),
	    SIGN_ERROR("400", "签名错误"),
	    NO_LOGIN("401", "未登录"),
	    SYS_ERROR("500", "系统异常");
		
		NetCodeEnum(String code, String msg) {
	    	this.code = code;
	    	this.msg = msg;
	    }


		public String getMsgByCode(String code) {
	        for (NetCodeEnum entry : NetCodeEnum.values()) {
	            if (Objects.equals(entry.getCode(), code)) {
	                return entry.getMsg();
	            }
	        }

	        return "";
	    }
	    
	    private String code;
	    private String msg;
	    
	    public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		
	}
}


