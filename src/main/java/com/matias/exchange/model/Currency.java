package com.matias.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Instant;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Currency implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Double rate;
    @CreatedDate
    private Instant createdAt;

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        return result;
    }
}
