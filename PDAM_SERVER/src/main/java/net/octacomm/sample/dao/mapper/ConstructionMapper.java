package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.ConstructionParam;

@CacheNamespace
public interface ConstructionMapper extends CRUDMapper<Construction, ConstructionParam, Integer>{
	
	public String INSERT_FIELDS = " ( role, name, location , address , manager, contact, userId , password , groupIdx, secretCode , createDate, isDel  )";
	
	public String INSERT_VALUES = " ( 1, #{name}, #{location}, #{address}, #{manager}, #{contact}, #{userId}, #{password}, #{groupIdx} , #{secretCode} , now(), 0 )";
	
	public String TABLE_NAME = " TB_CONSTRUCTION ";
	
	public String UPDATE_VALUES = " role = #{role} , name = #{name} ,  location = #{location} , address = #{address}, manager = #{manager} , contact = #{contact} , userId = #{userId}, password = #{password} , groupIdx = #{groupIdx} , secretCode = #{secretCode} ";
	
	public String SELECT_FIELDS = " id, role, name, location , address , manager, contact, userId , password , groupIdx, createDate , isDel, secretCode , conduct  ";
	
	@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	@Override
	int insert(Construction domain);
		
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE id =  #{id} and isDel = 0")
	@Override
	Construction get(Integer id);
	
	@Override
	int update(Construction domain);
	
	@Delete("DELETE FROM " + TABLE_NAME + " WHERE id =  #{id} and isDel = 0")
	@Override
	int delete(Integer id);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<Construction> getList();
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME + " WHERE userId = #{userId} and isDel = 0")
	List<Construction> getFindByContact(String userId);
	
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME + " WHERE id = #{constructionIdx} and isDel = 0")
	List<Construction> getListByConstructionIdx(int constructionIdx);
	
	@Update("UPDATE " + TABLE_NAME + " SET isDel = 1 where id = #{id}")
	int doDelete(int id);
	
	
	@Select(" SELECT CONCAT(CONCAT(IFNULL((SELECT groupName from TB_GROUP WHERE TB_GROUP.idx = TB_CONSTRUCTION.groupIdx), ''),  CONCAT(' ',NAME)), CONCAT(' ',LOCATION)) AS NAME  FROM TB_CONSTRUCTION WHERE id = #{id}")
	Construction getFullName(int id);

	@Update("UPDATE " + TABLE_NAME + " SET conduct = #{conduct} where id = #{id}")
	int updateConduct(@Param("id") int id, @Param("conduct") int conduct);

}
