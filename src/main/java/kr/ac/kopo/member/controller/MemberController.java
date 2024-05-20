package kr.ac.kopo.member.controller;


import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dto.MemberDTO;
import kr.ac.kopo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입 -> 주입을 받는다는건 controller클래스가 service클래스의 자원(필드나메서드)를 사용할 권한이 생긴다. 라고 이해
    private final MemberService memberService; //->이 필드를 매개변수로하는  MemberController()기본생성자 생성

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public  String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){ //@RequestParam("memberEmail") 담아서 String memberEmail 옮겨 담는다
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);

        memberService.save(memberDTO);
        return  "login"; //회원가입이 완료되면 로그인페이지를 띄우겠다.
    }

    @GetMapping("/member/login") // 주소요청이오면
    public  String loginForm(){
        return "login";  //login.html을 보여주자~
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null){
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail()); //loginResult.getMemberEmail()로그인한 회원정보를 세션정보 "loginEmail" 에 담아준다~
            return  "main";
        }else{
            //login 실패
            return  "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){ //findAll은 db에 있는 전체값을 끌어온다~  //Model은 실어나르는 역활을 해줌
       List<MemberDTO> memberDTOList =memberService.findAll();
       //어떠한 html로 가져갈 데이터가 있다면 model을 사용
        model.addAttribute("memberList",memberDTOList);  // "키 : 값" 이라고 이해하면됨
        return "list";
    }


}
