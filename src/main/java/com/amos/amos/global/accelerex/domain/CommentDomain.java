package com.amos.amos.global.accelerex.domain;

import lombok.Data;

@Data
public class CommentDomain {
    Long id;
    String comment;
    String ipAddress;
    String created;

    public CommentDomain(Long id,
                         String comment,
                         String ipAddress,
                         String created) {
        this.id = id;
        this.comment = comment;
        this.ipAddress = ipAddress;
        this.created = created;
    }
}
