package com.practice.TrackZilla.service;

import java.util.List;

import com.practice.TrackZilla.entity.Release;

public interface ReleaseService {

	List<Release> listReleases();
	Release findRelease(long id);
	Release newRelease(Release release);
	Release updateRelease(long id, Release release);
	String deleteRelease(long id);
}