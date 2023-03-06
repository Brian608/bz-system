package com.feather.bz.manage.api;

import com.feather.bz.common.constants.CoreConstant;
import io.swagger.annotations.Api;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.api
 * @className: FilePreviewController
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-03-03 17:45
 * @version: 1.0
 */

@Api(value = "文件预览", tags = {"文件预览"})
@RestController
@RequestMapping(CoreConstant.API+CoreConstant.V1+"/file-preview")
public class FilePreviewController {


//    @GetMapping("/preview/{name}")
//    public  ResponseEntity<byte[]> previewFile( @PathVariable("name") String name) throws IOException {
//
//        // 根据文件类型获取对应的Resource
//        Resource resource = new ClassPathResource("static/"+ name);
//
//        // 判断文件是否存在
//        if (!resource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // 获取文件名和扩展名
//        String fileName = resource.getFilename();
//        String fileExt = StringUtils.getFilenameExtension(fileName);
//
//        // 根据文件扩展名判断文件类型
//        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
//        if ("pdf".equalsIgnoreCase(fileExt)) {
//            mediaType = MediaType.APPLICATION_PDF;
//        } else if ("doc".equalsIgnoreCase(fileExt) || "docx".equalsIgnoreCase(fileExt)) {
//            mediaType = MediaType.valueOf("APPLICATION/vnd.ms-word");
//        } else if ("xls".equalsIgnoreCase(fileExt) || "xlsx".equalsIgnoreCase(fileExt)) {
//            mediaType = MediaType.valueOf("APPLICATION/vnd.ms-excel");
//        } else if ("txt".equalsIgnoreCase(fileExt)) {
//            mediaType = MediaType.TEXT_PLAIN;
//        }
//
//        // 读取文件内容并生成响应
//        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(mediaType);
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentLength(bytes.length);
//        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//    }


    @GetMapping("/preview/{fileName:.+}")
    public ResponseEntity<Resource> preview(@PathVariable String fileName) throws IOException {
        Resource file = loadFile(fileName);
        String contentType = determineContentType(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    private Resource loadFile(String fileName) {
        Resource resource = new ClassPathResource("static/" + fileName);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not find file: " + fileName);
        }
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return MediaType.APPLICATION_PDF_VALUE;
        } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            return "APPLICATION/vnd.ms-word";
        } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            return "APPLICATION/vnd.ms-excel";
        } else {
            return MediaType.TEXT_PLAIN_VALUE;
        }
    }
}
