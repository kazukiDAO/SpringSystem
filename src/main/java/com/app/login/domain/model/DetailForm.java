package com.app.login.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DetailForm {

	@NotBlank
	@Email
	//@Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")
	private String userId;
	
	@NotBlank
	private String userName;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private Date birthDay;
	
	@NotNull
	@Range(min=1,max=150)
	private Integer age;
	
	@NotNull
	@Range(min=0,max=1)
	private Integer marriage;

}
