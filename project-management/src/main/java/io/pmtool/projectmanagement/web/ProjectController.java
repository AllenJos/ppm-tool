package io.pmtool.projectmanagement.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pmtool.projectmanagement.domain.Project;
import io.pmtool.projectmanagement.services.MapValidationErrorService;
import io.pmtool.projectmanagement.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	MapValidationErrorService validationService;
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errorMap = validationService.mapValidationService(result);
		
		if(errorMap!=null) return errorMap;
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> findProject(@PathVariable String projectId){
		
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> findAllProjects(){
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId);
		return new ResponseEntity<String>("Project '"+projectId+ "' was deleted", HttpStatus.OK);
	}
	

}
