package com.springboot.blog.payload;

import lombok.Getter;
import java.util.Date;

@Getter
public class ErrorDetails {

    private Date stampDate;

    private String  message;

    private String detail;

    public ErrorDetails(Date stampDate, String message, String detail) {
        this.stampDate = stampDate;
        this.message = message;
        this.detail = detail;
    }
}
