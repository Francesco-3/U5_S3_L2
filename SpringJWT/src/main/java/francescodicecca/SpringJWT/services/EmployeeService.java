package francescodicecca.SpringJWT.services;

import francescodicecca.TravelManagement.entities.Employee;
import francescodicecca.TravelManagement.exceptions.BadRequestException;
import francescodicecca.TravelManagement.exceptions.NotFoundException;
import francescodicecca.TravelManagement.payloads.NewEmployeePayload;
import francescodicecca.TravelManagement.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //CREATE
    public Employee saveEmployee(NewEmployeePayload payload) {
        this.employeeRepository.findByEmail(payload.getEmail()).ifPresent(employee -> {
            throw new BadRequestException("L'email " + employee.getEmail() + " è già in uso!");
        });

        Employee employee = new Employee();
        employee.setUsername(payload.getUsername());
        employee.setName(payload.getName());
        employee.setSurname(payload.getSurname());
        employee.setEmail(payload.getEmail());
        employee.setAvatarUrl(payload.getAvatarUrl());

        log.info("Dipendente " + employee.getUsername() + " è stato salvato correttamente!");
        return employeeRepository.save(employee);
    }

    //READ
    public Page<Employee> getEmployee(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeRepository.findAll(pageable);
    }

    public Employee findEmployeeById(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }

    //UPDATE
    public Employee findEmployeeByIdAndUpdate(UUID employeeId, Employee payload) {
        Employee found = this.findEmployeeById(employeeId);
        found.setUsername(payload.getUsername());
        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());

        return employeeRepository.save(found);
    }

    //DELETE
    public void findEmployeeByIdAndDelete(UUID employeeId) {
        Employee found = this.findEmployeeById(employeeId);

        employeeRepository.delete(found);
    }
}
