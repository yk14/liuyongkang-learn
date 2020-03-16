package com.taotao.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.uitls.JsonUtils;

@Service
public class ItemParamImpl implements ItemParamService{
	@Autowired
	TbItemParamMapper itemParamMapper;
	@Autowired
	TbItemParamItemMapper itemParamItemMapper;
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria cri = example.createCriteria();
		cri.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null&&list.size() > 0) {
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam);
		}
		return TaotaoResult.ok();

	}
	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入记录
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		  // 分页查询 
			PageHelper.startPage(page, rows);
			// 查询dao中的所有记录
			List<TbItemParam> list =itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
			// 将结果list 放入 分页对象中；
			 PageInfo<TbItemParam> pageList= new PageInfo<>(list);
			// 将分页之后的list  放入  EasyUIDataGrid中
			 EasyUIDataGridResult result=new EasyUIDataGridResult(pageList.getTotal(),
			pageList.getList());				 
			return result;
	}
	@Override
	public String getItemParamHtml(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem>list = itemParamItemMapper.selectByExample(example);
		if (list == null || list.isEmpty()) {
			return "";
		}
		//取规格参数
		TbItemParamItem itemParamItem = list.get(0);
		//取json数据
		String paramData = itemParamItem.getParamData();
		//转换成java对象
		List<Map>mapList = JsonUtils.jsonToList(paramData, Map.class);
		//遍历list生成html
		StringBuffer sb = new StringBuffer();
		
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			List<Map>mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("			<td>"+map2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		
		return sb.toString();

	}
	@Override
	public TaotaoResult deleteItemParam(String ids) {
		TaotaoResult result=null;
		// 根据ids 修改状态    
		int count=0;
		String [] myids = ids.split(",");
		for(String id : myids){
			// 批量执行 更新  
			count+=itemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
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

}
