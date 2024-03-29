package com.codeWayTask4;





	
	import java.util.Scanner;
	import java.util.Timer;
	import java.util.TimerTask;

	class Quiz {
	    private static final int QUESTION_TIME_LIMIT = 10; // Time limit for each question in seconds
	    private int score = 0;
	    private int currentQuestionIndex = 0;
	    private final Question[] questions;

	    public Quiz(Question[] questions) {
	        this.questions = questions;
	    }

	    public void startQuiz() {
	        System.out.println("Welcome to the Quiz!");
	        System.out.println("You will have " + QUESTION_TIME_LIMIT + " seconds to answer each question.");
	        System.out.println("Let's begin!");
	        displayNextQuestion();
	    }

	    private void displayNextQuestion() {
	        if (currentQuestionIndex < questions.length) {
	            Question currentQuestion = questions[currentQuestionIndex];
	            System.out.println("\n" + currentQuestion.getQuestion());
	            currentQuestion.displayOptions();
	            startTimer();
	        } else {
	            displayResult();
	        }
	    }

	    private void startTimer() {
	        Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            int timeRemaining = QUESTION_TIME_LIMIT;

	            @Override
	            public void run() {
	                if (timeRemaining >= 0) {
	                    System.out.println("Time remaining: " + timeRemaining + " seconds");
	                    timeRemaining--;
	                } else {
	                    System.out.println("Time's up!");
	                    timer.cancel();
	                    evaluateAnswer(null);
	                }
	            }
	        }, 0, 1000); // Schedule the timer to run every second

	        evaluateAnswer(timer);
	    }

	    private void evaluateAnswer(Timer timer) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter your choice (1-" + questions[currentQuestionIndex].getOptions().length + "): ");
	        int userChoice = scanner.nextInt();
	        if (userChoice == questions[currentQuestionIndex].getCorrectAnswer()) {
	            System.out.println("Correct!");
	            score++;
	        } else {
	            System.out.println("Incorrect!");
	        }
	        if (timer != null) {
	            timer.cancel(); // Cancel the timer if user submits the answer before time's up
	        }
	        currentQuestionIndex++;
	        displayNextQuestion();
	    }

	    private void displayResult() {
	        System.out.println("Quiz completed!");
	        System.out.println("Your final score is: " + score + "/" + questions.length);
	    }
	}

	class Question {
	    private final String question;
	    private final String[] options;
	    private final int correctAnswer;

	    public Question(String question, String[] options, int correctAnswer) {
	        this.question = question;
	        this.options = options;
	        this.correctAnswer = correctAnswer;
	    }

	    public String getQuestion() {
	        return question;
	    }

	    public String[] getOptions() {
	        return options;
	    }

	    public int getCorrectAnswer() {
	        return correctAnswer;
	    }

	    public void displayOptions() {
	        for (int i = 0; i < options.length; i++) {
	            System.out.println((i + 1) + ". " + options[i]);
	        }
	    }
	}

	public class QuizAppWithTimer {
	    public static void main(String[] args) {
	        // Sample Quiz Questions
	        Question[] questions = {
	            new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 1),
	            new Question("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare", "Charles Dickens", "Jane Austen", "Leo Tolstoy"}, 1),
	            new Question("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "NaCl", "O2"}, 1),
	            new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 1),
	            new Question("What is the tallest mammal?", new String[]{"Giraffe", "Elephant", "Horse", "Kangaroo"}, 1)
	        };

	        // Initialize and start the quiz
	        Quiz quiz = new Quiz(questions);
	        quiz.startQuiz();
	    }
	}



