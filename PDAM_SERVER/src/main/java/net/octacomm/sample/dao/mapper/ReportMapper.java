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

	void insert2(Report report);
	
	@Select("SELECT " + 
			" C.name as '현장명', " + 
			" B.machineNumber as  '기기번호', " +
			" A.currentDateTime AS '시공일자', " +
		    " A.location AS '위치', " +
		    " A.pileNo AS '파일번호', " +
		    " A.pileStandard AS '파일규격', " +
		    " A.drillingDepth AS '천공깊이', "+
		    " A.intrusionDepth AS '관입깊이', "+
		    " A.balance AS '파일잔량', "+
		    " A.connectLength AS '이음개소', "+
		    " A.managedStandard AS '관리기준', "+
		    " A.avgPenetrationValue AS '평균관입량', "+
		    " A.totalPenetrationValue AS '최종관입량', "+
		    " A.hammaT AS '헤머무게', "+
		    " A.fallMeter AS '낙하높이', "+
		    " A.pileType AS '파일종류', "+
		    " A.method AS '공법', "+
		    " A.totalConnectWidth AS '최종관입량', "+
		    " A.ultimateBearingCapacity AS '극한지지력', "+
		    " A.crossSection AS '단면적', "+
		    " A.hammaEfficiency AS '헤머효율', "+
		    " A.modulusElasticity AS '탄성계수' "+
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

}
