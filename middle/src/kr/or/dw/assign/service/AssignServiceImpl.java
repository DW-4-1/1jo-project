package kr.or.dw.assign.service;

import java.util.List;

import kr.or.dw.assign.dao.AssignDaoImpl;
import kr.or.dw.assign.dao.IAssignDao;
import kr.or.dw.vo.AssignVO;

public class AssignServiceImpl implements IAssignService{

	private IAssignDao dao;
	private static AssignServiceImpl service;
	
	public static AssignServiceImpl getInstance() {
		if(service == null) {
			service = new AssignServiceImpl();
		}
		return service;
	}
	
	//생성자
	private AssignServiceImpl() {
		dao = AssignDaoImpl.getInstance();
	}

	@Override
	public List<AssignVO> getLectureAssignList(String lec_code) {
		return dao.getLectureAssignList(lec_code);
	}




}