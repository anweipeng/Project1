package fanggu.org.lotteryapplication.util;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


public class ComnUtil {

	public  static String getUUID(){
		String UUID=java.util.UUID.randomUUID().toString();
		return UUID.replaceAll("-", "");
	}

	public static boolean isImg(String fileName){
		String[] arr=new String[]{"jpg","jpeg","png","gif","ico"};
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		List<String> list=Arrays.asList(arr);
		if(list.contains(suffix.toLowerCase())){
			return true;
		}
		return false;
	}

	public static boolean isVideo(String fileName){
		String[] arr=new String[]{"avi","rmvb","mp4","wmv","ico"};
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		List<String> list=Arrays.asList(arr);
		if(list.contains(suffix.toLowerCase())){
			return true;
		}
		return false;
	}

	/**
	 * 字符串数组转字符串，以逗号分隔
	 * @param arr
	 * @return
	 */
	public static String strArrToStr(String[] arr){
		StringBuilder str=new StringBuilder("");
		for(int i=0;i<arr.length;i++){
			str.append(arr[i]);
			if(i<arr.length-1){
				str.append(",");
			}
		}
		return str.toString();
	}




	/**
	 * 封装返回结果
	 * @param b
	 * @return
	 */
	public static JSONObject packResult(boolean b) throws JSONException {
		JSONObject result=new JSONObject();
		if(b){
			result.put("code", 1);
		}else{
			result.put("code", 0);
		}
		result.put("msg", "");
		result.put("content", "");
		return result;
	}


	/**
	 * 拼接url字符串
	 * @param params
	 * @return
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}


	/**
	 * 生成随机数字
	 * @param length
	 * @return
	 */
	public static String getRandomIntStr(int length) {
		String val = "";
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			String charOrNum = "num";   // random.nextInt(2) % 2 == 0 ? "char" : "num";
			val += String.valueOf(getRandomInt(0,9));
		}
		return val.toUpperCase();   //转换成大小
	}



	/**
	 * 生成随机数字或字母
	 * @param length
	 * @param charOrNum   char字母, num数字
	 * @return
	 */
	public static String getRandomStr(int length,String charOrNum) {
		String val = "";
		Random random = new Random(System.currentTimeMillis());
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char)(random.nextInt(26) + temp);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();   //转换成小写
	}



	/**
	 * 封装返回的map对象
	 * @param code
	 * @param msg
	 * @param content
	 * @return
	 */
	public static JSONObject returnResult(int code,String msg,Object content) throws JSONException {
		JSONObject json=new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		json.put("content", content);
		return json;
	}

	/**
	 * 封装返回的map对象
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JSONObject returnResult(int code,String msg) throws JSONException {
		JSONObject json=new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		return json;
	}


	/**
	 * 百度地图，获取两点间的距离
	 * @param lng_x 坐标x的经度
	 * @param lat_x 坐标x的纬度
	 * @param lng_y 坐标y的经度
	 * @param lat_y 坐标y的纬度
	 * @return
	 */
	public  static  double getDistanceFromXtoY_BaiDu(double lng_x, double lat_x ,double lng_y, double lat_y ) {
		double pk = 180 / Math.PI;
		double earth_radius=6370996.81;  //地球半径
		double a1 = lat_x / pk;    //转换为弧度
		double a2 = lng_x / pk;
		double b1 = lat_y / pk;
		double b2 = lng_y / pk;
		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		double t3 = Math.sin(a1) * Math.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);
		return  earth_radius* tt;
	}


	/**
	 * 删除List中每个map元素的key
	 * @param keyName
	 * @param list
	 * @return
	 */
	public static List removeKey(String keyName,List<Map>list){
		for(int i=0;i<list.size();i++){
			Map map=list.get(i);
			map.remove(keyName);
		}
		return list;
	}

	/**
	 * 异常堆栈输出到StringWriter
	 * @param e
	 * @return
	 */
	public static StringWriter packExceptionStringWriter(Exception e){
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw =  new PrintWriter(sw);
			//将出错的栈信息输出到printWriter中
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		return sw;
	}




	/**
	 * 随机生成一个[start,end]之间的数字,包含起始结束两个数字
	 * @param start
	 * @param end
	 */
	public static int getRandomInt(int start,int end){
		String uuId=getUUID();
		String ascii=stringToAsciiString(uuId);
		long seed=Long.parseLong(ascii.substring(0, 15));
		Random random=new Random(seed);
		int num= start+random.nextInt(end+1-start);
		return num;
	}


	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (i != chars.length - 1) {
				sbu.append((int) chars[i]).append(",");
			} else {
				sbu.append((int) chars[i]);
			}
		}
		return sbu.toString();
	}

	public static String stringToAsciiString(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append((int) chars[i]);
		}
		return sbu.toString();
	}


	/**
	 * 百分数转小数
	 * @param percent
	 * @return
	 */
	public static double changePercentToPoint(String percent) {
		return new Double(percent.substring(0, percent.indexOf("%")))/100;
	}

	/**
	 * 获得本机IP
	 * @return
	 */
	public static String getLocalMachineIp(){
		String addr = "";
		try {
			addr = InetAddress.getLocalHost().getHostAddress(); //获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr;
	}


}
