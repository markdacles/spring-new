import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProjectController extends SimpleFormController{

	private ProjectService projectService;
	private PersonnelService personnelService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
            dateFormat, true));
    }

	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		ModelAndView mav = new ModelAndView(getFormView());
        mav.addAllObjects(errors.getModel());
		mav.addObject("personnelList", personnelService.listPersonnel());
		mav.addObject("pact", "addj");
		String url = "addProject?";
		mav.addObject("url", url);
		return mav;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception { 
		Project project = (Project) command;
		if(errors.hasErrors()) {
			System.out.println("ERRORS FOUND!!!!");
		    ModelAndView mav = new ModelAndView(getFormView());
		    mav.addAllObjects(errors.getModel());
		    mav.addObject("personnelList", personnelService.listPersonnel());
		    mav.addObject("pact", "add");
		    return mav;
		} else {
			System.out.println("NO ERRORS FOUND!!!!");
			return onSubmit(request,response,command,errors);		
		}

	}

	@Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        
        Project project = (Project) command;

        System.out.println("project name:" + project.getProjectName());

		String[] assigned = request.getParameterValues("lstBox2");

		if(assigned != null){
			for(String id : assigned){
				Personnel p = personnelService.findById(Long.parseLong(id), "Personnel");
				project.getPersonnel().add(p);
			}
		}

		projectService.addProject(project);  
        
		return new ModelAndView("redirect:/listProject");
    }
}