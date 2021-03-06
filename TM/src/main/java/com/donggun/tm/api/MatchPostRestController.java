package com.donggun.tm.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donggun.tm.dto.ApplyPost;
import com.donggun.tm.dto.MatchPost;
import com.donggun.tm.service.MatchPostService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/matchPost")
public class MatchPostRestController {

	private final MatchPostService matchPostService;
	
	@Autowired
	public MatchPostRestController(MatchPostService matchPostService) {
		this.matchPostService = matchPostService;
	}

	@GetMapping("")
	public List<MatchPost> getAllMatchPost(@RequestParam Map<String, Object> param) {
		List<MatchPost> matchPostList = null;
		
		try {
			matchPostList = matchPostService.searchMatchPost(param);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return matchPostList;
	}
	
	@GetMapping("/{post_no}")
	public MatchPost detailMatchPost(@PathVariable int post_no) {
		MatchPost matchPost = null;
		
		try {
			matchPost = matchPostService.detailMatchPost(post_no);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			// TODO 사용자에게 어떻게 경고를 날릴 것인가 
		}
		
		return matchPost;
	}
	
	@PostMapping("/regist")
	public int registMatchPost(@RequestBody MatchPost matchPost) {
		int registCnt = 0;
		
		try {
			matchPostService.insertMatchPost(matchPost);
			registCnt = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return registCnt;
	}

	@PostMapping("/modify")
	public int modifyMatchPost(@RequestBody MatchPost matchPost) {
		int modifyCnt = 0;
		
		try {
			matchPostService.updateMatchPost(matchPost);
			modifyCnt = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modifyCnt;
	}
	
	@DeleteMapping("/delete/{post_no}")
	public int deleteMatchPost(@PathVariable int post_no) {
		int deleteCnt = 0;
		
		try {
			matchPostService.deleteMatchPost(post_no);
			deleteCnt = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return deleteCnt;
	}
	
	// Apply MatchPost
	@PostMapping("/registApply")
	public int registApplyMatchPost(@RequestBody ApplyPost applyPost) {
		int registCnt = 0;
		System.out.println(applyPost);
		try {
			matchPostService.insertApplyPost(applyPost);
			registCnt = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return registCnt;
	}
	
	@GetMapping("/searchApplyList/{post_no}")
	public List<ApplyPost> getApplyList(@PathVariable int post_no) {
		List<ApplyPost> applyPostList = null;
		
		try {
			applyPostList = matchPostService.searchApplyPost(post_no);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return applyPostList;
	}
	
	@DeleteMapping("/deleteApply/{apply_id}/{post_no}")
	public int deleteApplyMatchPost(@PathVariable String apply_id, @PathVariable int post_no) {
		int deleteCnt = 0;
		
		Map<String, Object> param = new HashMap<>();
		
		param.put("apply_id", apply_id);
		param.put("post_no", post_no);
		
		try {
			matchPostService.deleteApplyPost(param);
			deleteCnt = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return deleteCnt;
	}

	@PostMapping("/completeMatching")
	public int completeMatching(@RequestBody ApplyPost applyPost) {
		int updateCnt = 0;
		System.out.println(applyPost);
		Map<String, Object> param = new HashMap<>();
		
		param.put("matching_completed", true);
		param.put("matched_apply_no", applyPost.getNo());
		param.put("post_no", applyPost.getPost_no());
		
		try {
			matchPostService.updateApplyPost(applyPost);
			updateCnt++;

			matchPostService.updateMatchPostStatus(param);
			updateCnt++;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return updateCnt;
	}

}
