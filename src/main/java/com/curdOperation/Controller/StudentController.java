package com.curdOperation.Controller;

import com.curdOperation.Dto.StudentDto;
import com.curdOperation.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService service;
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto){

        StudentDto added = service.addNewStudent(dto);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudents(@PathVariable("id") long id, @RequestBody StudentDto dto){
        StudentDto saved = service.updateStudent(id, dto);

        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    @GetMapping
    public List<StudentDto> getAllStudent(){
        List<StudentDto> studentDtos = service.readAll();

        return studentDtos;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudents(@PathVariable("id")long id){
        service.deleteStudent(id);

        return new ResponseEntity<>("Record Deleted", HttpStatus.OK);
    }
}
