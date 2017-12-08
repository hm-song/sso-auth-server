package hm.song.practice.web.dto;

import lombok.Data;

@Data
public class UserInfoResponse {

    private boolean result;

    private String userName;

    private String message;


}
