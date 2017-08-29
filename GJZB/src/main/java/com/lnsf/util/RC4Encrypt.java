package com.lnsf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加密算法：RC4加密
 * 调用： String str = RC4Encrypt.getkeyToPWD(inputStr);  inputStr是密码，即可返回str加密以后的密文
 * 返回的str则是已经加密以后的密码
 *
 */
public class RC4Encrypt {
	
	public RC4Encrypt(){
		//构造方法，加载密钥		    
	}
	public  String HloveyRC4(String aInput,String aKey){   
		//机密S盒的核心算法
        int[] iS = new int[256];   
        byte[] iK = new byte[256];   

        for (int i=0;i<256;i++)   
            iS[i]=i;           //256个数字   

        int j = 1;             //整数 

        for (short i= 0;i<256;i++){   
        	//
            iK[i]=(byte)aKey.charAt((i % aKey.length()));   
        }   

        j=0;   

        for (int i=0;i<255;i++){   
            j=(j+iS[i]+iK[i]) % 256;   
            int temp = iS[i];   
            iS[i]=iS[j];   
            iS[j]=temp;   
        }   


        int i=0;   
        j=0;   
        char[] iInputChar = aInput.toCharArray();   
        char[] iOutputChar = new char[iInputChar.length];   
        for(short x = 0;x<iInputChar.length;x++)   
        {   
            i = (i+1) % 256;   
            j = (j+iS[i]) % 256;   
            int temp = iS[i];   
            iS[i]=iS[j];   
            iS[j]=temp;   
            int t = (iS[i]+(iS[j] % 256)) % 256;   
            int iY = iS[t];   
            char iCY = (char)iY;   
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;      
        }   

        return new String(iOutputChar);   

    }
	
	public String getKey(){
		  //获取字符串，自动获取密钥，进行加密
		String key=null;
		 try {
				Properties prop =  new  Properties();   
				InputStream in=this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
				prop.load(in);
				 key=prop.getProperty("key").trim(); 
				System.out.println("密钥是："+key);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("加载密钥失败！");
					e.printStackTrace();
				}
		 return key;
		 
	}
	
    public static String getkeyToPWD(String inputStr){
       //静态方法，任意方法可以进行加密
    	RC4Encrypt  rc4Encrypt=new RC4Encrypt();	
        String str = rc4Encrypt.HloveyRC4(inputStr,rc4Encrypt.getKey());  
        return str;
    }
	
	public static void main(String[] args){
		 String inputStr = "admin";      
		//    String key = "abcdefg";          
		    //打印加密后的字符RC4Encrypt串      
		    System.out.println("打印加密后："+RC4Encrypt.getkeyToPWD(inputStr));    
	}
}
