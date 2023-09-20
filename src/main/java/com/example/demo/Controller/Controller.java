package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Response.Response;
import com.example.demo.Service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class Controller {
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file){
		if(userService.HasCSVFromar(file)) {
			userService.ProcessAndSaveData(file);
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Uploaded successfully :" + file.getOriginalFilename()));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response("please upload csv file"));		
	}
	
	@CrossOrigin
	@GetMapping("/get")
	public ResponseEntity<?>getAllProduct(){
		return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
	}
}
