package kr.ac.kopo.member.entity;

import jakarta.persistence.*;
import kr.ac.kopo.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private  Long id;

    @Column(unique = true) //unique 제약조건 추가
    private  String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private  String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return  memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return  memberEntity;
    }

}
