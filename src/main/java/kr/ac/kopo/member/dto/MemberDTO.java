package kr.ac.kopo.member.dto;


import kr.ac.kopo.member.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor //필드를 모두다 매개변수로하는 생성자를 만들어줌
@NoArgsConstructor //기본생성자를 자동으로 만들어줌
@ToString //DTO객체가 가지고 있는 필드 값을 출력할때 string으로 자동으로 만들어줌
public class MemberDTO {
    private Long id;
    private String memberEmail;   //html에 있는 name과 dto의 필드값이 같다면 알아서 set해줘서 값을 넣어줌
    private String memberPassword;
    private String memberName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO =new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;
    }
}
