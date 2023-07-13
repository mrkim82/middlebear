package com.groo.bear.files.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groo.bear.files.domain.FilesVO;

@Mapper
public interface FileMapper {

    /**
     * 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    public void saveAll(List<FilesVO> files);

}