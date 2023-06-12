package com.curdOperation.Service;

import com.curdOperation.Dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto addNewStudent(StudentDto dto);

    StudentDto updateStudent(long id, StudentDto dto);

    List<StudentDto> readAll();

    void deleteStudent(long id);
}
