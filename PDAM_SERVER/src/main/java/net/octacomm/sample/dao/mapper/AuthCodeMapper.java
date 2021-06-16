package net.octacomm.sample.dao.mapper;


import net.octacomm.sample.domain.AuthCode;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.User;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * The MyBatis Mapper of "user" table  
 * 
 * @author tykim
 * 
 */
@CacheNamespace
public interface AuthCodeMapper {
	
	
	public String INSERT_FIELDS = " ( id, userId, authCode, createDate )";
	
	public String INSERT_VALUES = " ( null, #{userId}, #{authCode}, now() )";
	
	public String TABLE_NAME = " TB_AUTH_CODE ";
	
	@Select("SELECT * FROM TB_AUTH_CODE WHERE userId = #{userId}")
	AuthCode getAuthCode(Construction construction);
	
	
	@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	int insert(@Param("userId") String userId, @Param("authCode") String authCode);

	@Select("SELECT * FROM TB_AUTH_CODE WHERE userId = #{userId} and authCode = #{authCode}")
	AuthCode getAuthCode(AuthCode authCode);
	
}
