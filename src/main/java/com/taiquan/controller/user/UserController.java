package com.taiquan.controller.user;

import com.taiquan.bean.UserBean;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.service.*;
import com.taiquan.utils.DESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

@Controller
@SessionAttributes(value = {"user"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ManagerService managerService;
    @Autowired
    QueryCustomerService queryCustomerService;
    @Autowired
    AUDCustomerService audCustomerService;
    @Autowired
    OrderService orderService;
    //系统正式开通后，下面的部分会被关闭
    @RequestMapping(value = "/registermgr",method = RequestMethod.GET)
    public String registerMgr(ModelMap modelMap){
        modelMap.addAttribute("manager",new Manager());
        return "/user/registermgr";
    }

    //系统正式开通后，下面的部分会被关闭
    @RequestMapping(value = "/registermgr",method = RequestMethod.POST)
    public String registerMgr(Manager manager){
        managerService.registerMrg(manager);
        return "redirect:/user/login.html";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(ModelMap modelMap){
        //List<Manager> managers = managerService.
        modelMap.addAttribute("user1",new User());
        List<Manager> managers = managerService.allManagerNames();
        List<String> mgrnames = new ArrayList<>();
        for(Manager manager : managers){
            mgrnames.add(manager.getName());
        }
        modelMap.addAttribute("mgrnames",mgrnames);
        return "/user/register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(User user1,@RequestParam String mgrname) throws NoSuchObjectException{
        Manager manager = managerService.getManagerByName(mgrname);
        user1.setManager(manager);
        userService.register(user1);
        return  "redirect:/user/login.html";
    }

    //管理员和用户都通过该页面登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap modelMap,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")){
                    user.setUsername(DESUtils.getDecryptString(cookie.getValue()));
                }else if (cookie.getName().equals("password")){
                    println(DESUtils.getDecryptString(cookie.getValue()));
                    user.setPassword(DESUtils.getDecryptString(cookie.getValue()));
                }else if (cookie.getName().equals("mgr")){
                    user.setMgr(Boolean.getBoolean(cookie.getValue()));
                }
            }
        }
        modelMap.addAttribute("user",user);
        return "/user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user,HttpServletResponse response, HttpSession session) throws NoSuchObjectException, IOException {
        Cookie name,pass,role;
        ModelMap mm = new ModelMap();
        String url = (String) session.getAttribute("beforeUrl");
        session.setAttribute("textures",orderService.loadAllTexture());
        if (user.isMgr()){
            Manager manager =managerService.login(user);
            mm.addAttribute("username",manager.getUsername());
            session.setAttribute("username",manager.getUsername());
            session.setAttribute("user",manager);
            name = new Cookie("username", DESUtils.getEncryptString(manager.getUsername()));
            pass = new Cookie("passord",DESUtils.getEncryptString(manager.getPassword()));
            role = new Cookie("mgr","true");
        }else {
            User user1 =userService.login(user);
            mm.addAttribute("username", user1.getUsername());
            session.setAttribute("username",user1.getUsername());
            session.setAttribute("user",user1);
            name = new Cookie("username", DESUtils.getEncryptString(user1.getUsername()));
            pass = new Cookie("passord",DESUtils.getEncryptString(user1.getPassword()));
            role = new Cookie("mgr","false");
        }
        name.setMaxAge(7*24*60*60);pass.setMaxAge(7*24*60*60);role.setMaxAge(7*24*60*60);
        name.setSecure(false);pass.setSecure(false);role.setSecure(false);

        String path = session.getServletContext().getContextPath() + "/";
        name.setPath(path);
        pass.setPath(path);
        role.setPath(path);

        response.addCookie(name);
        response.addCookie(pass);
        response.addCookie(role);

        if(url != null){
            //拦截POST请求
            //出现问题，因为伪造的请求，没有携带modelBean，解决方式是伪造一个bean，可能涉及到的东西比较多
            //解决方式：
            // 1、暂时把所有的Post请求都转到主页；
            // 2、或者，转到提交前的页面；
            //3、记录当前浏览器的地址，重新请求之前的页面,在每个controller中记录当前的controller；
            /*if (session.getAttribute("paramMap")!=null) {
                MyHttpClient client = new MyHttpClient(response);
                client.setParamMap((Map<String, String[]>) session.getAttribute("paramMap"));
                client.sendByPost(session.getServletContext().getContextPath() + url);
                return null;
            }*/

            //暂时把所有的Post请求都转到主页；
                // 是否可以在地址栏中加入参数呢？
            if(session.getAttribute("paramMap")!=null) {
                return "redirect:/company/addCompany.html";
                //这种方式让会产生get与post的问题

                /*Map<String,String[]> params = (Map<String, String[]>) session.getAttribute("paramMap");
                attributes.addAllAttributes(params);
                session.removeAttribute("paramMap");
                println("**************************************************************************");
                println(request.getHeader("method"));
                println("**************************************************************************");
                return "redirect:" + url;*/
            }
            return "redirect:" + url;
        }else{
            return "redirect:/company/addCompany.html";
        }
    }

    @RequestMapping(value = "/passUser",method = RequestMethod.GET)
    public ModelAndView passUser(HttpSession session) throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        Manager manager = managerService.getManagerByUsername(username);
        List<User> users = managerService.getUsers(manager);
        ModelAndView mm = new ModelAndView();
        mm.addObject("users",users);
        mm.setViewName("/user/userList");
        return mm;
    }
    @RequestMapping(value = "/passUser/{userId}",method = RequestMethod.GET)
    public ResponseEntity passUser(HttpSession session, @PathVariable int userId) throws NoSuchObjectException {
        User user1 = userService.getUser(userId);
        managerService.passUser(user1);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/userDetails/{userId}",method = RequestMethod.GET)
    public ResponseEntity<UserBean> userDetail(@PathVariable int userId){
        User user1 = userService.getUser(userId);
        UserBean userBean = new UserBean();
        userBean.setUserId(user1.getUserId());
        userBean.setUsername(user1.getUsername());
        userBean.setName(user1.getName());
        userBean.setPhoneNumber(user1.getPhoneNumber().getNumbers());
        return new ResponseEntity<>(userBean,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) throws NoSuchObjectException {
        User user1 = userService.getUser(userId);
        Manager manager = user1.getManager();
        List<Customer> customers = queryCustomerService.getAllCustomer(user1,1, 1).getAllData();
        for (Customer customer:customers){
            customer.setUser(user1);
            audCustomerService.updateCustomer(customer);
        }
        List<Order> orders = orderService.getOrderByUser(user1);
        for (Order order :orders){
            order.setUser(user1);
            orderService.updateOrder(order);
        }
        user1.setPassed(false);
        userService.updateUser(user1);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/login.html";
    }
    @ExceptionHandler(NoSuchObjectException.class)
    public ModelAndView handlerNuSuchObject(NoSuchObjectException e, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user",new User());
        modelAndView.addObject("msg",e.getMsg());
        return modelAndView;
    }

}
