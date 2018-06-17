import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value="/contact")
public class ContactController {
	
	@Autowired
	private PersonnelService personnelService;
	
	@RequestMapping(value="/list")
	public ModelAndView listPersonsContact(ModelAndView modelAndView,
									@RequestParam(value="prompt", required=false) String prompt) {
		List<Person> persons = personService.listPersons();
		if (persons == null || persons.isEmpty()) {
			modelAndView.setViewName("noPersons");
		}
		else {
			modelAndView.addObject("orderType", "contacts");
			modelAndView.addObject("prompt", prompt);
			modelAndView.addObject("title", "Contact Information");
			modelAndView.addObject("persons", persons);
			modelAndView.setViewName("listPersons");
		}
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addContact(@RequestParam(value="personId", required=true) long id) {
		ContactInformation contactInformation = new ContactInformation();
		ModelAndView modelAndView = new ModelAndView("contactForm");
		modelAndView.addObject("title", "Add Contact Information");
		modelAndView.addObject("personId", id);
		modelAndView.addObject("contactInformation", contactInformation);
		return modelAndView;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updateContact(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		ContactInformation contactInformation = person.getContactInformation();
		ModelAndView modelAndView = new ModelAndView("contactForm");
		modelAndView.addObject("title", "Update Contact Information");
		modelAndView.addObject("personId", id);
		modelAndView.addObject("contactInformation", contactInformation);
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute("contactInformation") ContactInformation contactInformation, 
							BindingResult result,
							@RequestParam(value="personId") long id) {
		ContactFormValidation formValidation = new ContactFormValidation();
		formValidation.validate(contactInformation, result);
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("contactForm");
			modelAndView.addObject("title", "Update Contact Information");
			modelAndView.addObject("personId", id);
			modelAndView.addObject("contactInformation", contactInformation);
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/contact/list");
		Person person = personService.getPersonById(id);
		person.setContactInformation(contactInformation);
		personService.updatePerson(person);
		modelAndView.addObject("prompt", "Successfully Updated a Contact Information!");
		return modelAndView;
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		person.setContactInformation(null);
		personService.updatePerson(person);		
		personService.deleteContact(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/contact/list");
		modelAndView.addObject("prompt", "Successfully Deleted a Contact Information");
		return modelAndView;
	}
	
}