package com.globallogic.ox.domain;

import java.io.Serializable;
import java.util.List;

public class Projects implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Project> Projects;

	public List<Project> getProjects() {
		return Projects;
	}

	public void setProjects(List<Project> projects) {
		Projects = projects;
	}
	
}
