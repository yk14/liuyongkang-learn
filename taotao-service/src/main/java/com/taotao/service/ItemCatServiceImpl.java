package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.common.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	TbItemCatMapper itemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		//按照parentID查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//条件
		Criteria criteria= example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行
		List<TbItemCat> list=itemCatMapper.selectByExample(example );
		List<EasyUITreeNode> resultlist= new ArrayList<>();
		for(TbItemCat item :list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(item.getId().intValue());
			node.setText(item.getName());
			node.setState(item.getIsParent()?"closed" : "open");
			resultlist.add(node);
		}
		return resultlist;
	}

}
