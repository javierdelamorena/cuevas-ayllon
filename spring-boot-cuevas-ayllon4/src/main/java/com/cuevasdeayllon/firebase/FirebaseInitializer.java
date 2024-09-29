package com.cuevasdeayllon.firebase;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.controllers.AnunciosController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {
	private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);
	@PostConstruct

	public void iniFireStor() throws IOException {

		InputStream serviceAccount = getClass().getClassLoader()
				.getResourceAsStream("spring-boot-cuevas-de-ayllon.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://default.firebaseio.com/")
				.build();
//		if (FirebaseApp.getApps().isEmpty()) {
//			FirebaseApp.initializeApp(options);
//		}
	}

	public Firestore getFireStore() {
		return FirestoreClient.getFirestore();
	}
	@PostConstruct
    public void firebaseInit() throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource("spring-boot-cuevas-de-ayllon.json");

            FirebaseOptions options = new FirebaseOptions
                .Builder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .build();
            FirebaseApp.initializeApp(options);
            logger.info("isFirebaseActive = true"+ resource.toString());
        } catch (Exception e) {
            logger.error("Post Construct: {}", e);
        }
    }

}
