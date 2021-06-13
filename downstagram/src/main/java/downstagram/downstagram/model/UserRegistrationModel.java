package downstagram.downstagram.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationModel {
    @NotEmpty
    @Size(min = 3, max = 12, message = "3 자리 이상 12 자리 이하이어야 합니다.")
    private String userid;

    @NotEmpty
    @Size(min = 8, max = 20, message = "8 자리 이상 20 자리 이하이어야 합니다.")
    private String passwd1;
    @NotEmpty
    @Size(min = 2, max = 10, message = "2 자리 이상 10 자리 이하이어야 합니다.")
    private String name;
    private String phone;
}
