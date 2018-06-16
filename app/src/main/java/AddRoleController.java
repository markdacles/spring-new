import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;

public class AddRoleController extends SimpleFormController{

	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		ModelAndView mav = new ModelAndView(getFormView());
		List<Roles> rolelist = roleService.listRoles();
		Collections.sort(rolelist, (Roles a1, Roles a2) -> a1.getRoleId().compareTo(a2.getRoleId()) );
		if("role".equals(request.getParameter("sortby"))) {
            Collections.sort(rolelist, (Roles a1, Roles a2) -> a1.getRole().compareTo(a2.getRole()) );
        }
		mav.addObject("rolelist", rolelist);
        mav.addAllObjects(errors.getModel());
		return mav;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception { 
		
		Roles roles = (Roles) command;
		if(errors.hasErrors()) {
			System.out.println("ERRORS FOUND!!!!");
		    ModelAndView mav = new ModelAndView(getFormView());
		    mav.addAllObjects(errors.getModel());
		    List<Roles> rolelist = roleService.listRoles();
			mav.addObject("rolelist", rolelist);
		    return mav;
		} else {
			System.out.println("NO ERRORS FOUND!!!!");
			return onSubmit(request,response,command,errors);		
		}
	}

	@Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        
        Roles r = (Roles) command;
		
		roleService.addRole(r);
        
		return new ModelAndView("redirect:/addRole");
    }
}