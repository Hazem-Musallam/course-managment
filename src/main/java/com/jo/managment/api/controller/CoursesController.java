package com.jo.managment.api.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jo.managment.api.entity.Course;
import com.jo.managment.api.entity.services.CourseService;
import com.jo.managment.api.exception.CourseException;
import com.jo.managment.api.exception.ErrorAndSuccessMessagges;
import com.jo.managment.api.model.request.CourseRequest;
import com.jo.managment.api.model.response.BaseResponse;
import com.jo.managment.api.model.response.CourseResponse;

@Component
public class CoursesController implements CoursesRootController {
	@Autowired
	private CourseService coursesService;

	@Override
	public BaseResponse add(Map<String, String> headers, HttpServletRequest request, CourseRequest course) {
		Course findByNumber = coursesService.findByNumber(course.getNumber());
		if (findByNumber != null) {
			throw new CourseException(ErrorAndSuccessMessagges.COURSE_ADDED_BEFORE);
		}
		// TODO here we can do validation on multible things
		if (!validRequest(course)) {
			throw new CourseException(ErrorAndSuccessMessagges.NOT_VALID_REQUEST);
		}
		Course build = Course.builder()//
				.courseName(course.getName())//
				.number(course.getNumber())//
				.created(Instant.now()).build();
		coursesService.save(build);
		return new BaseResponse(getCourseConverted(build));
	}

	private boolean validRequest(CourseRequest course) {
		// TODO do what you want from validations
		return course != null && course.getNumber() != null && !course.getNumber().isEmpty();

	}

	@Override
	public BaseResponse update(Map<String, String> headers, HttpServletRequest request, CourseRequest courseRequest,
			int courseId) {

		if (!validRequest(courseRequest)) {
			throw new CourseException(ErrorAndSuccessMessagges.NOT_VALID_REQUEST);
		}
		Course course = coursesService.findById(courseId);
		Course findByNumber = coursesService.findByNumber(courseRequest.getNumber());

		if (findByNumber != null && findByNumber.getId() != courseId) {
			throw new CourseException(ErrorAndSuccessMessagges.COURSE_ADDED_BEFORE);
		}
		course.setCourseName(courseRequest.getName());
		course.setNumber(courseRequest.getNumber());
		coursesService.save(course);
		return new BaseResponse(getCourseConverted(course));
	}

	@Override
	public BaseResponse getCourses(Map<String, String> headers, HttpServletRequest request) {
		List<Course> findAll = coursesService.findAll();
		List<CourseResponse> collect = findAll.stream().map(this::getCourseConverted).collect(Collectors.toList());

		return new BaseResponse(collect);
	}

	@Override
	public BaseResponse delete(Map<String, String> headers, HttpServletRequest request, int course) {
		Course findById = coursesService.findById(course);

		if (findById == null) {
			throw new CourseException(ErrorAndSuccessMessagges.COURSE_OR_STUDENT_NOT_FOUND);
		}
		coursesService.delete(findById);
		return new BaseResponse("Delete In Progress");
	}

	private CourseResponse getCourseConverted(Course courseRelation) {
		return CourseResponse.builder()//
				.courseName(courseRelation.getCourseName())//
				.courseNumber(courseRelation.getNumber())//
				.id(courseRelation.getId())//
				.build();
	}
}