package downstagram.downstagram.utils;

import downstagram.downstagram.domain.PostImage;
import downstagram.downstagram.domain.User;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonFileUtils {

    private static String profile_path = "C:/Users/JKEY93/Desktop/project/studyjpa/downstagram/src/main/resources/static/img/profile/";
    private static String feed_path = "C:/Users/JKEY93/Desktop/project/studyjpa/downstagram/src/main/resources/static/img/feed/";
    private static final DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String profileUpload(MultipartFile mFile, User user) {
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

    public static List<PostImage> feedUpload(List<MultipartFile> mFiles, String userId) throws Exception {
        String path = feed_path + userId;

        File file = new File(path);
        if (!file.exists()) { // userId의 폴더가 없다면
            file.mkdirs(); // 폴더생성
        }

        int fileNum = 0;
        List<PostImage> postImages = new ArrayList<>();
        for (MultipartFile f : mFiles) { // 사진리스트를 하나씩 풀어서 post_image에 따로따로저장

            String original_name = f.getOriginalFilename();

            // 임의의 파일이름은 혹시나 중복된 파일이름을 방지하기위함이다.
            String newFile_name = rnd(original_name, f.getBytes(), path);

            PostImage postImage = new PostImage(newFile_name);
            postImages.add(postImage);
        }

        return postImages;
    }

    // 임의의 파일이름 생성하는 메소드
    private static String rnd(String originalName, byte[] fileData, String path) throws Exception {
        UUID uuid = UUID.randomUUID();
        String savedName = uuid.toString() + "_" + originalName;
        File target = new File(path, savedName);

        FileCopyUtils.copy(fileData, target);
        return savedName;
    }
}
