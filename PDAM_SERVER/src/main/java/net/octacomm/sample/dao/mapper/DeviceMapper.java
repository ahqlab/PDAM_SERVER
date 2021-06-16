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
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.DeviceParam;

@CacheNamespace
public interface DeviceMapper extends CRUDMapper<Device, DeviceParam, Integer>{
	
	public String INSERT_FIELDS = " ( lavelNo, machineNumber, constructionIdx, bluetoothNo, tabletNo,  password , tabletManager , weContact ,  startDate, endDate )";
	
	public String INSERT_VALUES = " ( #{lavelNo}, () #{constructionIdx}, #{bluetoothNo}, #{tabletNo},  #{password} , #{tabletManager} , #{weContact} ,  #{startDate}, #{endDate} )";
	
	public String TABLE_NAME = " TB_DEVICE ";
	
	public String UPDATE_VALUES = " lavelNo = #{lavelNo} , machineNumber = #{machineNumber} , constructionIdx = #{constructionIdx} , bluetoothNo = #{bluetoothNo} , tabletNo = #{tabletNo}, lavelNo = #{lavelNo} , password = #{password} , password = #{password} , tabletManager = #{tabletManager} , startDate = #{startDate}, endDate = #{endDate} ";
	
	public String SELECT_FIELDS = " id, lavelNo, machineNumber , weContact , constructionIdx, bluetoothNo, tabletNo, lavelNo , password , tabletManager , conduct , date_format(startDate, '%Y-%m-%d') as startDate , date_format(endDate, '%Y-%m-%d') as endDate ";
	
	@Override
	int insert(Device domain);
		
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME + " WHERE id =  #{id} and isDel = 0 ")
	@Override
	Device get(Integer id);
	
	@Override
	int update(Device domain);
	
	
	@Delete("DELETE FROM " + TABLE_NAME + " WHERE id =  #{id}")
	@Override
	int delete(Integer id);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME + " WHERE isDel = 0 ")
	@Override
	public List<Device> getList();
	//end default
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME+ " WHERE tabletNo =  #{tabletNo} and isDel = 0 ")
	List<Device> getFindByTabletNo(String tabletNo);
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME+ " WHERE tabletNo =  #{tabletNo} and password = #{password} and isDel = 0 ")
	Device getFindByTabletNoAndPassword(Device device);
	
	
	
	
	@Update("UPDATE " + TABLE_NAME + " SET isDel = 1 where id = #{id}")
	int doDelete(int id);
	
	
	@Select("SELECT" + SELECT_FIELDS + "FROM " + TABLE_NAME+ " WHERE constructionIdx =  #{id} and isDel = 0 ")
	List<Device> getFindByConstructionIdx(int id);
	
	
	@Update("UPDATE " + TABLE_NAME + " SET conduct = #{conduct} where id = #{id}")
	int updateConduct(@Param("id") int id, @Param("conduct") int conduct);

}
