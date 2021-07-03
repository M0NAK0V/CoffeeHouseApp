package com.coffeehouse.web.user;

import com.coffeehouse.config.ApiResponse;
import com.coffeehouse.service.UsersService;
import com.coffeehouse.session.UsersInfo;
import com.coffeehouse.web.user.dto.AddrInfoDto;
import com.coffeehouse.web.user.dto.CardInfoDto;
import com.coffeehouse.web.user.dto.UsersSignInDto;
import com.coffeehouse.web.user.dto.UsersSignUpDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "User", description = "\n" +
        "Users management", tags = { "user" })
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UsersApiController {
    private final UsersService usersService;
    private final UsersInfo usersInfo;

    @ApiOperation(value = "\n" +
            "Войти")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersSignInDto usersSignInDto){
        ApiResponse result = null;
        try {
            if (usersService.signin(usersSignInDto) == true){
                result = new ApiResponse(true, "\n" +
                        "success", usersService.signin(usersSignInDto));
                usersInfo.setUserId(usersSignInDto.getId());
                return ResponseEntity.ok().body(result);
            }else {
                result = new ApiResponse(true, "false", usersService.signin(usersSignInDto));
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
          e.printStackTrace();
          result = new ApiResponse(false, e.getMessage(), null);
          return ResponseEntity.badRequest().body(result);
        }
    }


    @ApiOperation(value = "\n" +
            "\n" +
            "Register now")
    @PostMapping("/signup")
    public ResponseEntity<?> signupPosts(@RequestBody UsersSignUpDto usersSignUpDto){
        ApiResponse result = null;
        try {
            if(usersService.findUsersById(usersSignUpDto.getId()) == false){
                result = new ApiResponse(true, "\n" +
                        "success", usersService.signup(usersSignUpDto));
                return ResponseEntity.ok().body(result);
            }else{
                result = new ApiResponse(false, "\n" +
                        "false", usersService.findUsersById(usersSignUpDto.getId()));
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" +
            "\n" +
            "Add card")
    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody CardInfoDto cardInfoDto){
        ApiResponse result = null;
        try {
            cardInfoDto.setUsers(usersService.findUsers(usersInfo));
            result = new ApiResponse(true, "\n" +
                    "success", usersService.addCard(cardInfoDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }

    }

    @ApiOperation(value = "\n" +
            "Delete card")
    @PostMapping("/deleteCard/{cardId}")
    public RedirectView deleteCard(@PathVariable("cardId") String cardid){
        usersService.deleteCard(cardid);
        return new RedirectView("/users/mypage");
    }

    @ApiOperation(value = "\n" +
            "Add address")
    @PostMapping("/addAddr")
    public ResponseEntity<?> addAddr(@RequestBody AddrInfoDto addrInfoDto){
        ApiResponse result = null;
        try {
            addrInfoDto.setUsers(usersService.findUsers(usersInfo));
            result = new ApiResponse(true, "\n" +
                    "success", usersService.addAddr(addrInfoDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" +
            "Delete address")
    @PostMapping("/deleteAddr/{addrUid}")
    public RedirectView deleteAddr(@PathVariable("addrUid") Long uid){
        usersService.deleteAddr(uid);
        return new RedirectView("/users/mypage");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ApiResponse result = null;
        usersInfo.setUserId(null);
        result = new ApiResponse(true, "\n" +
                "success", usersInfo.getUserId());
        return ResponseEntity.ok().body(result);
    }
}
