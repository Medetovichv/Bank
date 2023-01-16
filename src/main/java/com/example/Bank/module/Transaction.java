package com.example.Bank.module;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dateTime;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account transaction;

    private BigDecimal amount;



}

//{
//        "id": 1,
//        "dateTime": "2022-12-31 23:59:59",
//        "type": "Transfer between accounts",
//        "accountFrom": "2",
//        "accountTo": "4",
//        "amount": 200.50
//        }
