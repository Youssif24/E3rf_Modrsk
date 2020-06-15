package com.saad.youssif.e3rfmodrsk.View;

import com.saad.youssif.e3rfmodrsk.Model.Student;
import com.saad.youssif.e3rfmodrsk.Model.Teacher;

import java.util.List;

public interface LoginView {
     void showStudentList(List<Student> loginResults);
     void showTeacherList(List<Teacher> loginResults);



}
