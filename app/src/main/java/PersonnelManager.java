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
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;

public class PersonnelManager extends SimpleFormController{

	private PersonnelService personnelService;
	private RoleService roleService;

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
            dateFormat, true));
    }

	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		ModelAndView mav = new ModelAndView("personnelIndex");
		List<Personnel> personnelList = personnelService.listPersonnel();

		String nameFilter = request.getParameter("nameFilter");

		if(nameFilter != null) {
			personnelList = personnelService.searchFor(nameFilter);
		}

		if("id".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getId().compareTo(a2.getId()) );
        } else if("name".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getName().getLname().compareTo(a2.getName().getLname()) );
        } else if("address".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getAddress().getBrgy().compareTo(a2.getAddress().getBrgy()) );
        } else if("bday".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getBirthday().compareTo(a2.getBirthday()) );
        } else if("gwa".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getGwa().compareTo(a2.getGwa()) );
        } else if("datehired".equals(request.getParameter("sortby"))) {
            Collections.sort(personnelList, (Personnel a1, Personnel a2) -> a1.getDateHired().compareTo(a2.getDateHired()) );
        }

		mav.addObject("personnelList", personnelList);
		mav.addObject("pact", "manp");
		return mav;
	}

	// public ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception { 
	// 	Personnel personnel = (Personnel) command;
	// 	if(errors.hasErrors()) {
	// 		System.out.println("ERRORS FOUND!!!!");
	// 	    ModelAndView mav = new ModelAndView(getFormView());
	// 	    mav.addAllObjects(errors.getModel());
	// 	    mav.addObject("roleList", roleService.listRoles());
	// 	    mav.addObject("pact", "add");
	// 	    return mav;
	// 	} else {
	// 		System.out.println("NO ERRORS FOUND!!!!");
	// 		return onSubmit(request,response,command,errors);		
	// 	}

	// }

	@Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors){
        
        try {
	        System.out.println("YOW");
		    FileUpload fileUpload = (FileUpload) command;
	        MultipartFile file = fileUpload.getFile();
			Personnel p = new Personnel();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String content = new String(file.getBytes());

			String lines[] = content.split("\\r?\\n");

			String line0[] = lines[0].split(",");

			int i = 0;

			Name name = new Name();
			Address address = new Address();

			if(line0[i] != "") {
				name.setLname(line0[i++]);
			}
			if(line0[i] != "") {
				name.setFname(line0[i++]);
			}
			if(line0[i] != "") {
				name.setMname(line0[i++]);
			}
			if(line0[i] != "") {
				p.setName(name);
			}
			if(line0[i] != "") {
				address.setBrgy(line0[i++]);
			}
			if(line0[i] != "") {
				address.setCity(line0[i++]);
			}
			if(line0[i] != "") {
				p.setAddress(address);
			}
			if(line0[i] != "") {
				p.setBirthday(formatter.parse(line0[i++]));
			}
			if(line0[i] != "") {
				p.setGwa(Double.parseDouble(line0[i++]));
			}
			if(line0[i] != "") {
				p.setDateHired(formatter.parse(line0[i]));
			}

			String line1[] = lines[1].split(",");

			for(String str : line1) {
				Contact c = new Contact();
				c.setContactType("Landline");
				c.setContactDetails(str);
				p.getContact().add(c);
			}

			String line2[] = lines[2].split(",");


			for(String str : line2) {
				Contact c = new Contact();
				c.setContactType("Mobile");
				c.setContactDetails(str);
				p.getContact().add(c);
			}

			String line3[] = lines[3].split(",");


			for(String str : line3) {
				Contact c = new Contact();
				c.setContactType("Email");
				c.setContactDetails(str);
				p.getContact().add(c);
			}

			String line4[] = lines[4].split(",");

			List<Roles> roleList = roleService.listRoles();
			for(String str : line4) {
				for(Roles r : roleList) {
					if(str.equals(r.getRole())) {
						p.getRoles().add(r);
					}
				}
			}

			personnelService.addPersonnel(p);
	        
			return new ModelAndView("redirect:/personnelManager");
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("personnelIndex");
			List<Personnel> personnelList = personnelService.listPersonnel();
			mav.addObject("personnelList", personnelList);
			mav.addObject("pact", "manp");
			mav.addObject("fileerror", "Invalid file. Try again!");
			return mav;
		}
    }
}