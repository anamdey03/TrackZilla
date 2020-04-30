package com.practice.TrackZilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.TrackZilla.entity.Application;
import com.practice.TrackZilla.exception.ApplicationNotFoundException;
import com.practice.TrackZilla.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Override
	public List<Application> listApplications() {
		return (List<Application>) applicationRepository.findAll();
	}
	
	@Override
    public Application findApplication(long id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);

        if(optionalApplication.isPresent())
            return optionalApplication.get();
        else
            throw new ApplicationNotFoundException("Application Not Found");
    }
	
	@Override
	public Application newApplication(Application application) {
		return applicationRepository.save(application);
	}
	
	@Override
	public Application updateApplication(long id, Application application) {
		Optional<Application> existingApplication = applicationRepository.findById(id);
		if(existingApplication.isPresent()) {
			application.setId(id);
			BeanUtils.copyProperties(application, existingApplication.get());
			return applicationRepository.save(existingApplication.get());
		}
        else
            throw new ApplicationNotFoundException("Application Not Found");
	}
	
	public String deleteApplication(long id) {
		Optional<Application> application = applicationRepository.findById(id);
		if(application.isPresent()) {
			applicationRepository.deleteById(id);
			return "Success";
		}
        else
            throw new ApplicationNotFoundException("Application Not Found");
	}
}
