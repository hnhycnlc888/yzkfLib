package yzkf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import yzkf.utils.Utility;

public class SocketTest {
	static String hello = "16 03 02 00  dc 01 00 00 d8 03 02 53 "
			+"43 5b 90 9d 9b 72 0b bc  0c bc 2b 92 a8 48 97 cf "
			+"bd 39 04 cc 16 0a 85 03  90 9f 77 04 33 d4 de 00 "
			+"00 66 c0 14 c0 0a c0 22  c0 21 00 39 00 38 00 88 "
			+"00 87 c0 0f c0 05 00 35  00 84 c0 12 c0 08 c0 1c "
			+"c0 1b 00 16 00 13 c0 0d  c0 03 00 0a c0 13 c0 09 "
			+"c0 1f c0 1e 00 33 00 32  00 9a 00 99 00 45 00 44 "
			+"c0 0e c0 04 00 2f 00 96  00 41 c0 11 c0 07 c0 0c "
			+"c0 02 00 05 00 04 00 15  00 12 00 09 00 14 00 11 "
			+"00 08 00 06 00 03 00 ff  01 00 00 49 00 0b 00 04 "
			+"03 00 01 02 00 0a 00 34  00 32 00 0e 00 0d 00 19 "
			+"00 0b 00 0c 00 18 00 09  00 0a 00 16 00 17 00 08 "
			+"00 06 00 07 00 14 00 15  00 04 00 05 00 12 00 13 "
			+"00 01 00 02 00 03 00 0f  00 10 00 11 00 23 00 00 "
			+"00 0f 00 01 01";

	 static String hb = "18 03 02 00 03 01 40 00";
	 public static byte[] hexStringToByteArray(String s){
		 s= s.replace(" ", "");
    	int len = s.length();
    	byte[] arr = new byte[len/2]; 
    	for(int i=0;i<len;i+=2){
    		int d1 = Integer.parseInt(s.substring(i, i+1),16);
    		int d2 = Integer.parseInt(s.substring(i+1, i+2),16);
    		int n = d1*16 + d2;
    		arr[i/2] = (byte) n;
    	}
    	return arr;
	    }
	public static void main(String[] args) throws IOException{
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try{
			socket=new Socket("pbsz.ebank.cmbchina.com",443);
			out =new PrintWriter(socket.getOutputStream());
			
			out.println(hexStringToByteArray(hb));
			out.flush();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String outData = in.readLine();
			System.out.println(outData);
//			out.println(hexStringToByteArray(hb));
//			out.flush();
//			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			outData = in.readLine();
//			System.out.println(outData);
		}finally{			
			if(out != null)
				out.close();
			if(in != null)
				in.close();
			if(socket !=null){
				try{socket.close();}catch(IOException e){};
			}
		}
	}
}
