package net.octacomm.sample.dao.mapper;


import net.octacomm.sample.domain.FileUsingChart;
import net.octacomm.sample.domain.FileUsingChartParam;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * The MyBatis Mapper of "user" table  
 * 
 * @author tykim
 * 
 */
@CacheNamespace
public interface FileUsingChartMapper {
	
	FileUsingChart getChartData(FileUsingChartParam param);
	
	FileUsingChart getChartData(@Param("constructionIdx") int constructionIdx, @Param("pileType") String pileType, @Param("pileStandard") String pileStandard, @Param("name") String name);
	
	FileUsingChart getChartData2(@Param("constructionIdx") int constructionIdx, @Param("pileType") String pileType, @Param("pileWeight") String pileWeight, @Param("pileStandard") String pileStandard, @Param("name") String name);
}
