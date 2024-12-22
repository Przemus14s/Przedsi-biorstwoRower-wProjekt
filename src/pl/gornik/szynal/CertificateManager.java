package pl.gornik.szynal;

import java.util.*;

public class CertificateManager {
    private final List<String> certificates;

    public CertificateManager() {
        certificates = new ArrayList<>();
    }

    public void addCertificate(String certificate) {
        certificates.add(certificate);
    }

    public void listCertificates() {
        if (certificates.isEmpty()) {
            System.out.println("Brak certyfikatów.");
        } else {
            System.out.println("Lista certyfikatów:");
            for (String cert : certificates) {
                System.out.println("- " + cert);
            }
        }
    }
}
