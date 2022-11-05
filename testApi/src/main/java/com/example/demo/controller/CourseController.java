package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

import lombok.AllArgsConstructor;

@RequestMapping("/courses")
@RestController
@AllArgsConstructor
public class CourseController {
	
	private CourseService courseService;
	
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		
		return ResponseEntity.ok(courseService.findAllCourses());
	}
	
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourseById (@PathVariable Long courseId) {
		
		return ResponseEntity.ok(courseService.findCourseById(courseId));
	}
	
	@GetMapping("/queue/{way}")
	public ResponseEntity<List<Course>> getCoursesByWay(@PathVariable String way) {
		
		return ResponseEntity.ok(courseService.findCourseByWay(way));
	}
	
	@PostMapping
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
	}
}