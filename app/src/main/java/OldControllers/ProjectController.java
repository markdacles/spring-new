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
import java.util.Collections;

public class ProjectController extends MultiActionController{
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private ProjectService projectService;
	private PersonnelService personnelService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public ModelAndView listProject (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("projectIndex");
		List<Project> projectList = projectService.listProject();
		if("id".equals(request.getParameter("sortby"))) {
            Collections.sort(projectList, (Project a1, Project a2) -> a1.getProjectId().compareTo(a2.getProjectId()) );
        } else if("name".equals(request.getParameter("sortby"))) {
            Collections.sort(projectList, (Project a1, Project a2) -> a1.getProjectName().compareTo(a2.getProjectName()) );
        } else if("start".equals(request.getParameter("sortby"))) {
            Collections.sort(projectList, (Project a1, Project a2) -> a1.getStartDate().compareTo(a2.getStartDate()) );
        } else if("end".equals(request.getParameter("sortby"))) {
            Collections.sort(projectList, (Project a1, Project a2) -> a1.getEndDate().compareTo(a2.getEndDate()) );
        }
		mav.addObject("projectList", projectList);
		mav.addObject("pact", "manj");
		return mav;
	}

	public ModelAndView deleteProject (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/listProject");
		projectService.deleteProject(Long.parseLong(request.getParameter("pid")), "Personnel");
		return mav;
	}

}