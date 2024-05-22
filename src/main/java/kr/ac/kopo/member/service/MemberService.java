package kr.ac.kopo.member.service;

import kr.ac.kopo.member.dto.MemberDTO;
import kr.ac.kopo.member.entity.MemberEntity;
import kr.ac.kopo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //1. dto -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        //2. repository의 save 메서드 호출
        memberRepository.save(memberEntity);  //여기서의 save는 jpa가 제공해주는 save메서드임.   내가만든 save메서드가 아님.
        
        //repository의 save 메서드 호출 (조건. entity객체를 넘겨줘야함)
        
    }
    
    public MemberDTO login(MemberDTO memberDTO){
        //1. 회원이 입력한 이메일로 db에서 조회를 함
        //2. db에서 조회한 비밀번호와 사용자가 입력한 비빌번호가 일치하는 판단

        Optional<MemberEntity> byMemberEmail =memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //if문에 걸리는건 조회 결과가 있다는것!( 해당 이메일을 가진 회원 정보가 있다)
          MemberEntity memberEntity  = byMemberEmail.get(); //->get은 Optional 의 포장지를 벗겨냄. 객체를 가져옴
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //if문에 걸리는건 비밀번호가 일치하는 경우!
                //엔티티를 컨트롤러에 넘겨줄때 dto로 해줘야됨. entity ->dto로 변환후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else{
                //비밀번호가 불일치하는경우!
                return  null;
            }

        }else{
            //조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return  null;
        }


    }

    public List<MemberDTO> findAll(){
       List<MemberEntity> memberEntityList =memberRepository.findAll(); // 여기는 findAll()은 repository에서 제공해주는 메서드임.
       List<MemberDTO> memberDTOList =new ArrayList<>();

       for(MemberEntity memberEntity : memberEntityList){
           memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
       }
       return memberDTOList;

    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return  null;
        }


    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {

        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO)); //save는 insert , update 둘다 가능
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
         Optional<MemberEntity> byMemberEmail =memberRepository.findByMemberEmail(memberEmail);
         if (byMemberEmail.isPresent()){
            //조회결과가 있다라는것은 -> 사용할수 없다.
            return null;
         }else {
             //조회 결과가 없다 -> 사용할수 있다.
             return "ok";
         }
    }
}

