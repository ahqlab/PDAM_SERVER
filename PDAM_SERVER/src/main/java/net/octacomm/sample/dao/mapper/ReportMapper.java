package net.octacomm.sample.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.ApiReport;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.ConstructionParam;
import net.octacomm.sample.domain.Report;
import net.octacomm.sample.domain.ReportParam;
import net.octacomm.sample.domain.UpdateReport;

@CacheNamespace
public interface ReportMapper extends CRUDMapper<Report, ReportParam, Integer> {

	//public String INSERT_FIELDS = " ( deviceIdx, currentDateTime, location, pileNo , drillingDepth , intrusionDepth, balance, connectLength, managedStandard, avgPenetrationValue, totalPenetrationValue, hammaT, fallMeter  )";

	//public String INSERT_VALUES = " ( #{deviceIdx}, #{currentDateTime}, #{location}, #{pileNo} , #{drillingDepth} , #{intrusionDepth}, #{balance}, #{connectLength}, #{managedStandard}, #{avgPenetrationValue}, #{totalPenetrationValue}, #{hammaT}, #{fallMeter})";

	public String TABLE_NAME = " TB_REPORT ";

	public String UPDATE_VALUES = " pileType = #{pileType} ,"
			+ "method = #{method} , "
			+ "location = #{location}, "
			+ "pileNo = #{pileNo} ,"
			+ "pileStandard = #{pileStandard} , "
			+ "drillingDepth = #{drillingDepth} , "
			+ "intrusionDepth = #{intrusionDepth}  , "  
			+ "balance = #{balance}  ,"
			+ "connectLength = #{connectLength}  , "
			+ "totalConnectWidth = #{totalConnectWidth}  ,"
			+ "managedStandard = #{managedStandard}  , "
			+ "hammaT = #{hammaT}  , "
			+ "fallMeter = #{fallMeter} ,"
			+ "ultimateBearingCapacity = #{ultimateBearingCapacity} , "
			+ "hammaEfficiency = #{hammaEfficiency} , "
			+ "crossSection = #{crossSection} , "
			+ "modulusElasticity = #{modulusElasticity} , "
			+ "avgPenetrationValue = #{avgPenetrationValue} ,"
			+ "totalPenetrationValue = #{totalPenetrationValue} , "
			+ "bigo = #{bigo} ";
	public String SELECT_FIELDS = " id, deviceIdx, currentDateTime, location, pileNo , drillingDepth , intrusionDepth, balance, connectLength, managedStandard, avgPenetrationValue, totalPenetrationValue , hammaT, fallMeter, ultimateBearingCapacity, crossSection , hammaEfficiency , modulusElasticity, bigo ";

	@Override
	int insert(Report report);

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE id =  #{id}")
	@Override
	Report get(Integer id);
	
	@Update("UPDATE  " + TABLE_NAME + " SET " + UPDATE_VALUES  + " WHERE id =  #{id}")
	int update(UpdateReport report);

	@Delete("DELETE FROM " + TABLE_NAME + " WHERE id =  #{id}")
	@Override
	int delete(Integer id);

	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME)
	@Override
	public List<Report> getList();
	
	@Override
	List<Report> getListByParam(
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize,
			@Param("param") ReportParam param);
	
	List<Report> getListByParamExcel(@Param("param") ReportParam param);
	
	@Override
	int getCountByParam(@Param("param") ReportParam param);
	
	@Update("UPDATE " + TABLE_NAME + " SET isDel = 1 where id = #{id}")
	int doDelete(int id);
	
	
	@Update("UPDATE " + TABLE_NAME + " SET isDel = 0 where id = #{id}")
	int doRestore(int id);

	void insert2(Report report);
	
	@Select("SELECT " + 
			" C.name as '?????????', " + 
			" B.machineNumber as  '????????????', " +
			" A.currentDateTime AS '????????????', " +
		    " A.location AS '??????', " +
		    " A.pileNo AS '????????????', " +
		    " A.pileStandard AS '????????????', " +
		    " A.drillingDepth AS '????????????', "+
		    " A.intrusionDepth AS '????????????', "+
		    " A.balance AS '????????????', "+
		    " A.connectLength AS '????????????', "+
		    " A.managedStandard AS '????????????', "+
		    " A.avgPenetrationValue AS '???????????????', "+
		    " A.totalPenetrationValue AS '???????????????', "+
		    " A.hammaT AS '????????????', "+
		    " A.fallMeter AS '????????????', "+
		    " A.pileType AS '????????????', "+
		    " A.method AS '??????', "+
		    " A.totalConnectWidth AS '???????????????', "+
		    " A.ultimateBearingCapacity AS '???????????????', "+
		    " A.crossSection AS '?????????', "+
		    " A.hammaEfficiency AS '????????????', "+
		    " A.modulusElasticity AS '????????????' "+
			" FROM TB_REPORT A , TB_DEVICE B, TB_CONSTRUCTION C " + 
		    " WHERE A.deviceIdx = B.id AND B.constructionIdx = C.id " + 
			" AND A.deviceIdx IN (" + 
			"  SELECT D.id " +
			"           FROM (SELECT * " +
			"                 FROM TB_CONSTRUCTION " +
			"                 WHERE groupIdx = (SELECT idx " +
			"                                   FROM TB_GROUP " +
			"                                   WHERE TB_GROUP.userId = 'hd0001')) C, " +
			"                TB_DEVICE D " +
			"           WHERE C.id = D.constructionIdx) " +
			" ORDER BY A.deviceIdx ASC, A.createDate DESC ")
	List<ApiReport> getApiReport();
	
	
	
	@Select("SELECT count(*) FROM " + TABLE_NAME + " WHERE deviceIdx =  #{id} and isDel = 0")
	int getCount(ReportParam param);
	
	@Select("SELECT COUNT(*) FROM (SELECT * FROM TB_REPORT WHERE isDel = 0) A , (SELECT * FROM TB_DEVICE WHERE isDel = 0) B " + 
			"WHERE A.deviceIdx = B.id " + 
			"AND B.constructionIdx = #{constructionIdx} ")
	int getCountByConstruction(@Param("constructionIdx") int constructionIdx);

}
