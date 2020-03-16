package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	public TbItem getItemById(@PathVariable Long id){
		TbItem item = itemService.findById(id);
		return item;

		
	}
	
	@ResponseBody
	@RequestMapping("/item/list")  
	public EasyUIDataGridResult itemList(Integer page ,Integer rows){
		
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		
		return result;
	}

	@ResponseBody
	@RequestMapping("/item/instock")
	public TaotaoResult  instock(String ids){
		// 1001,1002,1003
		 System.out.println("ids:"+ids);
		 TaotaoResult result = itemService.updateByIds(ids);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/item/reshelf")
	public TaotaoResult  reshelf(String ids){
		// 1001,1002,1003
		 System.out.println("ids:"+ids);
		 TaotaoResult result = itemService.updateByIds2(ids);
		
		return result;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc ,String itemParam) {
		TaotaoResult result = itemService.createItem(item, desc,itemParam);
		return result;
	}
	@RequestMapping(value="/item/edit", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult editItem(TbItem item, String desc) {
		System.out.println(item+"++++++++++++");
		TaotaoResult result = itemService.editItem(item,desc);
		return result;
	}
	@RequestMapping(value="/item/delete", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItem(String ids) {
		System.out.println(ids+"++++++++++++");
		TaotaoResult result = itemService.deleteItem(ids);
		return result;
	}
}

