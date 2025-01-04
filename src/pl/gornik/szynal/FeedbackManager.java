package pl.gornik.szynal;

import java.util.*;

public class FeedbackManager {
    private static class Feedback {
        private int rating;
        private String comment;

        public Feedback(int rating, String comment) {
            this.rating = rating;
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "Ocena: " + rating + ", Komentarz: " + comment;
        }
    }

    private List<Feedback> feedbacks = new ArrayList<>();

    public void addFeedback(int rating, String comment) {
        feedbacks.add(new Feedback(rating, comment));
    }

    public void listFeedbacks() {
        System.out.println("Opinie:");
        for (Feedback feedback : feedbacks) {
            System.out.println(feedback);
        }
    }
}