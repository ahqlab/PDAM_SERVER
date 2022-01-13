package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.Group;
import net.octacomm.sample.domain.GroupParam;

@CacheNamespace
public interface GroupMapper extends CRUDMapper<Group, GroupParam, Integer>{
	
	public String INSERT_FIELDS = " ( idx, groupName, createDate , lastModifiedDate   )";
	
	public String INSERT_VALUES = " ( null, #{groupName}, now() , now() )";
	
	public String TABLE_NAME = " TB_GROUP ";
	
	public String UPDATE_VALUES = " groupName = #{groupName} , isDel = #{isDel} lastModifiedDate = now() ";
	
	public String SELECT_FIELDS = " idx, groupName,  isDel,  createDate, lastModifiedDate ";
	
	@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	@Override
	int insert(Group domain);
		
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE idx =  #{idx} and isDel = 0")
	@Override
	Group get(Integer id);
	
	@Override
	int update(Group domain);
	
	@Delete("DELETE FROM " + TABLE_NAME + " WHERE idx =  #{idx} and isDel = 0")
	@Override
	int delete(Integer id);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<Group> getList();
	
	@Select("SELECT count(*) FROM (SELECT * FROM TB_DEVICE WHERE conduct = 0 AND isDel = 0) A, (SELECT * FROM TB_CONSTRUCTION WHERE conduct = 0 AND isDel = 0) B, TB_GROUP C WHERE C.idx = B.groupIdx AND A.constructionIdx = B.id ")
	int getTotalUseDeviceCount();
	
	@Select("SELECT count(*) FROM (SELECT * FROM TB_DEVICE WHERE conduct = 2 AND isDel = 0) A, (SELECT * FROM TB_CONSTRUCTION WHERE conduct = 0 AND isDel = 0) B, TB_GROUP C WHERE C.idx = B.groupIdx AND A.constructionIdx = B.id ")
	int getPrenchTotalUseDeviceCount();
	
	@Select("SELECT count(*) FROM TB_CONSTRUCTION  A,  TB_GROUP C WHERE C.idx = A.groupIdx AND A.conduct = 0 AND A.isDel = 0")
	int getTotalUseConstructionCount();

	
	@Select("SELECT * FROM TB_GROUP WHERE userId = #{userId}")
	Group selectByUserId(@Param("userId") String userId);
	

}
