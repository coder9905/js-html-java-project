package uz.zako.lesson_42.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.zako.lesson_42.entity.entity.Admin;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.service.AdminService;
import uz.zako.lesson_42.service.DistrictService;
import uz.zako.lesson_42.service.ProvinceService;
import uz.zako.lesson_42.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   UserService userService;

   @Autowired
    DistrictService districtService;

   @Autowired
    ProvinceService provinceService;

   @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {

        Cookie[] cookies = request.getCookies();
        String email = null;
        Base64 base64 = new Base64();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("user")) {
                    email = new String(Base64.decodeBase64(cookies[i].getValue()));
                }
            }
        };

        if (email == null) {
            return "auth/login";
        }

        User user = userService.getByEmail(email);
        if (user == null) return "auth/login";

        model.addAttribute("user", user);
        return "cabinetUser/cabinetUser";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return ("auth/login");
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp, Model model) {

        String email = req.getParameter("email");

        String password = req.getParameter("password");

        User user = userService.getUser(email, password);

        Admin admin = adminService.getAllAdmin();

        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            System.out.println(admin.toString());
            return "cabinet/cabinet";
        } else {

            if (user == null) {
                model.addAttribute("isError", "Email yoki password xato !!!");
                return "auth/login";
            }
            Cookie cookie = new Cookie("user", Base64.encodeBase64String(user.getEmail().getBytes()));
//        sekund
            cookie.setPath("/");
            cookie.setMaxAge(36000);
            resp.addCookie(cookie);

            return "cabinetUser/cabinetUser";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest req, HttpServletResponse resp, Model model) {

        String email = req.getParameter("email");
        short age = Short.parseShort(req.getParameter("age"));
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String phone=req.getParameter("phone");
        String rePassword = req.getParameter("rePassword");
        Long districtId = Long.valueOf(req.getParameter("regionId"));

//        String district=districtService.getRegionId(districtId).getName();
//        String province=provinceService.getProvinceId(districtService.getRegionId(districtId).getProvinceId()).getName();
//        System.out.println(district+" = "+province);
//        System.out.println(districtService.getRegionId(districtId));

        System.out.println(age + email + fullName + password + rePassword);
        if (!rePassword.equals(password)) {
            req.setAttribute("isPasswordEqual", "Passwordlar bir xil emas!");
        }

        User user = new User(
                fullName,
                email,
                password,
                districtId,
                age,
                phone
        );

        System.out.println(user.toString());

        boolean isSuccess = userService.saveUser(user);

        if (!isSuccess) {
            model.addAttribute("error", "biron nimada xatolik:(");
            return "auth/register";
        }

        model.addAttribute("user", user);
        return "auth/login";

    }
}
