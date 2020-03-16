package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.EasyUITreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryImpl implements ContentCategoryService{
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
				TbContentCategoryExample example = new TbContentCategoryExample();
				Criteria criteria = example.createCriteria();
				criteria.andParentIdEqualTo(parentId);
				//执行查询
				List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
				//转换成EasyUITreeNode列表
				List<EasyUITreeNode> resultList = new ArrayList<>();
				for (TbContentCategory tbContentCategory : list) {
					//创建一EasyUITreeNode节点
					EasyUITreeNode node = new EasyUITreeNode();
					node.setId(tbContentCategory.getId().intValue());
					node.setText(tbContentCategory.getName());
					node.setState(tbContentCategory.getIsParent()?"closed":"open");
					//添加到列表
					resultList.add(node);
				}
				return resultList;

	}

}
