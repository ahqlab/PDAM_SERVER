package net.octacomm.sample.service;

import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService{
	
	@Autowired
	DeviceMapper deviceMapper;

	@Override
	public Device login(Device device) throws NotFoundUserException, InvalidPasswordException {
		if (deviceMapper.getFindByTabletNo(device.getTabletNo()) == null) {
			throw new NotFoundUserException();
		}
		Device result = deviceMapper.getFindByTabletNoAndPassword(device);
		if (result == null) {
			throw new InvalidPasswordException();
		}
		return result;
	}

}
