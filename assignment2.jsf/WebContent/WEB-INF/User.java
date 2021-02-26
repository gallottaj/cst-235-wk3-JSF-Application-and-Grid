package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ManagedBean
@ViewScoped
public class User {
	@NotEmpty(message= "First Name cannot be empty!")
	@NotNull(message= "First Name cannot be null!")
	@Size(min=5, max=15, message="First Name must be between 5-15 characters!")
	private String firstName;
	
	@NotEmpty(message= "Last Name cannot be empty!")
	@NotNull(message= "Last Name cannot be null!")
	@Size(min=5, max=15, message="Last Name must be between 5-15 characters!")
	private String lastName;

	public User() {
		firstName ="joey";
		lastName = "gallotta";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
