package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	TbContentMapper contentMapper;

	@Override
	public TaotaoResult insertContent(Long cid, String paramData) {
		
		return null;
	}

	@Override
	public EasyUIDataGridResult getContentList(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaotaoResult deleteContent(String ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
