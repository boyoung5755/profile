package profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import profile.stack.service.StackService;
import profile.vo.StackVO;

@Controller
public class TestController {
	
	@Inject
	private  StackService service;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, List<StackVO>> testList(){
		
		Map<String, List<StackVO>> map  = new HashMap<>();
		List<StackVO> v =  service.testselect();
		
		map.put("test",v);
		return map;
	}

}
