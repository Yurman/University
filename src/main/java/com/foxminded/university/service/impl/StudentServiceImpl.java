package com.foxminded.university.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Student;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.repository.StudentRepository;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public StudentDto getStudentDtoById(int id) {
        return convertToStudentDto(studentRepository.findById(id));
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        studentRepository.save(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        studentRepository.save(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id);
        student.setDeleted(true);
        studentRepository.save(student);
    }

    @Override
    public void restoreStudent(int id) {
        Student student = studentRepository.findById(id);
        student.setDeleted(false);
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentDto> getAllStudentDto() {
        return convertToListDto(studentRepository.findAll());
    }

    private List<StudentDto> convertToListDto(List<Student> students) {
        return students.stream().map(student -> convertToStudentDto(student)).collect(Collectors.toList());
    }

    private StudentDto convertToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setDeleted(student.isDeleted());
        if (student.getGroup() != null) {
            studentDto.setGroupTitle(student.getGroup().getTitle());
            studentDto.setGroupId(student.getGroup().getId());
        }
        return studentDto;
    }

    private Student convertDtoToStudent(StudentDto studentDto) {
        Student student = (studentDto.getId() != 0) ? studentRepository.findById(studentDto.getId()) : new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        if (studentDto.getGroupId() != 0) {
            student.setGroup(groupRepository.findById(studentDto.getGroupId()));
        }
        return student;
    }

}
