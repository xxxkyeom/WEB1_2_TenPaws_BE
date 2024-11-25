package com.example.tenpaws.domain.faq.controller;

import com.example.tenpaws.domain.faq.dto.FaqRequest;
import com.example.tenpaws.domain.faq.dto.FaqResponse;
import com.example.tenpaws.domain.faq.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faqs")
public class FaqController {
    private final FaqService faqService;

    @GetMapping("/{faqId}")
    public ResponseEntity<FaqResponse> getFaq(@PathVariable("faqId") Long faqId) {
        return ResponseEntity.ok(faqService.read(faqId));
    }

    @GetMapping("/child/{parentId}")
    public ResponseEntity<List<FaqResponse>> getChildFaq(@PathVariable("parentId") Long parentId) {
        return ResponseEntity.ok(faqService.findByParentId(parentId));
    }

    @PostMapping
    public ResponseEntity<FaqResponse> createFaq(@Valid @RequestBody FaqRequest faqRequest) {
        return ResponseEntity.ok(faqService.create(faqRequest));
    }

    @PutMapping("/{faqId}")
    public ResponseEntity<FaqResponse> updateFaq(@Valid @RequestBody FaqRequest faqRequest, @PathVariable("faqId") Long faqId) {
        faqRequest.setFaqId(faqId);
        return ResponseEntity.ok(faqService.update(faqRequest));
    }

    @DeleteMapping("/{faqId}")
    public ResponseEntity<Map<String, String>> deleteFaq(@PathVariable("faqId") Long faqId) {
        faqService.delete(faqId);
        return ResponseEntity.ok(Map.of("message", "Faq deleted"));
    }
}