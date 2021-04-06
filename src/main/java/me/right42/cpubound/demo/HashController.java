package me.right42.cpubound.demo;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class HashController {

    @GetMapping("/hello")
    public String hello(){
        return "hello deploy3";
    }


    @GetMapping("/hash/{input}")
    public String getHash(@PathVariable String input) {
        for (int i = 0; i < 100000; i++) {
            input = getMd5Digest(input);
        }
        return input;
    }

    private String getMd5Digest(String input) {
        byte[] digest = DigestUtils.md5Digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
