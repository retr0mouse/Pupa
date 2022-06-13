package com.example.demo.services;

import com.example.demo.models.QuizPack;
import com.example.demo.models.UserTable;
import com.example.demo.repositories.PackToQuizRepository;
import com.example.demo.repositories.QuizPackRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuizPackService {

    private final QuizPackRepository quizRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuizPackService(QuizPackRepository quizRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    public List<QuizPack> getQuizPacks() {
        return quizRepository.findAll();
    }

    public void addNewQuizPack(QuizPack quiz, Long userId) {
        Optional<UserTable> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("UserTable with id (" + userId + ") does not exist");
        }
        Optional<QuizPack> quizOptional = quizRepository.findQuizPackByTitleAndCreator(quiz.getTitle(), userOptional.get());
        if (quizOptional.isPresent()) {
            throw new IllegalStateException("UserTable " + userOptional.get().getUsername() + " already has a quiz with title '" + quizOptional.get().getTitle() + "'");
        }
        quiz.setCreated(LocalDate.now());
        userOptional.get().addQuizPack(quiz);
        quizRepository.save(quiz);
    }


    public QuizPack getQuizPackById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The quiz pack with id (" + id + ") is not in the database"
                ));
    }
}
