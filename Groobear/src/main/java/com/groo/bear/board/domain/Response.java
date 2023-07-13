//package com.groo.bear.board.domain;
//
//import java.time.LocalDateTime;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//public class GalleryDto {
//
//    @Getter
//    @NoArgsConstructor
//    public static class Response {
//        private Long id;
//        private String memberName;
//        private String title;
//        private String contents;
//        private LocalDateTime createdDateTime;
//
//
//        public Response(Gallery entity) {
//            this.id = entity.getId();
//            this.memberName = entity.getMemberName();
//            this.title = entity.getTitle();
//            this.contents = entity.getContents();
//            this.createdDateTime = entity.getCreatedDateTime();
//        }
//    }
//
//    @Getter
//    public static class ListResponse {
//        private Long id;
//        private String member;
//        private String title;
//
//        public ListResponse(Gallery entity) {
//            this.id = entity.getId();
//            this.member = entity.getMemberName();
//            this.title = entity.getTitle();
//        }
//    }
//}