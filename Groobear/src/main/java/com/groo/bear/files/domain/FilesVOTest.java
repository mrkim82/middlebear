package com.groo.bear.files.domain;

import java.math.BigDecimal;

public class FilesVOTest {

    public static void main(String[] args) {
        // 일반적인 생성자를 통한 객체 생성
        FilesVO filesVO = new FilesVO("테스트.txt", "abcdeabcde.txt", new BigDecimal(10768));

        // 같은 파라미터를 가진 또 다른 객체 생성
        FilesVO filesVO2 = new FilesVO("테스트.txt", "abcdeabcde.txt", new BigDecimal(10768));
    }
}
