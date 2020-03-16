package com.taotao.service;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;

public interface ItemParamService {
	public TaotaoResult getItemParamByCid(long cid);
	public TaotaoResult insertItemParam(Long cid, String paramData);
	public EasyUIDataGridResult getItemParamList(int page,int rows);
	public String getItemParamHtml(Long itemId);
	public TaotaoResult deleteItemParam(String ids);
}
