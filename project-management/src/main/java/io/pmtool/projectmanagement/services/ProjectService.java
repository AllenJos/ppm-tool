package io.pmtool.projectmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import io.pmtool.projectmanagement.domain.Project;
import io.pmtool.projectmanagement.exceptions.ProjectIdException;
import io.pmtool.projectmanagement.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return repository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = repository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
			throw new ProjectIdException("Project ID '"+projectId+"' does not exists");
		
		return project;

	}
	
	public Iterable<Project> findAllProjects(){
		return repository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = repository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
			throw new ProjectIdException("Project ID '"+projectId+"' cannot be deleted. It does not exists");
		
		repository.delete(project);
	}	

}
