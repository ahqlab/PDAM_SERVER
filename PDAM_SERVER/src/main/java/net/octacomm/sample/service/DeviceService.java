package net.octacomm.sample.service;

import net.octacomm.sample.domain.Device;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;

public interface DeviceService {

	Device login(Device device) throws NotFoundUserException, InvalidPasswordException;
}
