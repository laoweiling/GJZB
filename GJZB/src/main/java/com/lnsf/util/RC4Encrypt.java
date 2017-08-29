package com.lnsf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * �����㷨��RC4����
 * ���ã� String str = RC4Encrypt.getkeyToPWD(inputStr);  inputStr�����룬���ɷ���str�����Ժ������
 * ���ص�str�����Ѿ������Ժ������
 *
 */
public class RC4Encrypt {
	
	public RC4Encrypt(){
		//���췽����������Կ		    
	}
	public  String HloveyRC4(String aInput,String aKey){   
		//����S�еĺ����㷨
        int[] iS = new int[256];   
        byte[] iK = new byte[256];   

        for (int i=0;i<256;i++)   
            iS[i]=i;           //256������   

        int j = 1;             //���� 

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
		  //��ȡ�ַ������Զ���ȡ��Կ�����м���
		String key=null;
		 try {
				Properties prop =  new  Properties();   
				InputStream in=this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
				prop.load(in);
				 key=prop.getProperty("key").trim(); 
				System.out.println("��Կ�ǣ�"+key);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("������Կʧ�ܣ�");
					e.printStackTrace();
				}
		 return key;
		 
	}
	
    public static String getkeyToPWD(String inputStr){
       //��̬���������ⷽ�����Խ��м���
    	RC4Encrypt  rc4Encrypt=new RC4Encrypt();	
        String str = rc4Encrypt.HloveyRC4(inputStr,rc4Encrypt.getKey());  
        return str;
    }
	
	public static void main(String[] args){
		 String inputStr = "admin";      
		//    String key = "abcdefg";          
		    //��ӡ���ܺ���ַ�RC4Encrypt��      
		    System.out.println("��ӡ���ܺ�"+RC4Encrypt.getkeyToPWD(inputStr));    
	}
}
