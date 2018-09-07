package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("app")
public class AppController {
 
 @Autowired
 private Environment env;
 
 @GetMapping("/property")
 public String getPropertyValue(@RequestParam("key") String key)
 {
  String returnValue = "No value";
  
  String keyValue = env.getProperty(key);
  
  if( keyValue!= null && !keyValue.isEmpty())
  {
   returnValue = keyValue;
  }
  return returnValue;
 }
}