package com.practice.TrackZilla.service;

import java.util.List;

import com.practice.TrackZilla.entity.Application;

public interface ApplicationService {

	List<Application> listApplications();
	Application findApplication(long id);
	Application newApplication(Application application);
	Application updateApplication(long id, Application application);
	String deleteApplication(long id);
}