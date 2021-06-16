package downstagram.downstagram.utils;

import downstagram.downstagram.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonFileUtils {

    private static String profile_path = "C:/Users/JKEY93/Desktop/project/studyjpa/downstagram/src/main/resources/static/img/profile/";

    public static String profileUpload(MultipartFile mFile, User user) {
        DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String originalFileName = mFile.getOriginalFilename();
        String filePathAndName = timeStampPattern.format(LocalDateTime.now())+user.getId()+originalFileName.substring(originalFileName.lastIndexOf("."));

        try {
            if (user.getProfileImg() != null) { // 기존 프로필 사진이 있을경우
                File file = new File(profile_path + user.getProfileImg()); // 기존파일 가져오기
                file.delete(); // 기존파일 삭제
            }

            mFile.transferTo(new File(profile_path + filePathAndName));  // 업로드
            return filePathAndName;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
