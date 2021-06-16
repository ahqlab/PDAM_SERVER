package net.octacomm.sample.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.octacomm.sample.dao.mapper.DesignDepthMapper;
import net.octacomm.sample.domain.DesignDepth;
import net.octacomm.sample.domain.DesignDepthParam;
import net.octacomm.sample.service.DesignDepthService;

@RequestMapping("/designDepth")
@Controller
public class DesignDepthController extends AbstractCRUDController<DesignDepthMapper, DesignDepth, DesignDepthParam, Integer>{

	@Autowired
	private DesignDepthService designDepthService;
	
	@Autowired
	@Override
	public void setCRUDMapper(DesignDepthMapper mapper) {
		this.mapper = mapper;	
	}

	@Override
	protected Class<DesignDepth> getDomainClass() {
		return DesignDepth.class;
	}

	@Override
	protected String getRedirectUrl(HttpServletRequest request, HttpSession session) {
		return "redirect:/designDepth/list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
	public List<DesignDepth> uploadExcelFile(@RequestParam("reportFile") MultipartFile file) {
		List<DesignDepth> list = designDepthService.uploadExcelFile(file);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/registList", method = RequestMethod.POST)
	public boolean registList(@RequestBody List<DesignDepth> designDepths) {
		for (DesignDepth designDepth : designDepths) {
			try {
				mapper.insert(designDepth);
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return true;
	}
	
	@RequestMapping(value = "/registAj", method = RequestMethod.GET)
	public void registAj(@ModelAttribute("domainParam") DesignDepthParam param, Model model) throws InstantiationException, IllegalAccessException {
		//model.addAttribute("domain", getDomainClass().newInstance());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public boolean doDelete(@RequestParam("ddIdx") int ddIdx) {
		return mapper.delete(ddIdx) > 0;
	}
	
	//@PostMapping("/singleFileUpload")
	//public String singleFileUpload(@RequestParam("reportFile") MultipartFile file, Model model)
	//throws IOException {

	    // Save mediaFile on system
	    //if (!file.getOriginalFilename().isEmpty()) {
	    //    file.transferTo (new File(DOWNLOAD_PATH + "/" + SINGLE_FILE_UPLOAD_PATH, file.getOriginalFilename()));
	    //    model.addAttribute("msg", "File uploaded successfully.");
	    //} else {  
	    //    model.addAttribute("msg", "Please select a valid mediaFile..");
	   // }
	    //return "fileUploadForm";
	//}
}
