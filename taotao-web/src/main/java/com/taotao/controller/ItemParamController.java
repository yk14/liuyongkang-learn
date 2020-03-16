package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {
	@Autowired
	ItemParamService itemParamService;
	
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long cid) {
		TaotaoResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}
	
	
	@RequestMapping("/item/param/list") 
	@ResponseBody
	public EasyUIDataGridResult itemParamList(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="30")Integer rows){
		
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		System.out.println("111");
		return result;
	}
	
	@RequestMapping("/item/param/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String html = itemParamService.getItemParamHtml(itemId);
		model.addAttribute("html", html);
		return"itemparam";
	}

	@RequestMapping("/itemParam/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
		return result;
	}
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public TaotaoResult deleteItemParam(String ids){
		return itemParamService.deleteItemParam(ids);
		
	}
	
}	
