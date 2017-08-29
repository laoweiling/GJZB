package com.lnsf.service.impl;

import org.springframework.stereotype.Service;

import com.lnsf.service.ReadWordService;
import com.lnsf.util.ReadWord;

/**
* @author 黄卉 
* @version 创建时间：2017年7月31日 下午8:18:07
* @introduction    读取文档业务逻辑
*/
@Service("readWordService")
public class ReadWordServiceImpl implements ReadWordService {
		
	@Override
	public String readWord(String filePath) {
		//String中是使用域 count 来记录对象字符的
		//String的最大长度=2的28次方 -1=268,435,455.最大长度只和字符的个数有关.也就是理论上2G.
		String text=ReadWord.readWord(filePath);
		return text;
	}

}
