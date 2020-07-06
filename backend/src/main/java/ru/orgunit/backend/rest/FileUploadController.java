package ru.orgunit.backend.rest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    @GetMapping("/")
    @ResponseBody
    public byte[] listUploadedFiles() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/static/index.html");
        return IOUtils.toByteArray(in);
    }
}
