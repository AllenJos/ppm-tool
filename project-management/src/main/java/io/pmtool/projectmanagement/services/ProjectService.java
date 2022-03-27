package io.pmtool.projectmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import io.pmtool.projectmanagement.domain.Project;
import io.pmtool.projectmanagement.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	public Project saveOrUpdateProject(Project project) {
		
		return repository.save(project);
	}

}
