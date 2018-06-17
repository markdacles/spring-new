import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonnelValidator implements Validator {

	public boolean supports(Class clazz) {
		return Personnel.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.fname", "error.name.fname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.mname", "error.name.mname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.lname", "error.name.lname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.brgy", "error.address.barangay");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.address.city");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "error.birthday");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gwa", "error.gwa");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateHired", "error.dateHired");

		Personnel personnel = (Personnel) target;
		Double gwa = personnel.getGwa();

		if (gwa != null) {
			if (gwa < 1 || gwa > 5) {
				errors.rejectValue("gwa", "gwa.invalidValue");
			}
		}

	}

}