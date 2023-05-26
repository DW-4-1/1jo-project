package kr.or.dw.lecture.service;

import java.util.List;

import kr.or.dw.vo.LectureVO;

public interface ILectureService {

	/**
	 * 학생 강의 목록을 가져오는 메서드
	 * @param id(학생 아이디)
	 * @return	강의목록이 저장될 LecVO 리스트
	 */
	List<LectureVO> getAllLecture(String id);

	/**
	 * 강의등록 메서드
	 * @param lecVo(강의객체)
	 * @return
	 */
	int insertLecture(LectureVO lecVo);

	/**
	 * lec_code 가져오는 메서드
	 * @return
	 */
	String getLecCode();

	/**
	 * 교수 강의 목록 가져오는 메서드
	 * @param staff_id (교수아이디)
	 * @return
	 */
	List<LectureVO> staffLecList(String staff_id);

}
