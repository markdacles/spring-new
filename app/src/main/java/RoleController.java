import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.bind.ServletRequestDataBinder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;	

public class RoleController extends MultiActionController{
	
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setValidator(new RoleValidator());
	}

	public ModelAndView listRoles (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("roleIndex");
		List<Roles> roleList = roleService.listRoles();
		mav.addObject("roleList", roleList);
		return mav;
	}

	public ModelAndView manageRoles (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("roleForm");
		List<Roles> roleList = roleService.listRoles();
		mav.addObject("roleList", roleList);
		mav.addObject("requestStatus", "Manage Roles");
		return mav;
	}

	public ModelAndView processAction(HttpServletRequest request, HttpServletResponse response, Roles roles) throws Exception{
		//ModelAndView mav = new ModelAndView("roleForm");
		ServletRequestDataBinder binder = createBinder(request, roles);
		binder.bind(request);

		if(binder.getBindingResult().hasErrors()) {
			ModelAndView mav = new ModelAndView("roleForm");
		    mav.addAllObjects(binder.getBindingResult().getModel());
		    return mav;
		} else {
			return addRole(request,response);
		}
	}

	public ModelAndView addRole (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/listRoles");
		String[] ornames = request.getParameterValues("oldroles");
		String[] orid = request.getParameterValues("oldrid");
		String[] nrnames = request.getParameterValues("newroles");
		if(ornames != null){
			for(int i = 0; i < ornames.length; i++){
				Roles r = roleService.findById(Long.parseLong(orid[i]), "Roles");
				r.setRole(ornames[i]);
				roleService.updateRole(r);
			}
		}
		if(nrnames != null) {
			for(int i = 0; i < nrnames.length; i++){
				Roles r = new Roles();
				r.setRole(nrnames[i]);
				roleService.addRole(r);
			}
		}
		return mav;
	}

	public ModelAndView updateRole (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("roleForm");
		Roles r = roleService.findById(Long.parseLong(request.getParameter("roleid")), "Roles");
		mav.addObject("role", r);
		mav.addObject("requestStatus", "Update Role");
		return mav;
	}

	public ModelAndView deleteRole (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/addRole");
		roleService.deleteRole(Long.parseLong(request.getParameter("roleid")), "Roles");
		return mav;
	}
}