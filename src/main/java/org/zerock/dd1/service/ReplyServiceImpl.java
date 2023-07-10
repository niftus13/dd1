package org.zerock.dd1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.dd1.domain.Reply;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.ReplyDTO;
import org.zerock.dd1.dto.ReplyPageRequestDTO;
import org.zerock.dd1.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;


    @Override
    public PageResponceDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();
        int pageNum = requestDTO.getPage();

        if(last){
            Long totalCount = replyRepository.getCountBoard(requestDTO.getBno());

            pageNum = (int)(Math.ceil(totalCount/(double)requestDTO.getSize()));
            pageNum = pageNum <= 0 ? 1: pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum-1, requestDTO.getSize(), Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(requestDTO.getBno(), pageable);
        
        long totalReplyCount = result.getTotalElements();


        List<ReplyDTO> dtoList = result.get().map(en -> modelMapper.map(en, ReplyDTO.class))
        .collect(Collectors.toList());

        PageResponceDTO<ReplyDTO> responceDTO = new PageResponceDTO<>(dtoList, totalReplyCount, requestDTO);

        responceDTO.setPage(pageNum);
        return responceDTO;
    }


    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);

        log.info("reply...");
        log.info(reply);

        Long newRno = replyRepository.save(reply).getRno();

        return newRno;  
    }


    @Override
    public ReplyDTO read(Long rno) {
        
        Optional<Reply> result = replyRepository.findById(rno);
        
        Reply reply = result.orElseThrow();

        return modelMapper.map(reply, ReplyDTO.class);
    }


    @Override
    public void remove(Long rno) {
        
        Optional<Reply> result = replyRepository.findById(rno);

        Reply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다");
        reply.changeFile(null);

        replyRepository.save(reply);
    }

}
