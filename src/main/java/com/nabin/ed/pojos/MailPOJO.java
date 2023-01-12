package com.nabin.ed.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Narendra
 * @version 1.0
 * @since 2023-01-12
 */
@Getter
@Setter
@Builder
public class MailPOJO {
    private String to;
    private String subject;
    private String content;
}
