package net.octacomm.sample.dao.mapper;


import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.User;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

/**
 * The MyBatis Mapper of "user" table  
 * 
 * @author tykim
 * 
 */
@CacheNamespace
public interface UserMapper {
	
//	@Select("SELECT * FROM TB_CONSTRUCTION WHERE userId = #{userId} and isDel = 0")
	@Select("SELECT * FROM (" + 
			"	SELECT  userId , id , role, password, groupIdx FROM TB_CONSTRUCTION where isDel = 0 " + 
			"	UNION ALL " + 
			"	SELECT userId, 1000, role, password , idx as groupIdx FROM TB_GROUP WHERE TB_GROUP.userid is not null " + 
			"	UNION ALL " + 
			"	SELECT userId, 2000, role, password , idx as groupIdx FROM tb_franchise WHERE tb_franchise.userid is not null " + 
			") A WHERE userId = #{userId} ")
	Construction getUser(Construction construction);

//	@Select("SELECT * FROM TB_CONSTRUCTION WHERE userId = #{userId} AND password = #{password} and isDel = 0")
	@Select("SELECT * FROM (" +  
			"	SELECT  userId , id , role, password, groupIdx FROM TB_CONSTRUCTION where isDel = 0 " + 
			"	UNION ALL " + 
			"	SELECT userId, 1000, role, password , idx as groupIdx FROM TB_GROUP WHERE TB_GROUP.userid is not null " + 
			"	UNION ALL " + 
			"	SELECT userId, 2000, role, password , idx as groupIdx FROM tb_franchise WHERE tb_franchise.userid is not null " + 
			") A WHERE userId = #{userId}  AND password = #{password} ")
	Construction getUserForAuth(Construction construction);
	
	@Select("SELECT * FROM TB_CONSTRUCTION WHERE userId = #{userId} AND CONCAT(password ,secretCode) = #{password} and isDel = 0")
	Construction findByHiddenManagerPassword(Construction construction);

}
