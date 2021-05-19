package yzkf.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import yzkf.utils.MD5;

public class DesEncryptUtil163 {
	private final static String DES = "DES";
	/**
	 *固定的密钥
	 */
	private static final String DEFAULT_KEY = "1!2@3#4#56789^&(0098765&**%4321ewews*(^%^@dnsd,ms*HUI*new4324739";

	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return    返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws RuntimeException
	{
		//      DES算法要求有一个可信任的随机数源
		try
		{
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象  
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(src);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 * @param src   数据源
	 * @param key   密钥，长度必须是8的倍数
	 * @return      返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws RuntimeException
	{
		try
		{
			//      DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(src);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 数据解密
	 * @param data
	 * @param key 密钥
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String charSetName) throws Exception
	{
		return decrypt(data, DEFAULT_KEY, charSetName);
	}

	/**
	 * 数据解密
	 * @param data
	 * @param key 密钥
	 * @param charSetName 编码
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key, String charSetName) throws Exception
	{
		return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()), charSetName);
	}

	/**
	 * 数据加密
	 * @param data
	 * @param key 密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key, String charSetName)
	{
		if (data != null)
			try
			{
				return byte2hex(encrypt(data.getBytes(charSetName), key.getBytes()));
			}
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		return null;
	}



	/**
	 * 数据加密
	 * @param data
	 * @param key 密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String charSetName)
	{

		return encrypt(data, DEFAULT_KEY, charSetName);
	}

	/**
	 * 二行制转字符串
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b)
	{
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++)
		{
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b)
	{
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2)
		{
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) throws Exception
	{
		String mail = "13888020393@139.com";
		String key = "mail139dyp2014";
		String url = "http://piao.163.com/baoxian/activity/index.html?um="+encrypt(mail,key,"utf-8")+"&sign="+MD5.encode(mail+ "mail139dy" + "netease-piao-139dy","GBK")+"&remark=mail139dy";
		System.out.println("邮箱：" + mail);
		System.out.println("URL：" + url);

//		readSendHistory("D:\\163history\\");
//		
//		File dir = new File("D:\\163");
//		File destDir = new File("D:\\163url\\");
//		if(!destDir.exists())
//			destDir.mkdir();
//		File[] files = dir.listFiles();
//		for(int i=0;i<files.length;i++){
//			//System.out.println(destDir.getPath() + "\\" + files[i].getName());
//			change(files[i],new File(destDir.getPath() + "\\" + files[i].getName()));
//			
//		}
		
//		change("D:\\p2edm20140402-tdzx-1wangy（139返利）.txt","D:\\p2edm20140402-tdzx-1wangy（139返利）_url.txt");
//		change("D:\\p5edm20140402-tdzx-1wangy电商.txt","D:\\p5edm20140402-tdzx-1wangy电商_url.txt");
	}
	public static ArrayList<String> history  = new ArrayList<String>();
	public static void readSendHistory(String path){
		//ArrayList<String> history  = new ArrayList<String>();
		File dir = new File(path);
		File[] files = dir.listFiles();
		for(int i=0;i<files.length;i++){
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
				for (String mail = input.readLine(); mail != null; mail = input.readLine()) { 
					history.add(mail.substring(0, 19));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
	}
	public static void change(String src,String dest) throws IOException{
		File srcFile=new File(src);
	    File destFile=new File(dest);
	    change(srcFile,destFile);
	}
	public static void change(File src,File dest) throws IOException{
		System.out.println("["+formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"]Start："+src.getName());
		BufferedReader input = null;
		OutputStreamWriter output = null;
		int lineNumber = 0;
		try {    
	        if(!dest.exists())
	        	newFile(dest);
	        output = new OutputStreamWriter(new FileOutputStream(dest));

	        String key = "mail139dyp2014";
			
			input = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
			for (String mail = input.readLine(); mail != null; mail = input.readLine()) {  
				if(mail.length() <=11) continue;
				if(history.contains(mail)) continue;
				String url =mail+","+encrypt(mail,key,"utf-8")+","+md5encode(mail+ "mail139dy" + "netease-piao-139dy","GBK")+"\r\n";
				output.append(url);
				lineNumber++;
				if(lineNumber%10000 == 0)
					System.out.println("["+formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"]进度："+lineNumber);
	        }
			output.flush();	        
		}finally{
			try{
				if(input != null)
					input.close();
			}finally{
				if(output != null)
					output.close();
			}
		}
		System.out.println("["+formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"]Complete:("+lineNumber+")"+src.getName());
	}
	private static boolean newFile(File file) throws IOException{
		if(!file.exists()){	        	
        	File dir =  file.getParentFile();
        	if(!dir.exists())
        		dir.mkdirs();
            return file.createNewFile();
        }
		return false;
	}
	private static String formatDate(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
     * 生成对字符串的MD5签名
     * @param originString 源字符串
     * @param charsetName 字符串编码字符
     * @return
     */
    public static String md5encode(String originString,String charsetName){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes(charsetName));
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 转换字节数组为十六进制字符串
     * @param b    字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    /**
     * 将一个字节转化成十六进制形式的字符串
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;        
        return hexDigits[d1] + hexDigits[d2];
        //return Integer.toHexString(d1) + Integer.toHexString(d2);
    }
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
