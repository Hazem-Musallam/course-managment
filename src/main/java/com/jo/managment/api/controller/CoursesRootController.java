package com.jo.managment.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jo.managment.api.entity.Course;
import com.jo.managment.api.model.request.CourseRequest;
import com.jo.managment.api.model.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/api/courses")
public interface CoursesRootController {

	@Operation(summary = "Add course", description = "This api to add a course", tags = { "Admin Action" })
	@PostMapping(value = "/", produces = { "application/json" })
	public BaseResponse add(@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@RequestBody CourseRequest courseRequest);

	@Operation(summary = "update course", description = "This api to Update a course", tags = { "Admin Action" })
	@PutMapping(value = "/{id}", produces = { "application/json" })
	public BaseResponse update(@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@RequestBody CourseRequest courseRequest, @PathVariable(name = "id") int courseId);

	@Operation(summary = "get courses", description = "This api to get courses", tags = { "Admin/User Action" })
	@GetMapping(value = "/", produces = { "application/json" })
	public BaseResponse getCourses(@RequestHeader Map<String, String> headers, HttpServletRequest request);

	@Operation(summary = "update course", description = "This api to delete a course", tags = { "Admin Action" })
	@DeleteMapping(value = "/{id}", produces = { "application/json" })
	public BaseResponse delete(@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@PathVariable(name = "id") int courseId);

}
