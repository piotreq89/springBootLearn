package com.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity  // klasa do zmapowania w bazie
public class LottoResultModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // informacja że to jest ID i że ma być wygenerowana automatycznie
    private long id;

    private Date lotteryDrawingDate;

    @ElementCollection(fetch = FetchType.EAGER)  // to wiadomo inna kolekcja, [EAGER - zachłanny, LAZY - leniwy]
    private Collection<Integer> numbers;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLotteryDrawingDate() {
        return lotteryDrawingDate;
    }

    public void setLotteryDrawingDate(Date lotteryDrawingDate) {
        this.lotteryDrawingDate = lotteryDrawingDate;
    }

    public Collection<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(Collection<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "LottoResultModel{" +
                "id=" + id +
                ", lotteryDrawingDate=" + lotteryDrawingDate +
                ", numbers=" + numbers +
                '}';
    }
}
