package com.virtusa.security.service;

import com.virtusa.security.model.Member;

public interface MemberService {
    void save(Member mem);

    Member findByUsername(String username);
}
