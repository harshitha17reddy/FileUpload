package com.example.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RestController
@RequestMapping({"/api"})
public class FileController {
	  
	@Autowired
	private FileRepository fileRepository;
	
    

    @PostMapping(value="/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
    	
    	try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileData(file.getBytes());
            fileRepository.save(fileEntity);

            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file");
        }
    	
    }
    @GetMapping(value="files")
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
    
   

  @GetMapping(path = {"/{id}"})
  public java.util.Optional<FileEntity> findOne(@PathVariable("id") long id){
      return fileRepository.findById(id);
  }
    	
    
//    public FileEntity saveDocument(@RequestBody FileEntity fileEntity) {
//    	return fileService.create(fileEntity);	
//    }
    
    

    
   
}
