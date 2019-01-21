package cn.lt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lt.model.User;
import cn.lt.service.UserService;

/**
 * 操作用户信息的Controller
 * @author lt
 * @Date 2017年12月21日 下午2:40:55
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取单用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	public ModelAndView getUser(@PathVariable String id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/userInfo");
		
		int userid = Integer.parseInt(id);
		User user = userService.queryById(userid);
		System.out.println(user);
		mv.addObject("user", user);
		/*user.setName("lt-update");
		userService.update(user);*/
		
		return mv;
	}
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	@RequestMapping(value = "/list")	
	public ModelAndView getUserList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/userInfoList");
		
		List<User> user = userService.queryAll();
		System.out.println(user);
		mv.addObject("users", user);
		
		return mv;
	}

}
