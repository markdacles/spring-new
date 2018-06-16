import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProjectValidator implements Validator {

	public boolean supports(Class clazz) {
		return Project.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "error.projectName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.startDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.endDate");
	}

}