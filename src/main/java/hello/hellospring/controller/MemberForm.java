package hello.hellospring.controller;

public class MemberForm {
    private String name;

    // 이름 가져오기
    public String getName() {
        return name;
    }

    // 이름 저장
    public void setName(String name) {
        this.name = name;
    }
}
