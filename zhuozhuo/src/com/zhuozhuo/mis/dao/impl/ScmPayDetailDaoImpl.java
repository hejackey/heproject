package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmPayDetailDao;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.utils.StringUtils;

public class ScmPayDetailDaoImpl  extends BaseDao implements ScmPayDetailDao {

	@Override
	public List<ScmPayDetail> showScmPayDetail(String id) {
		List<ScmPayDetail> list = getSqlMapClientTemplate().queryForList("scmPayDetail.showScmPayDetail",id);
		return list;
	}
	@Override
	public List<ScmPayDetail> listScmPayDetails() {		
		List<ScmPayDetail> list = getSqlMapClientTemplate().queryForList("scmPayDetail.getAllScmPayDetails");
		return list;
	}

	@Override
	public void saveScmPayDetail(ScmPayDetail scmPayDetail) {
		getSqlMapClientTemplate().insert("scmPayDetail.insertScmPayDetail",scmPayDetail);			
	}

	@Override
	public void deleteScmPayDetails(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmPayDetail.deleteScmPayDetails",map);
	}
	
	public List<ScmPayDetail> getScmPayDetailByProviderId(String pid)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("scmPayDetail.getScmPayDetailByProviderId",pid);
	}
	
	public List<ScmPayDetail> getScmPayDetailByMasterId(String mid)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("scmPayDetail.getScmPayDetailByMasterId",mid);
	}
	
	public void updateScmPayDetailAuditSign(final String[] idList,final String masterId,final String[] arrids)throws Exception{
		if (arrids != null && arrids.length> 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
									
					int i=0;
					for (String id : arrids) {
						ScmPayDetail detail = new ScmPayDetail();
						detail.setId(Integer.valueOf(id));
						detail.setMasterId(masterId);
						
						if(i<idList.length && !StringUtils.isEmpty(idList[i])){
							detail.setAuditSign("1");
						}
						else{
							detail.setAuditSign("0");
						}
						i++;
						
						executor.update("scmPayDetail.updateScmPayDetailAuditSign", detail);										
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}
	}
}
