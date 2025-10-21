package francescodicecca.SpringJWT.controllers;

import francescodicecca.SpringJWT.payloads.NewEmployeePayload;
import francescodicecca.SpringJWT.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import francescodicecca.SpringJWT.entities.Employee;
import francescodicecca.SpringJWT.exceptions.ValidationException;
import francescodicecca.SpringJWT.payloads.LoginDTO;
import francescodicecca.SpringJWT.payloads.LoginResponseDTO;
import francescodicecca.SpringJWT.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private EmployeeService usersService;

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody LoginDTO body) {
		return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody @Validated NewEmployeePayload payload, BindingResult validationResult) {
		if (validationResult.hasErrors()) {

			throw new ValidationException(validationResult.getFieldErrors()
					.stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
		}
		return this.usersService.save(payload);
	}

}
