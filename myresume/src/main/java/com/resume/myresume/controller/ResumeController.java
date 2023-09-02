package com.resume.myresume.controller;

import com.resume.myresume.data.Resume;
import com.resume.myresume.repo.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ResumeController {
    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Resume> resumes = resumeRepository.findAll();
        model.addAttribute("resumes", resumes);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Resume resume = new Resume();
            resume.setName(name);
            resume.setResumeContent(file.getBytes());
            resumeRepository.save(resume);
        }
        return "redirect:/";
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> downloadResume(@PathVariable String name) {
        Optional<Resume> resumeOptional = resumeRepository.findByName(name);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + ".pdf\"")
                    .body(resume.getResumeContent());
        }
        return ResponseEntity.notFound().build();
    }
}

