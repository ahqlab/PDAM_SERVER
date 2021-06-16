package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.FileInventoryParam;
import net.octacomm.sample.domain.FileInventory;
import net.octacomm.sample.domain.FileInventoryOfChart;

@CacheNamespace
public interface FileInventoryMapper extends CRUDMapper<FileInventory, FileInventoryParam, Integer> {

	public String TABLE_NAME = " TB_FILE_INVENTORY ";

	public String UPDATE_VALUES = " pileType = '${pileType}' , pileStandard = '${pileStandard}'  , fileWeight = '${fileWeight}' "
			+ ", meterof51 = '${meterof51}'  , meterof52 = '${meterof52}'  , meterof53 = '${meterof53}'  , meterof54 = '${meterof54}'  "
			+ ", meterof61 = '${meterof61}'  , meterof62 = '${meterof62}'  , meterof63 = '${meterof63}'  , meterof64 = '${meterof64}'  "
			+ ", meterof71 = '${meterof71}'  , meterof72 = '${meterof72}'  , meterof73 = '${meterof73}'  , meterof74 = '${meterof74}'  "
			+ ", meterof81 = '${meterof81}'  , meterof82 = '${meterof82}'  , meterof83 = '${meterof83}'  , meterof84 = '${meterof84}'  "
			+ ", meterof91 = '${meterof91}'  , meterof92 = '${meterof92}'  , meterof93 = '${meterof93}'  , meterof94 = '${meterof94}'  "
			+ ", meterof101 = '${meterof101}'  , meterof102 = '${meterof102}'  , meterof103 = '${meterof103}'  , meterof104 = '${meterof104}'  "
			+ ", meterof111 = '${meterof111}'  , meterof112 = '${meterof112}'  , meterof113 = '${meterof113}'  , meterof114 = '${meterof114}'  "
			+ ", meterof121 = '${meterof121}'  , meterof122 = '${meterof122}'  , meterof123 = '${meterof123}'  , meterof124 = '${meterof124}'  "
			+ ", meterof131 = '${meterof131}'  , meterof132 = '${meterof132}'  , meterof133 = '${meterof133}'  , meterof134 = '${meterof134}'  "
			+ ", meterof141 = '${meterof141}'  , meterof142 = '${meterof142}'  , meterof143 = '${meterof143}'  , meterof144 = '${meterof144}'  "
			+ ", meterof151 = '${meterof151}'  , meterof152 = '${meterof152}'  , meterof153 = '${meterof153}'  , meterof154 = '${meterof154}'  "
			+ ", meterof161 = '${meterof161}'  , meterof162 = '${meterof162}'  , meterof163 = '${meterof163}'  , meterof164 = '${meterof164}'  "
			+ ", meterof171 = '${meterof171}'  , meterof172 = '${meterof172}'  , meterof173 = '${meterof173}'  , meterof174 = '${meterof174}'  "
			+ ", meterof181 = '${meterof181}'  , meterof182 = '${meterof182}'  , meterof183 = '${meterof183}'  , meterof184 = '${meterof184}' ";

	public String SELECT_FIELDS = " fiIdx,  registDate,  pileType,  pileStandard, constructionIdx, fileWeight ,"
			+ "meterof51 , meterof52,  meterof53,  meterof54, "
			+ "meterof61,  meterof62,  meterof63,  meterof64, "
			+ "meterof71,  meterof72,  meterof73,  meterof74, "
			+ "meterof81,  meterof82,  meterof83,  meterof84, "
			+ "meterof91,  meterof92,  meterof93,  meterof94, "
			+ "meterof101,  meterof102,  meterof103,  meterof104, "
			+ "meterof111,  meterof112,  meterof113,  meterof114, "
			+ "meterof121,  meterof122,  meterof123,  meterof124, "
			+ "meterof131,  meterof132,  meterof133,  meterof134, "
			+ "meterof141,  meterof142,  meterof143,  meterof144, "
			+ "meterof151,  meterof152,  meterof153,  meterof154, "
			+ "meterof161,  meterof162,  meterof163,  meterof164, "
			+ "meterof171,  meterof172,  meterof173,  meterof174, "
			+ "meterof181,  meterof182,  meterof183,  meterof184 ";

	//@Insert("INSERT INTO " + TABLE_NAME + " " + INSERT_FIELDS + " VALUES " + INSERT_VALUES)
	@Override
	int insert(FileInventory domain);

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE fiIdx =  #{fiIdx}")
	@Override
	FileInventory get(Integer fiIdx);

	@Override
	@Update("UPDATE  " + TABLE_NAME + " SET " + UPDATE_VALUES  + " WHERE fiIdx =  #{fiIdx}")
	int update(FileInventory domain);

	@Delete("DELETE FROM " + TABLE_NAME + " WHERE fiIdx =  #{fiIdx}")
	@Override
	int delete(Integer id);

	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<FileInventory> getList();
		
	@Override
	List<FileInventory> getListByParam(
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize,
			@Param("param") FileInventoryParam param);
	
	@Override
	int getCountByParam(@Param("param") FileInventoryParam param);
		
	@Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME + " WHERE registDate =  #{registDate} AND constructionIdx = #{constructionIdx} ")
	FileInventory getDate(FileInventory inventory);
	
	
	@Select("SELECT pileType, pileStandard, fileWeight  FROM " + TABLE_NAME + " WHERE constructionIdx = #{constructionIdx} GROUP BY pileType, pileStandard, fileWeight ORDER BY pileType ")
	List<FileInventory> getPileTypeList(FileInventory inventory);

	
	//List<FileInventoryOfChart> getFileInventoryOfChart(FileUsingChartParam param);

	List<FileInventoryOfChart> getFileInventoryOfChart(@Param("constructionIdx") int constructionIdx, @Param("pileType") String pileType, @Param("pileStandard") String pileStandard);
	
	List<FileInventoryOfChart> getFileInventoryOfSteelChart(@Param("constructionIdx") int constructionIdx, @Param("pileType") String pileType, @Param("pileStandard") String pileStandard, @Param("fileWeight") String fileWeight);

	
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE registDate =  #{registDate} and pileType =  #{pileType} and pileStandard =  #{pileStandard} and constructionIdx =  #{constructionIdx} and fileWeight =  #{fileWeight}")
	FileInventory getFileInventory1(@Param("registDate") String registDate ,  @Param("pileType") String pileType,  @Param("pileStandard") String pileStandard, @Param("constructionIdx") int constructionIdx, @Param("fileWeight") String fileWeight);
	
	
	@Select("SELECT * FROM " + TABLE_NAME + " WHERE registDate =  #{registDate} and pileType =  #{pileType} and pileStandard =  #{pileStandard} and constructionIdx =  #{constructionIdx}")
	FileInventory getFileInventory2(@Param("registDate") String registDate ,  @Param("pileType") String pileType,  @Param("pileStandard") String pileStandard, @Param("constructionIdx") int constructionIdx);

}
