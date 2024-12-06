package com.xml.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.xml.project.model.generated.Employee;
import com.xml.project.service.EmployeeService;
import com.xml.project.service.EmployeeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.awt.PageAttributes.MediaType;
import java.util.List;
@Controller
@RequestMapping("/elaa")
public class Elaa {

	@GetMapping()
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);

		return "greeting";
	}
	





}