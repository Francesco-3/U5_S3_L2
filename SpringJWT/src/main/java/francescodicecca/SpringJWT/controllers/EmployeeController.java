package francescodicecca.SpringJWT.controllers;

import francescodicecca.TravelManagement.entities.Employee;
import francescodicecca.TravelManagement.payloads.NewEmployeePayload;
import francescodicecca.TravelManagement.services.EmployeeService;
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

    //GET ALL <- lista dei dipendenti
    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sort
    ) { return this.employeeService.getEmployee(page, size, sort); }

    //GET BY ID <- singolo dipendente
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable UUID employeeId) {
        return this.employeeService.findEmployeeById(employeeId);
    }

    //CREATE <- nuovo dipendente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated NewEmployeePayload payload) {
        return this.employeeService.saveEmployee(payload);
    }

    //UPDATE BY ID <- modifica un dipendente specifico
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody Employee payload) {
        return this.employeeService.findEmployeeByIdAndUpdate(employeeId, payload);
    }

    //DELETE BY ID <- elimina un dipendente specifico
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID employeeId) {
        this.employeeService.findEmployeeByIdAndDelete(employeeId);
    }
}
