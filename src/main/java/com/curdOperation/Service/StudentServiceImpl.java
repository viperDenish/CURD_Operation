package com.curdOperation.Service;

import com.curdOperation.Dto.StudentDto;
import com.curdOperation.Entity.Student;
import com.curdOperation.Exception.ResourceNotFoundException;
import com.curdOperation.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repo;
    @Override
    public StudentDto addNewStudent(StudentDto dto) {
        Student student = mapToEntity(dto);

        Student save = repo.save(student);

        return mapToDto(save);
    }

    @Override
    public StudentDto updateStudent(long id, StudentDto dto) {
         repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student data does not exists")
        );

        Student updatedData = mapToEntity(dto);
        updatedData.setId(id);

        Student save = repo.save(updatedData);


        return mapToDto(save);
    }

    @Override
    public List<StudentDto> readAll() {
        List<Student> all = repo.findAll();

        List<StudentDto> dtoList = all.stream().map(student -> mapToDto(student)).collect(Collectors.toList());


        return dtoList;
    }

    @Override
    public void deleteStudent(long id) {
         repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student does not exists")
        );

        repo.deleteById(id);
    }


    private Student mapToEntity(StudentDto dto){
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setRollNumber(dto.getRollNumber());

        return student;
    }

    private StudentDto mapToDto(Student student){
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRollNumber(student.getRollNumber());
        return dto;
    }
}
