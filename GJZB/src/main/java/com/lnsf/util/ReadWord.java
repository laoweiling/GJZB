package com.lnsf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
* @author 劳伟玲 
* @version 创建时间：2017年7月31日 下午6:16:14
* @introduction   读取文档信息,2007和2007。      ReadWord.readWord(fileUrl)
*/
public class ReadWord {
	/**读取Word文档,通过试
	 * 备注：不能读取图片，只能是文字或者英文、中文
	* @author 劳伟玲 
	* @version 创建时间：2017年7月31日 下午6:16:14
	* @introduction   读取Word文档    调用接口：
	*/
	public static String readWord(String filePath){  
	    String text = "";  
	    File file = new File(filePath);    //获取文件
	    //如果是2003的文档  
	    if(file.getName().endsWith(".doc")){  
	        try {  
	            FileInputStream stream = new FileInputStream(file);  
	            WordExtractor word = new WordExtractor(stream);  
	            text = word.getText();  
	            //去掉word文档中的多个换行  
	      //      text = text.replaceAll("(\\r\\n){2,}", "\r\n");  
	      //      text = text.replaceAll("(\\n){2,}", "\n");  
	            stream.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	  //如果是2007的文档
	    }else if(file.getName().endsWith(".docx")){       //2007  
	        try {  
	            OPCPackage oPCPackage = POIXMLDocument.openPackage(filePath);  
	            XWPFDocument xwpf = new XWPFDocument(oPCPackage);  
	            POIXMLTextExtractor ex = new XWPFWordExtractor(xwpf);  
	            text = ex.getText();  
	            //去掉word文档中的多个换行  
	       //     text = text.replaceAll("(\\r\\n){2,}", "\r\n");  
	     //      text = text.replaceAll("(\\n){2,}", "\n");  
	            
	            System.out.println("ok:"+text);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    return text;  
	}  
	/**读取txt、sql、Java文档
	* @author 劳伟玲 
	* @version 创建时间：2017年7月31日 下午6:16:14
	* @introduction   读取txt文档、sql、Java文档也可以。
	*/
	public static String readTxtOrSql(String filePath)throws Exception{
		try{
		File file=new File(filePath);
    	System.out.println("判断文件是否存在，file.exists："+file.exists());
    	if(!file.exists()){
    		System.out.println("文件不存在!");
    	}
    	else{//文件存在
    		System.out.println("文件已经存在,开始读取文件，请稍后......");
    		RandomAccessFile raf=new RandomAccessFile(file,"rw");
    		 String line=null;  
             while((line=raf.readLine())!=null) { 
	    		 String s=raf.readLine();    
	    		 if(s!=null){
	    			 s=new String(s.getBytes("ISO-8859-1"), "gbk");//编码转换    	
		       //      s=new String(s.getBytes("8859_1"), "gbk");//编码转换    	
		             System.out.println(s); //打印文件内容
	    		 }
	    		 else{
	    			 System.out.println("----------");
	    		 }
             }
     	    raf.close();//关闭 
     	    System.out.println("关闭RandomAccessFile");
    	}
		}
		catch(Exception e){
			System.out.println("异常");
			e.printStackTrace();
		}
		return filePath;
	}
	

	
	public static void main(String[] args){
	/*	String fileUrl="f://a1项目设计（IT科技节）//培训内容文字稿.doc";
		try {
			System.out.println(readTxtOrSql(fileUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//f://文档1.docx    f://a1项目设计（IT科技节）//培训内容文字稿.doc
			String fileUrl="f://如何处理学习和工作的关系.docx";
		try {
			System.out.println(ReadWord.readWord(fileUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
