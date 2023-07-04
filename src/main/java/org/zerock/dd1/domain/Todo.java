package org.zerock.dd1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// todo 만들기
@Entity
@Table(name = "tbl_todo2")
//테스트데이터 만들기용
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Todo {
        // id객체는 Long 타입을 주로 사용
    @Id
    // 키 생성전략(identity -> autoimcrement) 번호는 DB에서 따짐
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 300, nullable = false)
    private String title;

    // 수정
    public void changeTitle(String title) {
        this.title = title;
    }

}
