package J_N_Super_Pvt_Ltd.asset.userManagement.entity.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSessionLogStatus {
    LOGGED("User Logged"),
    LOGOUT("User Logout"),
    FAILURE("Failure");

    private final String userSessionLogStatus;
}
