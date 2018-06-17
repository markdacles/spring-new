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

public class UpdateRoleController extends SimpleFormController{

	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return roleService.findById(Long.parseLong(request.getParameter("roleid")), "Roles");
	}

	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		ModelAndView mav = new ModelAndView(getFormView());
        mav.addAllObjects(errors.getModel());
		Roles r = roleService.findById(Long.parseLong(request.getParameter("roleid")), "Roles");
		mav.addObject("r",r);
		mav.addObject("roleid",request.getParameter("roleid"));
		return mav;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception { 
		
		Roles roles = (Roles) command;
		if(errors.hasErrors()) {
			System.out.println("ERRORS FOUND!!!!");
		    ModelAndView mav = new ModelAndView(getFormView());
		    mav.addObject("roleid",Long.parseLong(request.getParameter("roleid")));
		    mav.addAllObjects(errors.getModel());
		    return mav;
		} else {
			System.out.println("NO ERRORS FOUND!!!!");
			return onSubmit(request,response,command,errors);		
		}
	}

	@Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        
        Roles r = (Roles) command;
        	
		roleService.updateRole(r);
        
		return new ModelAndView("redirect:/addRole");
    }
}