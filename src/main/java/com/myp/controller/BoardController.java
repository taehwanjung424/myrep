package com.myp.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myp.domain.BoardVO;
import com.myp.service.BoardService;

@Controller
@RequestMapping(value = "/")
public class BoardController {
	
	@Inject
	private BoardService service; //Service 객체.
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model)throws Exception{
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST) // POST방식으로 내용 전송
	  public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception { // 인자값으로 REDIRECT 사용 	   
		  service.regist(board); // 글작성 서비스 호출	    	    
	    return "redirect:/listAll"; // 작성이 완료된 후, 목록페이지로 리턴
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno")int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr)throws Exception{
		service.modify(board);
		return "redirect:/listAll";
	}
	
	public String removePOST(@RequestParam("bno")int bno, RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		return "redirect:/listAll";
	}
	
}
