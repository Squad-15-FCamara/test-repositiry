package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class CourseService {
	
	private CourseRepository courseRepository;
	
	public List<Course> findAllCourses() {
		
		return courseRepository.findAll();
	}
	
	public Course findCourseById(Long courseId) {
		
		return courseRepository.findById(courseId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Esta aula nao existe"));
	}
	
	public Course saveCourse(Course course) {
		
		courseRepository.save(course);
		return course;
	}
	
	public List<Course> findCourseByWay(String way) {
		
		return courseRepository.findByWay(way);
	}
	
	public void deleteCourseById(Long courseId) {
		
		courseRepository.deleteById(courseId);
	}
	
}