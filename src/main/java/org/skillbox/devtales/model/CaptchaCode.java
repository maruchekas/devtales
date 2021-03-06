package org.skillbox.devtales.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "captcha_codes")
public class CaptchaCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATETIME")
    @NonNull
    private LocalDateTime time;

    @Column(columnDefinition = "TINYTEXT")
    @NonNull
    private String code;

    @Column(name = "secret_code", columnDefinition = "TINYTEXT")
    @NonNull
    private String secretCode;

}
