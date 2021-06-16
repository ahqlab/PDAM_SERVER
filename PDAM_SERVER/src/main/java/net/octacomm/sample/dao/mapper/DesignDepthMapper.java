package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.DesignDepth;
import net.octacomm.sample.domain.DesignDepthParam;

public interface DesignDepthMapper extends CRUDMapper<DesignDepth, DesignDepthParam, Integer> {
	
	public String INSERT_FIELDS = " ( ddidx, deviceIdx, pileType, method , location , pileNo , pileStandard , designDepth )";
	
	public String INSERT_VALUES = " ( null, #{deviceIdx}, #{pileType}, #{method} , #{location} , #{pileNo} , #{pileStandard} , #{designDepth} )";
	
	public String TABLE_NAME = " TB_DESIGN_DEPTH ";
	
	public String UPDATE_VALUES = " pileType = #{pileType} , method = #{method} , location = #{location} , pileNo = #{pileNo} , pileStandard = #{pileStandard} , designDepth = #{designDepth} ";
	
	public String SELECT_FIELDS = " ddidx, deviceIdx, pileType, method , location , pileNo , pileStandard , designDepth ";
	
	@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	@Override
	int insert(DesignDepth domain);
		
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE ddidx =  #{ddidx}")
	@Override
	DesignDepth get(Integer id);
	
	@Override
	@Update("UPDATE  " + TABLE_NAME + " SET " + UPDATE_VALUES  + " WHERE ddidx =  #{ddidx}")
	int update(DesignDepth domain);
	
	@Delete("DELETE FROM " + TABLE_NAME + " WHERE ddidx =  #{ddidx}")
	@Override
	int delete(Integer ddIdx);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<DesignDepth> getList();
	
	

	
	
}
