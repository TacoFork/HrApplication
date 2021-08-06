package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeController {

    public static long departmentIdCounter;
    public static long employeeIdCounter;
    ArrayList<Department> departmentList = new ArrayList<>();

    @PostConstruct
    public void initialize(){
        Department hr = new Department("Human Relations");
        departmentIdSetter(hr);
        Department sales = new Department("Sales");
        departmentIdSetter(sales);
        Department accounting = new Department("Accounting");
        departmentIdSetter(accounting);
        departmentList.add(hr);
        departmentList.add(sales);
        departmentList.add(accounting);

    }

    @RequestMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/addDepartment")
    public String departmentForm(Model model){
        Department department = new Department();
        departmentIdSetter(department);
        model.addAttribute("department", department);
        model.addAttribute("action", "/departmentAdded");
        return "adddepartment";
    }

    @PostMapping("/departmentAdded")
    public String departmentAdded(@Valid Department department, BindingResult result, Model model){
        if (result.hasErrors()){
            return "adddepartment";
        }
        else {
            departmentList.add(department);
        }
        return "redirect:/departmentList";
    }

    @GetMapping("/addEmployee")
    public String employeeForm(Model model){
        Employee employee = new Employee();
        employeeIdSetter(employee);
        model.addAttribute("employee", employee);
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("action", "/employeeAdded");
        return "addemployee";
    }

    @PostMapping("/employeeAdded")
    public String employeeAdded(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "addemployee";
        }
        else{
            for (Department department : departmentList){
                if (employee.getDepartmentId() == department.getId()){
                    department.addEmployee(employee);
                    break;
                }
            }
        }
        return "redirect:/departmentList";
    }

    @RequestMapping("/departmentList")
    public String departmentList(Model model){
        model.addAttribute("departmentList", departmentList);
        return "departmentlist";
    }

    @RequestMapping("/viewEmployees/{id}")
    public String viewEmployees(@PathVariable("id") long id, Model model){
        for (Department department : departmentList){
            if (id == department.getId()){
                model.addAttribute("employeeList", department.getEmployeeList());
                model.addAttribute("departmentName", department.getName());
                break;
            }
        }
        return "viewemployees";
    }

    @GetMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("action", "/departmentUpdated");
        for (Department department : departmentList) {
            if (id == department.getId()) {
                model.addAttribute("department", department);
                break;
            }
        }
        return "adddepartment";
    }

    @PostMapping("/departmentUpdated")
    public String departmentUpdated(@Valid Department department, BindingResult Result){
        for (Department department1 : departmentList){
            if (department.getId() == department1.getId()){
                department1.setName(department.getName());
            }
        }
        return "redirect:/departmentList";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("action", "/employeeUpdated");
        for(Department department : departmentList){
            for (Employee employee : department.getEmployeeList()){
                if (employee.getId() == id){
                    model.addAttribute("employee", employee);
                    return "addemployee";
                }
            }
        }
        return "redirect:/departmentList";
    }

    @PostMapping("/employeeUpdated")
    public String employeeUpdated(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "addemployee";
        }
        else {
            for (Department department : departmentList){
                for (Employee employee1 : department.getEmployeeList()) {
                    if (employee1.getId() == employee.getId() || department.getId() == employee.getDepartmentId()) {
//                if (department.getId() == employee.getDepartmentId()){
                        department.updateEmployee(employee);
                        return "redirect:/departmentList";
                    }
                    else if (employee1.getId() == employee.getId()){
                        department.removeEmployee(employee);
                        for (Department department2 : departmentList){
                            if (department2.getId() == employee.getDepartmentId()){
                                department2.addEmployee(employee1);
                                break;
                            }
                        }
                        return "redirect:/departmentList";
                    }
                }
            }
        }
        return "redirect:/departmentList";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true"; //redirect is a keyword redirecting to @RequestMapping("/login") while ?logout is changing logout to true
    }

    static void departmentIdSetter(Department department){
        departmentIdCounter += 1;
        department.setId(departmentIdCounter);
    }

    static void employeeIdSetter(Employee employee){
        employeeIdCounter += 1;
        employee.setId(employeeIdCounter);
    }
}
