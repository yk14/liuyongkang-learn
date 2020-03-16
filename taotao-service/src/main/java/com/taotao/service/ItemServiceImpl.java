package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.uitls.IDUtils;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	TbItemMapper itemMapper;
	@Autowired
	TbItemDescMapper itemDescMapper;
	@Autowired
	TbItemParamItemMapper itemParamItemMapper;
	@Override
	public int addItem(TbItem tbitem) {
		
		return itemMapper.insert(tbitem);
	}

	@Override
	public int deleteitem(Long id) {
		
		return itemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TbItem tbitem) {
		// TODO Auto-generated method stub
		return itemMapper.updateByPrimaryKey(tbitem);
	}

	@Override
	public List<TbItem> listItem() {
		
		TbItemExample example = new TbItemExample();
		Criteria cri=example.createCriteria();
		cri.andIdIsNotNull();
		return itemMapper.selectByExample(example );
	}

	@Override
	public TbItem findById(Long id) {
		
		return itemMapper.selectByPrimaryKey(id);
	}
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
	     // 分页查询 
		PageHelper.startPage(page, rows);
		// 查询dao中的所有记录
		List<TbItem> list =itemMapper.selectByExample(new TbItemExample());
		// 将结果list 放入 分页对象中；
		 PageInfo<TbItem> pageList= new PageInfo<>(list);
		// 将分页之后的list  放入  EasyUIDataGrid中
		 EasyUIDataGridResult result=new EasyUIDataGridResult(pageList.getTotal(),
				 pageList.getList());
		 
		 
		return result;
	}
	// 下架 
	@Override
	public TaotaoResult updateByIds(String ids) {
		TaotaoResult result=null;
		// 根据ids 修改状态    
		int count=0;
		String [] myids = ids.split(",");
		for(String id : myids){
			// 批量执行 更新  
			count+=itemMapper.updateByIds(Integer.parseInt(id), 2);
		}
		
		if(count>0){
			result=new TaotaoResult(null); // 返回一个前端需要的对象，成功状态 200
			// 默认200
		}else{
			// 操作失败
		    result =new TaotaoResult();
			result.setData("操作失败。");
			
		}
		
		return result;
	}
	
	// 上架
	@Override
	public TaotaoResult updateByIds2(String ids) {
		TaotaoResult result=null;
		// 根据ids 修改状态        暂时不用，每次只能修改一个 
	//	int count=itemMapper.updateByIds( ids ,1);
		// 批量修改   （将ids 分割）
		int count=0;
		String [] myids = ids.split(",");
		for(String id : myids){
			// 批量执行 更新  
			count+=itemMapper.updateByIds(Integer.parseInt(id), 1);
		}
		
		if(count>0){
			result=new TaotaoResult(null); // 返回一个前端需要的对象，成功状态 200
			// 默认200
		}else{
			// 操作失败
		    result =new TaotaoResult();
			result.setData("操作失败。");
			
		}
		
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc , String itemParam) {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//插入记录
		itemParamItemMapper.insert(itemParamItem);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult editItem(TbItem item, String desc ) {
		Date date = new Date();
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		int count=itemMapper.updateByPrimaryKey(item);
		System.out.println(count);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		int count1=itemDescMapper.updateByPrimaryKey(itemDesc);
		System.out.println(count1);
		if(count>0 && count>0){
		return TaotaoResult.ok();
		}
		else{
			return null;
		}
	}

	@Override
	public TaotaoResult deleteItem(String ids) {
		int count=itemMapper.deleteByPrimaryKey(Long.parseLong(ids));
		if(count>0){
			return TaotaoResult.ok();
		}else{
			return null;
		}
		
	}
}
