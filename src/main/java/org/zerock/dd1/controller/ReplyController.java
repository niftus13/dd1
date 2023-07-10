package org.zerock.dd1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.ReplyDTO;
import org.zerock.dd1.dto.ReplyPageRequestDTO;
import org.zerock.dd1.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class ReplyController {

    private final ReplyService replyService;
    
    @GetMapping("{bno}/list")
    public PageResponceDTO<ReplyDTO> list(@PathVariable("bno") Long bno, ReplyPageRequestDTO requestDTO) {

        log.info("bno......." + bno);
        log.info(requestDTO);

        return replyService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String,Long> register(@RequestBody ReplyDTO replyDTO) {
        log.info("replyDTO..." + replyDTO);

        Long newRno = replyService.register(replyDTO);

        return Map.of("register" , newRno);  
    }

    @GetMapping(value = "/{rno}")
    public ReplyDTO getOne(@PathVariable("rno") Long rno) {
        return replyService.read(rno);
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {

        replyService.remove(rno);

        return Map.of("result" , rno);
    }

    @PutMapping("/{rno}")
    public Map<String, Long> modify(@RequestBody ReplyDTO replyDTO) {

        replyService.modify(replyDTO);
            
        return Map.of("result", replyDTO.getRno());
    }
}
