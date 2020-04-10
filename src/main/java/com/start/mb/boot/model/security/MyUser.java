package com.start.mb.boot.model.security;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wll
 * @desc MyUser
 * @link
 * @date 2020/4/8 4:35 下午
 */
@Data
@EqualsAndHashCode
public class MyUser implements Serializable {

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;


}
