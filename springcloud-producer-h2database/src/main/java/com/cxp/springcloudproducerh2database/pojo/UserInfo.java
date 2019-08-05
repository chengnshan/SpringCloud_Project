package com.cxp.springcloudproducerh2database.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1086859637435185313L;

    private Integer id;
    private String username;
    private String password;
    private String userSex;
    private String nickName;
    private Date birthday;
    private String jobs;
}
