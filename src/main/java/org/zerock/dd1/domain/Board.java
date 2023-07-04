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

@Entity
@Table(name = "t_board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity{
    
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    // 컬럼 추가
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;


    public void changeTitle(String title){
        this.title = title;
    }





}