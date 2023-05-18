package kr.or.dw.student.service;

import kr.or.dw.student.dao.IStudentDao;
import kr.or.dw.student.dao.StudentDaoImpl;

public class StudentServiceImpl implements IStudentService{

	private IStudentDao dao;
	private static StudentServiceImpl service;
	
	public static StudentServiceImpl getInstance() {
		if(service == null) {
			service = new StudentServiceImpl();
		}
		return service;
	}
	
	//생성자
	private StudentServiceImpl() {
		dao = StudentDaoImpl.getInstance();
	}
}
