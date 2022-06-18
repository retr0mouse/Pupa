import React, { useEffect, useState } from "react";
import { QuizAPI } from "../apis/QuizAPI";
import { Message } from "../components/Message";
import { Quizzes } from "../components/Quizzes";

export function QuizzesList() {
    const [quizzes, setQuizzes] = useState([]) as any;
    const [message, setMessage] = useState() as any;

    useEffect(() => {
        fetchQuizzes().catch(console.log);
    }, []);

    return (
        <div>
            <Quizzes quizzes={quizzes}/>
            <Message
                updateMessage={() => setMessage()}
                message={message}
            />
        </div>
    );

    async function fetchQuizzes() {
        const quizzes = await QuizAPI.getQuizzes();
        if (quizzes.length == 0) {
            setMessage("No quizzes found");
            return;
        }
        setQuizzes(quizzes);
    }
}
