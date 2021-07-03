package com.coffeehouse.web.user.dto;

import com.coffeehouse.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSignUpDto {
    private String id;
    private String pw;
    private String name;

    @Builder
    public UsersSignUpDto(String id, String pw, String name){
        this.id = id;
        this.pw = pw;
        this.name = name;
    }
    
    public Users toEntity(){
        return Users.builder()
                .id(id).pw(pw).name(name).build();
    }
}
