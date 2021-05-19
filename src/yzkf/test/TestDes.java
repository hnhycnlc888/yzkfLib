package yzkf.test;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 
 * @author Qiulw
 *
 */
public class TestDes {

	/**
     * DES加密偏移量
     */
    private static final byte[] DESIV = { 30, (byte) 0xa2, 0x61, 0x5f,
		(byte) 0xd0, 60, (byte) 0x99, (byte) 0xbb };
    /**
     * DES加密
     * @param source 需要加密的字符串
     * @param key 秘钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptDES(String source,String key) throws Exception {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher	
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("utf-8"));
		SecretKey deskey = keyFactory.generateSecret(keySpec);
		IvParameterSpec iv = new IvParameterSpec(DESIV);		
		enCipher.init(Cipher.ENCRYPT_MODE, deskey, iv);
		
		byte[] pasByte = enCipher.doFinal(source.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(pasByte);
	}
    /**
     * DES解密
     * @param source 需要解密的字符串
     * @param key 秘钥
     * @return 解密后的字符串
     * @throws Exception
     */
	public static String decryptDES(String source,String key) throws Exception {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("utf-8"));
		SecretKey deskey = keyFactory.generateSecret(keySpec);
		IvParameterSpec iv = new IvParameterSpec(DESIV);	
		deCipher.init(Cipher.DECRYPT_MODE, deskey, iv);
		
		BASE64Decoder base64Decoder = new BASE64Decoder();		
		byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(source));
		return new String(pasByte, "utf-8");
	}
	/**
	 * 测试代码
	 * @param args
	 */
	public static void main(String[] args) {
		
		String uudi = UUID.randomUUID().toString().replace("-","");
		System.out.println(uudi.length() + " = " + uudi);
		uudi = UUID.randomUUID().toString().replace("-","");
		System.out.println(uudi.length() + " = " + uudi);
		uudi = UUID.randomUUID().toString().replace("-","");
		System.out.println(uudi.length() + " = " + uudi);
		
//		try {
//			String str = encryptDES("13580523825", "des139wt");
//			System.out.println(str);
//			String str2 = encryptDES("13580523825", "DES139WT");
//			System.out.println(str2);
////			String src = decryptDES(str, "12345670");
////			System.out.println(src);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
