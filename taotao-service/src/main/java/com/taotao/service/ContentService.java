package com.taotao.service;

import java.util.List;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbContent;


public interface ContentService {
	public TaotaoResult insertContent(Long cid, String paramData);
	public EasyUIDataGridResult getContentList(int page,int rows);
	public TaotaoResult deleteContent(String ids);
}
