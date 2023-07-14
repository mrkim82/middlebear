package com.groo.bear.files.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    @Transactional
    public void saveFiles(final int boardNo, final List<FilesVO> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FilesVO file : files) {
            file.setBoardNo(boardNo);
        }
        fileMapper.saveAll(files);
    }

}