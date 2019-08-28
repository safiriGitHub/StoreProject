package com.safiri.store.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.io.UnsupportedEncodingException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaUtils {
	
	/**
	 * 设置request、response编码格式为UTF-8，中文乱码问题
	 */
	public static void charsetUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
	 * 返回32位纯数字字母组成的UUID
	 * @return
	 */
	public static String uuid32() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 取出指定cookie
	 * @param cookies HttpServletRequest中的cookies属性
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(Cookie[] cookies, String name) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
     * 清除字典中值为空的项。
     *
     * @param <V> 泛型
     * @param map 待清除的字典
     * @return 清除后的字典
     */
    public static <V> Map<String, V> cleanupMap(Map<String, V> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, V> result = new HashMap<String, V>(map.size());
        Set<Entry<String, V>> entries = map.entrySet();

        for (Entry<String, V> entry : entries) {
            if (entry.getValue() != null) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
	
	private static String localIp;
	/**
     * 获取本机的网络IP
     */
    public static String getLocalNetWorkIp() {
        if (localIp != null) {
            return localIp;
        }
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
                    continue;
                }
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements()) {
                    InetAddress address = addresss.nextElement();
                    if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
                        ip = address;
                        break;
                    }
                }
                if (ip != null) {
                    break;
                }
            }
            if (ip != null) {
                localIp = ip.getHostAddress();
            } else {
                localIp = "127.0.0.1";
            }
        } catch (Exception e) {
            localIp = "127.0.0.1";
        }
        return localIp;
    }
	
	private static String RADIX_62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 将数字转为62进制
     *
     * @param num    Long 型数字
     * @param length 转换后的字符串长度，不足则左侧补0
     * @return 62进制字符串
     */
    public static String toRadix62Str(long num, int length) {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;

        while (num >= 62) {
            remainder = Long.valueOf(num % 62).intValue();
            sb.append(RADIX_62_CHARS.charAt(remainder));

            num = num / 62;
        }

        sb.append(RADIX_62_CHARS.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return StringUtils.leftPad(value, length, '0');
    }

    /**
     * 62进制字符串转为数字
     *
     * @param str 编码后的62进制字符串
     * @return 解码后的 10 进制字符串
     */
    public static long fromRadix62Str(String str) {
        str = str.replace("^0*", "");
        long num = 0;
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            index = RADIX_62_CHARS.indexOf(str.charAt(i));
            num += (long) (index * (Math.pow(62, str.length() - i - 1)));
        }

        return num;
    }
}
