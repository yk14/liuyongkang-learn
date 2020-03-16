package com.taotao.service;

import java.util.List;

import com.taotao.common.EasyUITreeNode;

public interface ContentCategoryService {
	public List<EasyUITreeNode> getContentCatList(Long parentId);
}
