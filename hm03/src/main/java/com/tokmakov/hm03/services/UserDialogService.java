package com.tokmakov.hm03.services;

import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.models.User;
import com.tokmakov.hm03.models.UserQuizResult;

import java.util.List;

public interface UserDialogService {
    User askUserName();
    void sayHelloToUser(User user);
    UserQuizResult askQuestionsToUser(List<Question> questions);
}
