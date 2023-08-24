package com.example.ecommerce.controller;


import com.example.ecommerce.dto.*;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignInResponseDto> Signup(@RequestBody SignupDto signupDto) throws CustomException
    {
        return new ResponseEntity(userService.signUp(signupDto),HttpStatus.OK) ;
    }

    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }

    @GetMapping("/getDetails/{email}")  //to get the user details from the Email ID
    public ResponseEntity<DeatilsDto> details (@PathVariable("email") String email)
    {
        return new ResponseEntity(userService.getAllDetails(email),HttpStatus.OK);
    }

    @GetMapping("/getUserName/{email}") //to get the the user name from the email id
    public ResponseEntity<String> getUserName (@PathVariable("email") String email)
    {
        System.out.println(userService.getAllDetails(email).getFirstName());
        return new ResponseEntity(userService.getAllDetails(email).getFirstName(),HttpStatus.OK);

    }

}
