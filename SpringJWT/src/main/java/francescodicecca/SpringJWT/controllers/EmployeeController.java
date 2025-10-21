package francescodicecca.SpringJWT.controllers;

import francescodicecca.SpringJWT.entities.Employee;
import francescodicecca.SpringJWT.payloads.NewEmployeePayload;
import francescodicecca.SpringJWT.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sort
    ) { return this.employeeService.getEmployee(page, size, sort); }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable UUID employeeId) {
        return this.employeeService.findEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated NewEmployeePayload payload) {
        return this.employeeService.saveEmployee(payload);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody Employee payload) {
        return this.employeeService.findEmployeeByIdAndUpdate(employeeId, payload);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID employeeId) {
        this.employeeService.findEmployeeByIdAndDelete(employeeId);
    }
}
