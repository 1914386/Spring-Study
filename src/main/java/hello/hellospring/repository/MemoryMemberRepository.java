package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 세팅
        store.put(member.getId(), member); // HashMap에 정보 저장
        return member; // 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // HashMap에서 꺼내기 (null이어도 가능)
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // getName()이 name과 일치하는지
                .findAny(); // 찾으면 반환, 없으면 null을 포함하여 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // Member 반환
    }

    public void clearStore() {
        store.clear(); // store을 지움
    }
}
