package yzkf.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import yzkf.model.ContactInfo;
import yzkf.utils.Xml;


public class XmlTest {
	public static void main(String[] args) throws  IOException{
		//Xml xml = new Xml(is)
		
//		String xmlStr = obj2xml();
//		xml2obj(xmlStr);
//		xml2obj();
		
//		java.io.File file = new java.io.File("ContactInfo.xml");  
//        if(!file.exists()){  
//            file.createNewFile();  
//        }  
//        
//        ContactInfo obj = new ContactInfo();
//		obj.setAddrFirstName("邱");
//		obj.setAddrSecondName("礼文");
//		obj.setMobilePhone("13760709457");
//		obj.setFamilyEmail("lwqiu@139.com"); 
//          
//		java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
//        java.io.BufferedOutputStream oop = new java.io.BufferedOutputStream(os);  
//        java.beans.XMLEncoder xe = new java.beans.XMLEncoder(oop);  
//        //xe.flush();  
//        //写入xml  
//        xe.writeObject(obj);
//        xe.flush();
//        byte[] bts = os.toByteArray();       
//        xe.close();  
//        oop.close();  
//          
//        //读取xml文件  
//        java.beans.XMLDecoder xd = new java.beans.XMLDecoder(new BufferedInputStream(new ByteArrayInputStream(bts)));  
//  
//        ContactInfo s2 = (ContactInfo) xd.readObject();  
//        xd.close();  
//        System.out.println("name: "+s2.getAddrFirstName());  
//        System.out.println("age :"+s2.getAddrSecondName());  
	}
	public static String obj2xml() throws UnsupportedEncodingException{
		ContactInfo obj = new ContactInfo();
		obj.setAddrFirstName("邱");
		obj.setAddrSecondName("礼文");
		obj.setMobilePhone("13760709457");
		obj.setFamilyEmail("lwqiu@139.com");
		String str = Xml.objectToXml(obj, "UTF-8");
		System.out.println(str);
		return str;
	}
	public static void xml2obj() throws UnsupportedEncodingException{
		ContactInfo obj = Xml.xmlToObject("<ContactInfo><addrFirstName>邱</addrFirstName><addrSecondName>礼文</addrSecondName></ContactInfo>", "UTF-8");
		System.out.println(obj.getAddrFirstName() + obj.getAddrSecondName());
	}
	public static void xml2obj(String str) throws UnsupportedEncodingException{
		ContactInfo obj = Xml.xmlToObject(str,"UTF-8");
		System.out.println(obj.getAddrFirstName() + obj.getAddrSecondName());
	}
}
