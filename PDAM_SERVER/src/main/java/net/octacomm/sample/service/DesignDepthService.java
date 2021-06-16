package net.octacomm.sample.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.octacomm.sample.domain.DesignDepth;

public interface DesignDepthService {

	List<DesignDepth> uploadExcelFile(MultipartFile file);

}
