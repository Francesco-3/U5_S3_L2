package francescodicecca.SpringJWT.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import francescodicecca.SpringJWT.entities.Employee;
import francescodicecca.SpringJWT.exceptions.UnauthorizedException;
import francescodicecca.SpringJWT.payloads.LoginDTO;
import francescodicecca.SpringJWT.security.JWTTools;

@Service
public class AuthService {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JWTTools jwtTools;

	public String checkCredentialsAndGenerateToken(LoginDTO body) {
		Employee found = this.employeeService.findByEmail(body.email());

		if (found.getPassword().equals(body.password())) {
			// TODO: Migliorare gestione password
			return jwtTools.createToken(found);
		} else {
			throw new UnauthorizedException("Credenziali errate!");
		}

	}
}
