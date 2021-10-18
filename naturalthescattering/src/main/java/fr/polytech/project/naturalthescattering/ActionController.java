package fr.polytech.project.naturalthescattering;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/action")
public class ActionController {
	@GetMapping({"test", "test/{testPV}"})
	public String test(@RequestParam(required=false, name="testRP", defaultValue="testRP") String testRP, @PathVariable(required=false, name="testPV") String testPV, Model model) {
		model.addAttribute("testRP", testRP);
		model.addAttribute("testPV", testPV);
		return "actiontest";
	}
}
