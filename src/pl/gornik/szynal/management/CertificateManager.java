package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class CertificateManager {
    private final Map<String, String> certificates = new HashMap<>();

    public void addCertificate(String name, String certificate) {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Nazwa użytkownika nie może być pusta.");
        }
        if (certificate == null || certificate.isEmpty()) {
            throw new ValidationException("Nazwa certyfikatu nie może być pusta.");
        }
        certificates.put(name, certificate);
    }

    public void listCertificates() {
        System.out.println("Lista certyfikatów:");
        if (certificates.isEmpty()) {
            System.out.println("Brak certyfikatów.");
        } else {
            certificates.forEach((name, certificate) ->
                    System.out.println("Użytkownik: " + name + ", Certyfikat: " + certificate)
            );
        }
    }
}
