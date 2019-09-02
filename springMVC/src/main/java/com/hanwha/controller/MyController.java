package com.hanwha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hanwha.model.DeptDAO_Mybatis;
import com.hanwha.model.DeptDTO;
import com.hanwha.model.EmpService;
import com.hanwha.model.EmpVO;

@Controller
public class MyController {
	@Autowired
	DeptDAO_Mybatis dao;
	// DeptDAO dao;

	@Autowired
	EmpService service;

	@RequestMapping("/404")
	public String error404(Model model) {
		model.addAttribute("company", "한화시스템ICT");
		model.addAttribute("manager", "김정아");
		return "error404";
	}

	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		model.addAttribute("company", "한화시스템ICT");
		model.addAttribute("name", "김정아");
		model.addAttribute("phone", "000-0000-0000");
		model.addAttribute("errormessage", ex.getMessage());
		return "error500";
	}

	@RequestMapping("/emp/emplist")
	public ModelAndView selectAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", service.selectAll());
		mv.setViewName("emp/empAll");
		return mv;
	}

	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.GET)
	public ModelAndView selectDetail(int empid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.selectById(empid));
		mv.addObject("managerlist", service.selectAllManager());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("deptlist", dao.selectAll());
		mv.setViewName("emp/empdetail");
		return mv;
	}

	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.POST)
	public String empUpdate(EmpVO emp) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.updateEmp(emp));
		return "redirect:emplist";
	}

	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.GET)
	public ModelAndView empInsert() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("deptlist", dao.selectAll());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("managerlist", service.selectAllManager());
		mv.setViewName("emp/empinsert");
		return mv;
	}

	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.POST)
	public String empInsert(EmpVO emp) {
		service.insertEmp(emp);
		return "redirect:emplist";
	}

	@RequestMapping(value = "/emp/empdelete", method = RequestMethod.GET)
	public String empDeletePost(int empid) {
		service.deleteEmp(empid);
		return "redirect:emplist";
	}

	// RequestMapping��û�� ���� Spring�� �ش� ��û�� �� �Լ��� �����ؼ� ������
	@RequestMapping("/dept/deptlist2")
	public String deptlist3(Model model) {
		model.addAttribute("deptlist", dao.selectAll());
		return "dept/deptlist";
	}

	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.GET)
	public String deptDetailGet(int deptid, Model model) {
		model.addAttribute("dept", dao.selectById(deptid));
		return "dept/deptdetail";
	}

	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.POST)
	public String deptDetailPost(DeptDTO dept) {
		dao.updateDept(dept);
		return "redirect:deptlist2";
	}

	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.GET)
	public String deptInsertGet() {
		return "dept/deptinsert";
	}

	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, HttpServletRequest request) {
		MultipartFile uploadfile = dept.getUploadfile(); // 바이너리 파일
		if (uploadfile == null)
			return "redirect:deptinsert";

		String path = request.getSession().getServletContext().getRealPath("/resources");
		System.out.println("웹서버의 실제 경로 : " + path);
		String fileName = uploadfile.getOriginalFilename();
		String fpath = path + "\\" + fileName;
		dept.setFileName(fileName); // 파일의 경로를 저장한다.
		try {
			// 방법1) FileOutputStream 사용
			// byte[] fileData = file.getBytes();
			// FileOutputStream output = new FileOutputStream(fpath);
			// output.write(fileData);

			// 방법2) File 사용
			File file = new File(fpath);
			uploadfile.transferTo(file); // 파일업로드 끝남
		} catch (IOException e) {
			e.printStackTrace();
		}

		dao.insertDept(dept);
		return "redirect:deptlist2";
	}

	@RequestMapping("/dept/deptdownload")
	public void download(String folder, String file, HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setHeader("Content-Disposition", "attachment;filename=" + file);
		String fullPath = request.getSession().getServletContext().getRealPath(folder + "/" + file);
		FileInputStream fi = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fi.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size);
		}
		fi.close();
		sout.close();
	}

	@RequestMapping(value = "/dept/deptdelete", method = RequestMethod.GET)
	public String deptDeletePost(int deptid) {
		dao.deleteDept(deptid);
		return "redirect:deptlist2";
	}

}
