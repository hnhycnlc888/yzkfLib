package yzkf.test;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import yzkf.utils.MD5;
import yzkf.utils.Security;

public class TestEndecrypt {
	

	/**
	 * 得到3-DES的密钥匙 根据接口规范，密钥匙为24个字节，md5加密出来的是16个字节，因此后面补8个字节的0
	 * 
	 * @param String
	 *            原始的SPKEY
	 * @return byte[] 指定加密方式为md5后的byte[]
	 */
	private byte[] getEnKey(String spKey) {
		byte[] desKey = null;
		try {
			byte[] desKey1 = md5(spKey);
			desKey = new byte[24];
			int i = 0;
			while (i < desKey1.length && i < 24) {
				desKey[i] = desKey1[i];
				i++;
			}
			if (i < 24) {
				desKey[i] = 0;
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desKey;
	}

	/**
	 * 3-DES加密
	 * 
	 * @param byte[] src 要进行3-DES加密的byte[]
	 * @param byte[] enKey 3-DES加密密钥
	 * @return byte[] 3-DES加密后的byte[]
	 */
	public byte[] Encrypt(byte[] src, byte[] enKey) {
		byte[] encryptedData = null;
		try {
			DESedeKeySpec dks = new DESedeKeySpec(enKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedData = cipher.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	/**
	 * 对字符串进行Base64编码
	 * 
	 * @param byte[] src 要进行编码的字符
	 * 
	 * @return String 进行编码后的字符串
	 */
	public static String getBase64Encode(byte[] src) {
		String requestValue = "";
		try {
			BASE64Encoder base64en = new BASE64Encoder();
			requestValue = base64en.encode(src);
			// System.out.println(requestValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	/**
	 * 去掉字符串的换行符号 base64编码3-DES的数据时，得到的字符串有换行符号 ，一定要去掉，否则uni-wise平台解析票根不会成功，
	 * 提示“sp验证失败”。在开发的过程中，因为这个问题让我束手无策， 一个朋友告诉我可以问联通要一段加密后 的文字，然后去和自己生成的字符串比较，
	 * 这是个不错的调试方法。我最后比较发现我生成的字符串唯一不同的 是多了换行。 我用c#语言也写了票根请求程序，没有发现这个问题。
	 * 
	 */
	private String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}

	/**
	 * 对字符串进行URLDecoder.encode(strEncoding)编码
	 * 
	 * @param String
	 *            src 要进行编码的字符串
	 * 
	 * @return String 进行编码后的字符串
	 */
	public String getURLEncode(String src) {
		String requestValue = "";
		try {

			requestValue = URLEncoder.encode(src);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	/**
	 * 3-DES加密
	 * 
	 * @param String
	 *            src 要进行3-DES加密的String
	 * @param String
	 *            spkey分配的SPKEY
	 * @return String 3-DES加密后的String
	 */
	public String get3DESEncrypt(String src, String spkey) {
		String requestValue = "";
		try {

			// 得到3-DES的密钥匙
			byte[] enKey = getEnKey(spkey);
			// 要进行3-DES加密的内容在进行\"UTF-16LE\"取字节
			byte[] src2 = src.getBytes("UTF-8");
			// 进行3-DES加密后的内容的字节
			byte[] encryptedData = Encrypt(src2, enKey);

			// 进行3-DES加密后的内容进行BASE64编码
			String base64String = getBase64Encode(encryptedData);
			// BASE64编码去除换行符后
			String base64Encrypt = filter(base64String);

			// 对BASE64编码中的HTML控制码进行转义的过程
			requestValue = getURLEncode(base64Encrypt);
			// System.out.println(requestValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	/**
	 * 对字符串进行URLDecoder.decode(strEncoding)解码
	 * 
	 * @param String
	 *            src 要进行解码的字符串
	 * 
	 * @return String 进行解码后的字符串
	 */
	public String getURLDecoderdecode(String src) {
		String requestValue = "";
		try {

			requestValue = URLDecoder.decode(src);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	/**
	 * 
	 *进行3-DES解密（密钥匙等同于加密的密钥匙）。
	 * 
	 * @param byte[] src 要进行3-DES解密byte[]
	 * @param String
	 *            spkey分配的SPKEY
	 * @return String 3-DES解密后的String
	 */
	public String deCrypt(byte[] debase64, String spKey) {
		String strDe = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DESede");
			byte[] key = getEnKey(spKey);
			DESedeKeySpec dks = new DESedeKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey sKey = keyFactory.generateSecret(dks);
			cipher.init(Cipher.DECRYPT_MODE, sKey);
			byte ciphertext[] = cipher.doFinal(debase64);
			strDe = new String(ciphertext, "UTF-16LE");
		} catch (Exception ex) {
			strDe = "";
			ex.printStackTrace();
		}
		return strDe;
	}

	/**
	 * 3-DES解密
	 * 
	 * @param String
	 *            src 要进行3-DES解密的String
	 * @param String
	 *            spkey分配的SPKEY
	 * @return String 3-DES加密后的String
	 */
	public String get3DESDecrypt(String src, String spkey) {
		String requestValue = "";
		try {

			// 得到3-DES的密钥匙

			// URLDecoder.decodeTML控制码进行转义的过程
			String URLValue = getURLDecoderdecode(src);

			// 进行3-DES加密后的内容进行BASE64编码

			BASE64Decoder base64Decode = new BASE64Decoder();
			byte[] base64DValue = base64Decode.decodeBuffer(URLValue);

			// 要进行3-DES加密的内容在进行\"UTF-16LE\"取字节
			requestValue = deCrypt(base64DValue, spkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestValue;
	}
	public static byte[] encrypt(String content, byte[] password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password,"AES");// 使用SecretKeySpec类来根据一个字节数组构造一个
							// SecretKey,，而无须通过一个（基于provider的）SecretKeyFactory.
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
																		// 为创建
																		// Cipher
																		// 对象，应用程序调用
																		// Cipher
																		// 的
																		// getInstance
																		// 方法并将所请求转换
																		// 的名称传递给它。还可以指定提供者的名称（可选）。
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
															// Cipher 的初始化方式）。
			//return byte2hex(result);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String byte2hex(byte[] b) { // 一个字节的数，

		// 转成16进制字符串

		String hs = "";
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			tmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}
		tmp = null;
		return hs.toUpperCase(); // 转成大写

	}
	/**
	 * 进行MD5加密
	 * 
	 * @param String
	 *            原始的SPKEY
	 * @return byte[] 指定加密方式为md5后的byte[]
	 */
	private static byte[] md5(String strSrc) {
		byte[] returnByte = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			returnByte = md5.digest(strSrc.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnByte;
	}
	 /** 对字符串进行MD5加密     */
    public static String md5Encode(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString;
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
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    /** *//** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    private static byte[] hexStringToByteArray(String s){
    	int len = s.length();
    	byte[] arr = new byte[16]; 
    	for(int i=0;i<len;i+=2){
    		int d1 = Integer.parseInt(s.substring(i, i+1),16);
    		int d2 = Integer.parseInt(s.substring(i+1, i+2),16);
    		int n = d1*16 + d2;
    		arr[i/2] = (byte) n;
    	}
    	return arr;
    }
//    pubic static void test1(String src,String key,String charSetName){
//
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			DESKeySpec keySpec = new DESKeySpec(key.getBytes(charSetName));
//			SecretKey securekey = keyFactory.generateSecret(keySpec);
//			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, securekey);
//			byte2hex(cipher.doFinal(byte2hex(src.getBytes(charSetName))));
//    }
	public static void main(String[] args) {
		String src = "13650890511";
		String key = "mail139@.";
		String charSetName = "utf-8";
		try {
			//System.out.println(Security.encryptDESECB("13650890511", "rBjzCNzoV9Qxvmf2"));//lt8J2DeJtPZ8nEcCo45ngQ==
			String sign = Security.encryptDES(src, MD5.encode(key).substring(0, 8),"utf-8");//96DF09D83789B4F67C9C4702A38E6781
			//String sign = Security.byte2hex(Security.encryptDES(src.getBytes(charSetName), MD5.encode(key).substring(0, 8).toUpperCase().getBytes("ASCII"),"DES/ECB/PKCS5Padding"));
			System.out.println(sign);
			//System.out.println(Security.decryptDESECB("lt8J2DeJtPZ8nEcCo45ngQ==", "rBjzCNzoV9Qxvmf2"));
			String de = Security.decryptDES(sign, MD5.encode(key).substring(0, 8),"utf-8");
			//String de = new String(Security.decryptDES(Security.hex2byte(sign.getBytes(charSetName)), MD5.encode(key).substring(0, 8).toUpperCase().getBytes("ASCII"),"DES/ECB/PKCS5Padding"), charSetName);
			System.out.println(de);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(byteArrayToHexString(md5("13726873311")));
		
//		String ClientHash = getBase64Encode(md5("MM_WAP_3G"+"2a89a2b70081898c0efe6559e4482425"+"Mmwap3G001"));
//		System.out.println("ClientHash: " + ClientHash);
		
		
//		String md5Str = md5Encode("MM_WAP_3G"+"Mmwap3G001");		
//		System.out.println("md5Str：" + md5Str);
//		byte[] key1 = hexStringToByteArray(md5Str);
//		byte[] key = md5("MM_WAP_3G"+"Mmwap3G001");		
//		if(key1.equals(key)) System.out.println("相等" );
//		byte[] aes = encrypt("30X4Zv",key);
//		String str = getBase64Encode(aes);
//		System.out.println("加密后: " + str); //Lytm2QP3+cD9qLFtD5Qzzg==

//		String source = "13632664097";
//		String key = "Ue2g_jTv";
//		String sign = "";
//		try {
//			TestEndecrypt se = new TestEndecrypt();
//			sign = se.get3DESEncrypt(source, key);
//			//sign = Security.encryptDES(source, key);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("进行DES加密后的密文: " + sign);
		
//		TestEndecrypt test = new TestEndecrypt();
//		String oldString = "13570475605";
//		String SPKEY = "des139wt";
//		System.out.println("1.密钥: " + SPKEY);
//		System.out.println("2.明文: " + oldString);
//		
//		String reValue = test.get3DESEncrypt(oldString, SPKEY);
//		//reValue = reValue.trim().intern();
//		System.out.println("进行3DES加密后的密文: " + reValue);
//		String reValue2 = test.get3DESDecrypt(reValue, SPKEY);
//		System.out.println("进行3DES解密后的明文: " + reValue2);
	}
}
