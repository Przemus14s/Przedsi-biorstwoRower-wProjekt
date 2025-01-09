package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class FeedbackManager {
    private static class Feedback {
        private final int rating;
        private final String comment;

        public Feedback(int rating, String comment) {
            if (rating < 1 || rating > 5) {
                throw new ValidationException("Ocena musi być w przedziale 1-5.");
            }
            this.rating = rating;
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "Ocena: " + rating + ", Komentarz: " + comment;
        }
    }

    private final List<Feedback> feedbacks = new ArrayList<>();

    public void addFeedback(int rating, String comment) {
        feedbacks.add(new Feedback(rating, comment));
    }

    public void listFeedbacks() {
        if (feedbacks.isEmpty()) {
            System.out.println("Brak opinii.");
        } else {
            System.out.println("Opinie:");
            feedbacks.forEach(System.out::println);
        }
    }
}
