package com.springapp.mvc;

import com.springapp.db.Departments;
import com.springapp.db.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    public static String[] splitString(String queryString, String pattern){
        String[] parts = queryString.split(pattern);
        return parts;
    }

    public static void main(String[] args) {

//        Queries Examples
//        EmployeeDeoImpl edi = new EmployeeDeoImpl();
//        Employee employee = new Employee(68, "Zlatko", "Pejekovic", 11, "Development");
//        edi.insert(employee);
//        Employee employeeSelect = edi.selectById(52);
//        System.out.println(employeeSelect.getId() + ", " + employeeSelect.getName() + ", " + employeeSelect.getSurname()
//         + ", " + employeeSelect.getExperience() + ", " + employeeSelect.getDepartment());
//        edi.delete(68);
//        Employee employeeUpdate = new Employee(65, "Mlado","Kalember", 1, "Development");
//        edi.update(employeeUpdate,65);
//        List<Employee> employees = edi.selectAll();
//        for(Employee e : employees) {
//            System.out.println(e.getId() + ", " + e.getName() + ", " + e.getSurname() + ", " + e.getExperience() + ", " + e.getDepartment());
//        }
//        List<Departments> departments = edi.departments();
//        for(Departments d : departments){
//            System.out.println(d.getDepartment() + ", " + d.getEmployeeCount());
//        }
    }


	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        EmployeeDeoImpl edi = new EmployeeDeoImpl();
        List<Employee> employees = edi.selectAll();
        JSONArray arr = new JSONArray();
        for (Employee e : employees) {
            JSONObject obj = new JSONObject();
            obj.put("id", Integer.toString(e.getId()));
            obj.put("name", e.getName());
            obj.put("surname", e.getSurname());
            obj.put("experience", Integer.toString(e.getExperience()));
            obj.put("department", e.getDepartment());
            arr.add(obj);
        }
        model.addAttribute("message", "All employees");
        model.addAttribute("employees", arr);

        if (request != null) {
            String queryString = "";
            queryString = request.getQueryString();
            if(queryString != null ){
                String pattern = "=|&";
                String[] parts = splitString(queryString, pattern);
                if(parts[0].equals("insertEmpl")){
                    if(parts[3].equals("")){
                        response.addHeader("Refresh", "0; URL=/");
                    }else {
                        Employee employee = new Employee(Integer.parseInt(parts[3]), parts[5], parts[7], Integer.parseInt(parts[9]), parts[11]);
                        edi.insert(employee);
                        response.addHeader("Refresh", "0; URL=/");
                    }

                }else if(parts[0].equals("updateid")){
                    Employee employeeUpdate = new Employee(Integer.parseInt(parts[3]), parts[5], parts[7], Integer.parseInt(parts[9]), parts[11]);
                    edi.update(employeeUpdate, Integer.parseInt(parts[3]));
                    response.addHeader("Refresh", "0; URL=/");

                }else if(parts[0].equals("deleteid")){
                    edi.delete(Integer.parseInt(parts[1]));
                    response.addHeader("Refresh", "0; URL=/");
                }
            }
        }
        return "hello";
    }

	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public String printDepartments(ModelMap model) {
		EmployeeDeoImpl edi = new EmployeeDeoImpl();
        List<Departments> departments = edi.departments();
		JSONArray arr = new JSONArray();
        for(Departments d : departments){
			JSONObject obj = new JSONObject();
			obj.put("department", d.getDepartment());
			obj.put("count", Integer.toString(d.getEmployeeCount()));
			arr.add(obj);
        }
		model.addAttribute("message", "All Departments");
		model.addAttribute("departments", arr);

		return "departments";
	}
}