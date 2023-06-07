package kr.or.dw.lecture.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.dw.lecture.service.ILectureService;
import kr.or.dw.lecture.service.LectureServiceImpl;
import kr.or.dw.staff.service.IStaffService;
import kr.or.dw.staff.service.StaffServiceImpl;
import kr.or.dw.vo.DateVO;
import kr.or.dw.vo.LectureVO;
import kr.or.dw.web.IAction;

public class LectureRegisterFormAction implements IAction{

	@Override
	public boolean isRedirect() {
		
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Date d = new Date();
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		DateFormat monthFormat = new SimpleDateFormat("MM");
		int lec_year = Integer.parseInt(yearFormat.format(d));
		int lec_term = Integer.parseInt(monthFormat.format(d));

		if(lec_term <= 7 && lec_term != 1) {
			lec_term = 1;
		}else {
			lec_term = 2;
			
		}
		IStaffService staffService = StaffServiceImpl.getInstance();
		
		DateVO yearTerm = new DateVO();
		yearTerm.setTerm(lec_term);
		yearTerm.setYear(lec_year);
		DateVO dateVo = staffService.getDate(yearTerm);
		String start = dateVo.getReg_start().split(" ")[0];
		String end = dateVo.getReg_end().split(" ")[0];
		start = start.split("-")[0] + start.split("-")[1] + start.split("-")[2];
		end = end.split("-")[0] + end.split("-")[1] + end.split("-")[2];
		int startDate = Integer.parseInt(start);
		int endDate = Integer.parseInt(end);
		DateFormat fomatter1 = new SimpleDateFormat("yyyyMMdd");
		int date = Integer.parseInt(fomatter1.format(d));
		
		if(startDate <= date && date <= endDate) {

			HttpSession session = req.getSession();
			String stu_id = (String) session.getAttribute("stu_id");
			
			List<LectureVO> lecList = null;
			
			ILectureService service = LectureServiceImpl.getInstance();
			
			
			LectureVO lecVo = new LectureVO();
			lecVo.setLec_term(lec_term);
			lecVo.setLec_year(lec_year);
			String search = "%%";
			if(req.getParameter("search") != null) {
				search = "%" + req.getParameter("search") + "%";
			}
			lecVo.setSearch(search);
			lecList = service.getAllLectureRegister(lecVo);
			
			List<LectureVO> stuLecList = null;
			stuLecList = service.getAllLecture(stu_id);
			
			List<LectureVO> remove = new ArrayList<>();
			
			String stu_dept_code = "";
			stu_dept_code = service.getStuDeptCode(stu_id);
			
			for(LectureVO lecall : lecList) {
				if(lecall.getLec_div().equals("전공필수") || lecall.getLec_div().equals("전공선택") || lecall.getLec_div().equals("교직")) {
					System.out.println();
					if(!lecall.getDept_code().equals(stu_dept_code)) {
						remove.add(lecall);
						continue;
					}
				}
				for(LectureVO stulec : stuLecList) {
					if(stulec.getLec_code().equals(lecall.getLec_code())) {
						remove.add(lecall);
					}
				}
			}
			lecList.removeAll(remove);
		
			req.setAttribute("lecList", lecList);
			req.setAttribute("titleName", "강의 관리");
			return "/student/lecture/lectureRegister.jsp";
		}else {
			return "/student/lecture/notPeriod.jsp";
		}
		
	}

}
