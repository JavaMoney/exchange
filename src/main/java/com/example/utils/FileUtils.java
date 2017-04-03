package com.example.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by woorea on 03/04/2017.
 */
public class FileUtils {

  public static String readClassPathResourceAsString(ClassPathResource resource) throws IOException {
    return new String(Files.readAllBytes(Paths.get(resource.getURI())));
  }

  public static String readClassPathResourceAsString(String path) throws IOException {
    ClassPathResource classPathResource = new ClassPathResource(path);
    return readClassPathResourceAsString(classPathResource);
  }

}
