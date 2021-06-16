package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.ErpAdmin;
import net.octacomm.sample.domain.ErpAdminParam;
import net.octacomm.sample.domain.Group;
import net.octacomm.sample.domain.GroupParam;

@CacheNamespace
public interface ErpAdminMapper extends CRUDMapper<ErpAdmin, ErpAdminParam, Integer>{
	
	public String INSERT_FIELDS = " ( eaidx, printDate, manager , publicService , construction , safety , measurement , pileCuttingWork  )";
	
	public String INSERT_VALUES = " ( null, #{printDate} , #{manager} , #{publicService} , #{construction} , #{safety} , #{measurement} , #{pileCuttingWork} )";
	
	public String TABLE_NAME = " TB_ERP_ADMIN ";
	
	public String UPDATE_VALUES = " printDate = #{printDate} , manager = #{manager} , publicService = #{publicService} , construction = #{construction} , safety = #{safety} , measurement = #{measurement} , pileCuttingWork = #{pileCuttingWork} ";
	
	public String SELECT_FIELDS = " eaidx, printDate, manager , publicService , construction , safety , measurement , pileCuttingWork ";
	
	@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	@Override
	int insert(ErpAdmin domain);
		
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE idx =  #{idx} and isDel = 0")
	@Override
	ErpAdmin get(Integer id);
	
	@Override
	int update(ErpAdmin domain);
	
	@Delete("DELETE FROM " + TABLE_NAME + " WHERE idx =  #{idx} and isDel = 0")
	@Override
	int delete(Integer id);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<ErpAdmin> getList();

}
