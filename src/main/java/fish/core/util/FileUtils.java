package fish.core.util;

import fish.common.file.entity.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

@RequiredArgsConstructor
@Component
public class FileUtils {
    public static String rootPath;
    private static String fileDomain;

    @Value("${file.filePath}")
    public void setRootPath(String value) {
        rootPath = value;
    }

    @Value("${file.fileDomain}")
    public void setFileDomain(String value) {
        fileDomain = value;
    }

    public static FileEntity fileUpload(MultipartFile multipartFile, String uploadFolder) throws IOException {

        String saveFilePath = FileUtils.calcPath(rootPath + uploadFolder + "/");

        // input은 있으나 전달받은 파일이 없는경우 패스
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String originFileExtension = originFileName.substring(originFileName.lastIndexOf(".")).toLowerCase(); //소문자로 처리
        String systemFileName;
        systemFileName = UUID.randomUUID().toString().replaceAll("-", "") + originFileExtension;
        //확장자 체크
        String[] allowedExt = "jpg,jpeg,png,gif,bmp,docx,xlsx,pptx,hwp,hwpx,pdf,zip,ppt,pptx,xls".split(",");
        boolean isAllowed = false;
        for (String ext : allowedExt) {
            if (originFileExtension.replace(".", "").equals(ext)) {
                isAllowed = true;
                break;
            }
        }
        if (!isAllowed) {
            return null;
            //throw new CustomException("== [파일업로드] 허용된 확장자가 아닙니다. ext :  " + originFileExtension, ErrorCode.NOT_ALLOWED_EXT);
        }

        // 절대 경로로 변환
        Path path = Paths.get(rootPath + uploadFolder + "/" + saveFilePath + "/" + systemFileName).toAbsolutePath();
        multipartFile.transferTo(path.toFile());

        // 파일패스가 있는경우에 saveFilePath에 전달받은 filePath 추가해서 전달
        String filePath;

        filePath = fileDomain + uploadFolder + saveFilePath;

        return new FileEntity(originFileName, systemFileName, filePath, multipartFile.getSize(), originFileExtension);

    }

    public static String calcPath(String uploadPath) {

        Calendar cal = Calendar.getInstance();

        String yearPath = "/" + cal.get(Calendar.YEAR);
        String monthPath = yearPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths) {

        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
        }
    }
}
