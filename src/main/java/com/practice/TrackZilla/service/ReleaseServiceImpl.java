package com.practice.TrackZilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.TrackZilla.entity.Release;
import com.practice.TrackZilla.exception.ReleaseNotFoundException;
import com.practice.TrackZilla.repository.ReleaseRepository;

@Service
public class ReleaseServiceImpl implements ReleaseService {

	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Override
	public List<Release> listReleases() {
        return (List<Release>) releaseRepository.findAll();
    }
	
	@Override
    public Release findRelease(long id) {
        Optional<Release> optionalRelease = releaseRepository.findById(id);

        if(optionalRelease.isPresent())
            return optionalRelease.get();
        else
            throw new ReleaseNotFoundException("Release Not Found");
    }
	
	@Override
	public Release newRelease(Release release) {
		return releaseRepository.save(release);
	}
	
	@Override
	public Release updateRelease(long id, Release release) {
		Optional<Release> existingRelease = releaseRepository.findById(id);
		if(existingRelease.isPresent()) {
			release.setId(id);
			BeanUtils.copyProperties(release, existingRelease.get());
			return releaseRepository.save(existingRelease.get());
		}
        else
            throw new ReleaseNotFoundException("Release Not Found");
	}

	@Override
	public String deleteRelease(long id) {
		Optional<Release> release = releaseRepository.findById(id);
		if(release.isPresent()) {
			releaseRepository.deleteById(id);
			return "Success";
		}
        else
            throw new ReleaseNotFoundException("Release Not Found");
	}
}
