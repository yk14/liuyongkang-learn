package com.taotao.service;

import java.util.List;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbItem;
public interface ItemService {
	public int addItem(TbItem tbitem);
	public int deleteitem(Long id);
	public int update(TbItem tbitem);
	public List<TbItem> listItem();
	public TbItem findById(Long id);
	// 分页查询 商品列表  page: 当前页   rows  一页显示的行数
	public EasyUIDataGridResult getItemList(int page,int rows) ;
	 	// 下架
	public TaotaoResult updateByIds(String ids);
	 	// 上架
	public TaotaoResult updateByIds2(String ids);
	
	public TaotaoResult createItem(TbItem item, String desc,String itemParam);
	public TaotaoResult editItem(TbItem item, String desc);
	public TaotaoResult deleteItem(String ids);
}
