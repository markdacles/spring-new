import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;	
import org.springframework.web.multipart.MultipartFile;

public class PersonnelController extends MultiActionController{
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private PersonnelService personnelService;
	private RoleService roleService;

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ModelAndView listPersonnel (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("personnelIndex");
		List<Personnel> personnelList = personnelService.listPersonnel();
		mav.addObject("personnelList", personnelList);
		mav.addObject("pact", "manp");
		return mav;
	}

	public ModelAndView deletePersonnel (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/personnelManager");
		personnelService.deletePersonnel(Long.parseLong(request.getParameter("pid")), "Personnel");
		return mav;
	}

}