import com.exist.model.Role;
import com.exist.model.Person;
import com.exist.model.ContactInformation;
import com.exist.services.PersonService;
import com.exist.services.RoleService;
import com.exist.validations.FormValidation;
import com.exist.util.Util;

import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping(value="/personnel")
public class PersonnelController {
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonnelValidator personnelValidator;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listPersonnel(ModelAndView mav) {

		List<Personnel> personnelList = personnelService.listPersonnel();
		mav.addObject("personnelList", personnelList);
		mav.addObject("pact", "manp");
		mav.setViewName("personnelIndex");
	
		return mav;

	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addPersonnel(ModelAndView mav) {

        List<Roles> roleList = roleService.listRoles();
		mav.addObject("roleList", roleList);
		mav.addObject("pact", "add");
		String url = "addPersonnel?";
		mav.addObject("url", url);
		mav.setViewName("personnelForm");
		return mav;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView savePersonnel(@ModelAttribute("personnel") Personnel p, BindingResult result,
		@RequestParam(value = "contactType", required = false) List<String> contactType,
		@RequestParam(value = "contactDetails", required = false) List<String> contactDetails,
		@RequestParam(value = "checkedRoles", required = false) List<String> roleId) {
		personnelValidator.validate(p, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("personnelForm");
		    mav.addAllObjects(errors.getModel());
		    mav.addObject("roleList", roleService.listRoles());
		    mav.addObject("pact", "add");
		    String url = "addPersonnel?";
		    return mav;
		}
		else {
			ModelAndView mav = new ModelAndView("redirect:/personnel/list");

			if(!contactDetails.isEmpty()){
				for(int i = 0; i < contactType.size(); i++){
					Contact c = new Contact();
					c.setContactType(contactType.get(i));
					c.setContactDetails(contactDetails.get(i));
					p.getContact().add(c);
				}
			}

			if (!roleId.isEmpty()) {
	            for (String id : roleId) {
	               Roles role = roleService.findById(Long.parseLong(id), "Roles");
	               p.getRoles().add(role);
	            }
	        }
	        
			if (p.getId() == 0) {
				personnelService.addPersonnel(p);
			}
			else {
				personnelService.updatePersonnel(p);
			}
			return mav;
		}
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deletePerson(@RequestParam(value="pid", required=true) long id) {
		ModelAndView mav = new ModelAndView("redirect:/personnel/list");
		personnelService.deletePersonnel(id, "Personnel");
		return mav;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updatePerson(@RequestParam(value="pid", required=true) long id) {
		
		ModelAndView mav = new ModelAndView(personnelForm);
		Personnel p = personnelService.findById(Long.parseLong(id, "Personnel"));
		List<Roles> roleList = roleService.listRoles();
		mav.addObject("roleList", roleList);
		mav.addObject("personnel",p);
		mav.addObject("pact", "updateform");
		String url = "updatePersonnel?pid=" + id + "&";
		mav.addObject("url", url);
		return mav;

	}
}