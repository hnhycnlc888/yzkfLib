package yzkf.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import yzkf.api.Contacts;
import yzkf.api.Mail;
import yzkf.api.result.ContactResult;
import yzkf.exception.ApiException;
import yzkf.exception.AppException;
import yzkf.exception.ParserConfigException;
import yzkf.model.ContactInfo;
import yzkf.utils.Xml;

public class ContactTest {

	public static void main(String[] args) throws IntrospectionException, ApiException{
		//add("13730124054");
		//getLastContact();
		//getAllWithDetail();		
		//getAll();
		//search("13730124054");
		//get139List("13730124054");
		//getSelfInfo();
		//addUserInfo("13730124054");
		modUserInfo("13730124054");
		//getSelfInfo("18215198418");
	}
	public static void addUserInfo(String mobile){
		
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			ContactInfo bean = new ContactInfo();
			bean.setOtherEmail("abc@qq.com");
			bean.setBusinessMobile("13900001111");
			bean.setAddrFirstName("ModFirstName");
			bean.setAddrNickName("ModeNickName");
			bean.setAddrSecondName("ModSecondName");
			result = api.addMySelfInfo(mobile, bean);
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		if(result.isOK()){
			System.out.println("接口执行成功");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	public static void modUserInfo(String mobile){
		
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			ContactInfo bean = new ContactInfo();
//			bean.setOtherEmail("abc@qq.com");
//			bean.setBusinessMobile("13900001111");
//			bean.setBirDay("2013-04-01");
			bean.setAddrFirstName("ModFirstName");
			bean.setAddrNickName("ModeNickName");
			bean.setAddrSecondName("ModSecondName");
			bean.setUserSex(1);
			result = api.modMySelfInfo(mobile, bean);
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		if(result.isOK()){
			System.out.println("接口执行成功");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	public static void getSelfInfo(String mobile){		
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.getMySelfInfo(mobile);
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				ContactInfo info = result.getValue(ContactInfo.class);
				if(info != null){
					BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
					PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
					for(PropertyDescriptor p : pd){
						System.out.println(p.getName() + "=" + p.getReadMethod().invoke(info) + ";");
					}
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void other(){
		ContactInfo info = new ContactInfo();		
		info.setMobilePhone("13730124054");
		info.setAddrFirstName("李");
		info.setAddrSecondName("三");
//		BeanInfo BEANINFO = Introspector.getBeanInfo(info.getClass());
//		PropertyDescriptor[] PD = BEANINFO.getPropertyDescriptors();
//		for(PropertyDescriptor P : PD){
//			System.out.println("GETPROPERTYTYPE="+P.getPropertyType().getName()+" | ATTRIBUTENAMES:"+P.attributeNames()+" | GETDISPLAYNAME="+P.getDisplayName()+" | GETNAME="+P.getName()+" | GETSHORTDESCRIPTION="+P.getShortDescription());
//			
//		}
		
		try {
			String nodeString = Xml.beanToXmlString(info,ContactInfo.class);
			System.out.println(nodeString);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void add(String mobile){
		Contacts api = new Contacts();
		ContactResult result = null;
		ContactInfo info = new ContactInfo();
		//info.setAddrFirstName("王");
		info.setAddrSecondName("小二");
		info.setMobilePhone("13760709455");
		info.setAddrNickName("昵称");
		//info.setFamilyEmail("13730124010@139.com");
		try {
			result = api.add(mobile, info);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			ContactInfo outBean = result.getValue(ContactInfo.class);
			System.out.println("SerialId="+outBean.getSerialId());
			System.out.println("FirstNameword="+outBean.getFirstNameword());
			System.out.println("FullNameword="+outBean.getFullNameword());
			System.out.println("FirstWord="+outBean.getFirstWord());
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	
	public static void getLastContact() throws ApiException{
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.getLastContact("13730124054");
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				HashMap<String, ArrayList<ContactInfo>> map = (HashMap<String, ArrayList<ContactInfo>>) result.getValue();
				List<ContactInfo> last = map.get("Last");	//获取最近联系人
				List<ContactInfo> close = map.get("Close");		//获取紧密联系人
				
				
				BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
				PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
				System.out.println("==============最近联系人==============");
				//输出最贱联系人
				Iterator<ContactInfo> itr = last.iterator();
				while(itr.hasNext()){
					ContactInfo info = itr.next();
					System.out.println("SerialId=" + info.getSerialId()+";" + "Name=" + info.getName() + ";"+"Type=" + info.getAddrType() + ";"+"Content=" + info.getFamilyEmail() + ";");
				}
				System.out.println("==============紧密联系人==============");
				//输出紧密联系人
				Iterator<ContactInfo> itr2 = close.iterator();
				while(itr2.hasNext()){
					ContactInfo info = itr2.next();
					System.out.println("SerialId=" + info.getSerialId()+";" + "Name=" + info.getName() + ";"+"Type=" + info.getAddrType() + ";"+"Content=" + info.getFamilyEmail() + ";");
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void getAllWithDetail() throws ApiException{
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.getAllWithDetail("13730124054", 1, 10);			
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				List<ContactInfo> list = (List<ContactInfo>) result.getValue();
				Iterator<ContactInfo> itr = list.iterator();
				BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
				PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
				while(itr.hasNext()){
					ContactInfo info = itr.next();
					for(PropertyDescriptor p : pd){
						System.out.print(p.getName() + "=" + p.getReadMethod().invoke(info) + ";");
					}
					System.out.println("");
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getAll(){
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.getAll("13730124054", 1, 10);
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				System.out.println("通讯录总数：" +api.getTotalRecord());
				ArrayList<ContactInfo> list = result.getValue(ArrayList.class);
				Iterator<ContactInfo> itr = list.iterator();
				BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
				PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
				while(itr.hasNext()){
					ContactInfo info = itr.next();
					for(PropertyDescriptor p : pd){
						System.out.print(p.getName() + "=" + p.getReadMethod().invoke(info) + ";");
					}
					System.out.println("");
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void search(String mobile){
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.search(mobile, "13648067454", 1, 0, 1, 100);//搜索有邮箱地址并且包含@139.com的通讯录信息
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				System.out.println("通讯录总数：" +api.getTotalRecord());
				ArrayList<ContactInfo> list = result.getValue(ArrayList.class);
				Iterator<ContactInfo> itr = list.iterator();
				BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
				PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
				while(itr.hasNext()){
					ContactInfo info = itr.next();
					for(PropertyDescriptor p : pd){
						System.out.print(p.getName() + "=" + p.getReadMethod().invoke(info) + ";");
					}
					System.out.println("");
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void get139List(String mobile){		
		Contacts api = new Contacts();
		ContactResult result = null;
		try {
			result = api.get139List(mobile, 1, 100);
		} catch (ApiException e) {
			e.printStackTrace();
			return;
		}
		try{
			if(result.isOK()){
				System.out.println("通讯录总数：" +api.getTotalRecord());//只有查询第一页时才返回
				ArrayList<ContactInfo> list = result.getValue(ArrayList.class);
				Iterator<ContactInfo> itr = list.iterator();
				BeanInfo beanInfo = Introspector.getBeanInfo(ContactInfo.class);
				PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
				while(itr.hasNext()){
					ContactInfo info = itr.next();
					for(PropertyDescriptor p : pd){
						System.out.print(p.getName() + "=" + p.getReadMethod().invoke(info) + ";");
					}
					System.out.println("");
				}
			}else{
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
