package com.jdpaley;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path="/add")
    public String addNew(Model m)
            {
                Student student = new Student();
                m.addAttribute("student", student);
                System.out.println(m);
                return "index";
            }
    
    
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String studentValidator(@Valid Student s, BindingResult bindingResult)
    
    {		
    		if (bindingResult.hasErrors())
    		{
    			return "index";
				
    		}else{
    			
    			return "redirect:/valid";
			
    		}
			
    }
    @RequestMapping(path = "/valid")
    public String saveStudent(@ModelAttribute("student") Student st,BindingResult bindingResult, Model m)
    	{
    
    	m.addAttribute("stud", st);
		System.out.println(m);
		studentRepository.save(st);
		return "saved";
		
    	}
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAll()
    {
        return studentRepository.findAll();
    }
}