package com.donggun.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donggun.tm.dto.Club;
import com.donggun.tm.service.ClubService;



@RequestMapping("/club")
@Controller
public class ClubController {

	private final ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}

	/**
	 * 클럽 정보 조회 
	 * @param name
	 * @param model
	 * @return
	 */
	@GetMapping("/search.do")
	public String getClub(String name, Model model) {
		System.out.println("#parameter : " + name);
		List<Club> clubList = null;
		
		try {
			clubList = clubService.getClub(name);
			model.addAttribute("clubList", clubList);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "ClubService.getClub() 수행 중 Exception 발생");
			return "errorPage";
		}
		
		return ""; // TODO
	}
	
	/**
	 * 모든 클럽 정보 조회 
	 * @param model
	 * @return
	 */
	@GetMapping("/searchAll.do")
	public String getAllClub(Model model) {
		List<Club> clubList = null;
		
		try {
			clubList = clubService.getAllClub();
			model.addAttribute("clubList", clubList);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "ClubService.getAllClub() 수행 중 Exception 발생");
			return "errorPage";
		}
		
		return ""; // TODO
	}
	
	/**
	 * 클럽 정보 등록 
	 * @param club
	 * @param model
	 * @return
	 */
	@PostMapping("/insert.do")
	public String insertClub(Club club, Model model) {
		System.out.println("#parameter : " + club);
		
		try {
			clubService.insertClub(club);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "ClubService.insertClub() 수행 중 Exception 발생");
			return "errorPage";
		}
		
		return "redirect:/";
	}
	
	/**
	 * 클럽 정보 수정 
	 * @param club
	 * @param model
	 * @return
	 */
	@PostMapping("/update.do")
	public String updateClub(Club club, Model model) {
		System.out.println("#parameter : " + club);
		
		try {
			clubService.updateClub(club);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "ClubService.updateClub() 수행 중 Exception 발생");
			return "errorPage";
		}
		
		return "redirect:/";
	}
	
	/**
	 * 클럽 정보 삭제 
	 * @param club_code
	 * @param model
	 * @return
	 */
	@PostMapping("/delete.do")
	public String deleteClub(int club_code, Model model) {
		System.out.println("#parameter : " + club_code);
		
		try {
			clubService.deleteClub(club_code);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "ClubService.deleteClub() 수행 중 Exception 발생");
			return "errorPage";
		}
		
		return "redirect:/";
	}
}
