package kr.ac.kopo.member.repository;


import kr.ac.kopo.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //특정 쿼리 작성하려면 여기서 정의~


    //이메일로 회원 정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail); //(select * from member_table where member_email=?)


}
