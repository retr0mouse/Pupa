package com.example.demo.models;

import com.example.demo.models.PackToQuiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "TranslateQuiz")
@Table (name = "translate_quiz")
public class TranslateQuiz {
    @Id // automatically determines the primary key
    @SequenceGenerator( // generate a sequence
            name = "translate_quiz_sequence",
            sequenceName = "translate_quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "translate_quiz_sequence"
    )
    @Column (
            name = "id",
            updatable = false
    )
    private Long id;

    @Column (
            name = "initial_word"
    )
    private String initialWord;

    @Column (
            name = "translated_word"
    )
    private String translatedWord;

    @OneToMany (
            mappedBy = "translateQuiz",
            cascade = CascadeType.ALL
    )
    private List<PackToQuiz> packsToQuizzes = new ArrayList<>();

    private void addPackToQuiz(PackToQuiz packToQuiz) {
        if (!packsToQuizzes.contains(packToQuiz)) {
            packsToQuizzes.add(packToQuiz);
        }
    }

    private void remotePackToQuiz(PackToQuiz packToQuiz) {
        packsToQuizzes.remove(packToQuiz);
    }

    public TranslateQuiz() {
    }

    public TranslateQuiz(String initialWord, String translatedWord) {
        this.initialWord = initialWord;
        this.translatedWord = translatedWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialWord() {
        return initialWord;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public List<PackToQuiz> getPacksToQuizzes() {
        return packsToQuizzes;
    }

    public void setPacksToQuizzes(List<PackToQuiz> packsToQuizzes) {
        this.packsToQuizzes = packsToQuizzes;
    }
}
