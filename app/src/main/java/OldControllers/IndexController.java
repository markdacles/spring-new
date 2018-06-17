import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.AbstractController;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends AbstractController{
	
	public ModelAndView handleRequestInternal (HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("index");
	}

}