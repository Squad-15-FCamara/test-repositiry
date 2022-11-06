package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/courses")
@RestController
@AllArgsConstructor
public class CourseController {

	private CourseService courseService;
	private CourseRepository courseRepository;

	@ApiOperation(value = "fetch all courses")
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {

		return ResponseEntity.ok(courseService.findAllCourses());
	}

	@ApiOperation(value = "fetch a course by ID")
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {

		return ResponseEntity.ok(courseService.findCourseById(courseId));
	}

	@ApiOperation(value = "fetch a list of courses with same topic")
	@GetMapping("/queue/{way}")
	public ResponseEntity<List<Course>> getCoursesByWay(@PathVariable String way) {

		return ResponseEntity.ok(courseService.findCourseByWay(way));
	}

	@ApiOperation(value = "create a course")
	@PostMapping
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {

		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "delete a course by ID")
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> removeCourse(@PathVariable Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			return ResponseEntity.notFound().build();
		}
		
		courseService.deleteCourseById(courseId);
		
		return ResponseEntity.noContent().build();
	}
	
}